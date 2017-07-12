package com.android.activelife.tampa.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.adpater.HoursSceduleListAdapter;
import com.android.activelife.tampa.adpater.LocationsListAdapter;
import com.android.activelife.tampa.adpater.MessagesListAdapter;
import com.android.activelife.tampa.appcontroller.ActiveLifeApplication;
import com.android.activelife.tampa.db.LocationData;
import com.android.activelife.tampa.db.LocationsData;
import com.android.activelife.tampa.db.MessageLocationData;
import com.android.activelife.tampa.db.MessagesData;
import com.android.activelife.tampa.services.request.ApiRequest;
import com.android.activelife.tampa.services.response.messagesdata.MessagesDataResponse;
import com.android.activelife.tampa.ui.MainActivity;
import com.android.activelife.tampa.ui.MessageDetailsActivity;
import com.android.activelife.tampa.util.Utilities;
import com.android.activelife.tampa.util.Utils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MemberDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemberDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RadioGroup mDetailsRG;
    private ListView messagesList;
    private TextView mNoMessages;
    private LinearLayout mFilterLayout,mListLayout;
    private RadioButton mMessagessRB, mDonateRB, mHoursRB, mContactRB, mProgramsRB;
    private RelativeLayout mLayoutContainer;
    private ApiRequest mApiInterface;
    private int offset = 0;
    private List<MessagesData> mMessagesDataResponseList;
    private List<MessageLocationData> mMessageLocationDataResponseList;
    private Handler mHandler = new Handler();
    private ListView hoursList;
    private Spinner mLocationSpinner;
    private Button mApplyButton, mClearFilterButton;
    private CheckBox mFilterImageView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<LocationsData> mLocationDataResponsesList;
    private String mLocationId;


    public MemberDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MemberDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MemberDetailsFragment newInstance(String param1, String param2) {
        MemberDetailsFragment fragment = new MemberDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_member_details, container, false);
        mMessagesDataResponseList = new ArrayList<>();
        mMessageLocationDataResponseList = new ArrayList<>();
        mDetailsRG = (RadioGroup) view.findViewById(R.id.member_details_rg);
        mMessagessRB = (RadioButton) view.findViewById(R.id.messages);
        mDonateRB = (RadioButton) view.findViewById(R.id.donate);
        mHoursRB = (RadioButton) view.findViewById(R.id.hours);
        mContactRB = (RadioButton) view.findViewById(R.id.contact);
        mProgramsRB = (RadioButton) view.findViewById(R.id.programs);
        mLayoutContainer = (RelativeLayout) view.findViewById(R.id.layout_container);
        mProgramsRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    setProgramDetails(savedInstanceState);
                }
            }
        });
        mDonateRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    setDonateDetails(savedInstanceState);
                }
            }
        });
        mMessagessRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    setMessagesList(savedInstanceState);
                }
            }
        });
        mContactRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    setContactData(savedInstanceState);
                }
            }
        });
        mHoursRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    setHoursList(savedInstanceState);
                }
            }
        });
        mMessagessRB.setChecked(true);
        return view;
    }

    public void setProgramDetails(final Bundle savedInstanceState) {
        View child = getLayoutInflater(savedInstanceState).inflate(R.layout.layout_member_details_programs_donate, null);
        WebView webview = (WebView) child.findViewById(R.id.programs_donate_wv);
        List<LocationData> data = ActiveLifeApplication.getInstance().setUpDb().getLocation();
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        if (data != null && data.size() > 0) {
            webview.setVisibility(View.VISIBLE);
            if (data.get(0).getLocation_donate_link() != null && data.get(0).getLocation_donate_link().length() > 0) {
                webview.loadUrl(data.get(0).getLocation_program_link());
            } else {
                String htmlString = "<div id='MyEdit'><b>No Programs Available</b></div>";
                webview.loadData(htmlString, "text/html; charset=utf-8", "UTF-8");
            }
        } else {
            String htmlString = "<div id='MyEdit'><b>No Programs Available</b></div>";
            webview.loadData(htmlString, "text/html; charset=utf-8", "UTF-8");
        }
        setLayoutParams(child);
    }

    public void setDonateDetails(final Bundle savedInstanceState) {
        View child = getLayoutInflater(savedInstanceState).inflate(R.layout.layout_member_details_programs_donate, null);
        WebView webview = (WebView) child.findViewById(R.id.programs_donate_wv);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        List<LocationData> data = ActiveLifeApplication.getInstance().setUpDb().getLocation();
        if (data != null && data.size() > 0) {
            webview.setVisibility(View.VISIBLE);
            if (data.get(0).getLocation_donate_link() != null && data.get(0).getLocation_donate_link().length() > 0) {
                webview.loadUrl(data.get(0).getLocation_donate_link());
            } else {
                String htmlString = "<div id='MyEdit'><b>No Donations Link Available</b></div>";
                webview.loadData(htmlString, "text/html; charset=utf-8", "UTF-8");
            }
        } else {
            String htmlString = "<div id='MyEdit'><b>No Donations Link Available</b></div>";
            webview.loadData(htmlString, "text/html; charset=utf-8", "UTF-8");
        }
        setLayoutParams(child);
    }


    public void setContactData(final Bundle savedInstanceState) {
        View child = getLayoutInflater(savedInstanceState).inflate(R.layout.layout_member_details_contact, null);
        TextView ymcaName = (TextView) child.findViewById(R.id.member_name);
        TextView ymcaAddress = (TextView) child.findViewById(R.id.member_address);
        TextView ymcaPhone = (TextView) child.findViewById(R.id.member_phone);
        TextView ymcaEmail = (TextView) child.findViewById(R.id.member_email);
        List<LocationData> data = ActiveLifeApplication.getInstance().setUpDb().getLocation();
        if (data != null && data.size() > 0) {
            ymcaName.setVisibility(View.VISIBLE);
            ymcaName.setText("" + data.get(0).getLocation_name());
            String address = data.get(0).getLocation_address();
            if (address != null && address.length() > 0) {
                address = address + "\n " + data.get(0).getLocation_city() + " - " + data.get(0).getLocation_zip();
            } else {
                address = data.get(0).getLocation_city() + " - " + data.get(0).getLocation_zip();
            }
            if (address != null && address.length() > 0) {
                address = address + ", " + data.get(0).getLocation_state();
            } else {
                address = data.get(0).getLocation_state();
            }
            if (address != null && address.length() > 0) {
                ymcaAddress.setVisibility(View.VISIBLE);
                ymcaAddress.setText(address);
            } else {
                ymcaAddress.setVisibility(View.GONE);
            }
            if (data.get(0).getLocation_phone() != null && data.get(0).getLocation_phone().length() > 0) {
                ymcaPhone.setVisibility(View.VISIBLE);
                ymcaPhone.setText(data.get(0).getLocation_phone());
            } else {
                ymcaPhone.setVisibility(View.GONE);
            }
            if (data.get(0).getLocation_email() != null && data.get(0).getLocation_email().length() > 0) {
                ymcaEmail.setVisibility(View.VISIBLE);
                ymcaEmail.setText(data.get(0).getLocation_email());
            } else {
                ymcaEmail.setVisibility(View.GONE);
            }

        } else {
            ymcaName.setVisibility(View.GONE);
            ymcaAddress.setVisibility(View.GONE);
            ymcaPhone.setVisibility(View.GONE);
            ymcaEmail.setVisibility(View.GONE);


        }
        setLayoutParams(child);
    }

    public void setHoursList(final Bundle savedInstanceState) {
        View child = getLayoutInflater(savedInstanceState).inflate(R.layout.layout_member_details_hours, null);
        setLayoutParams(child);
        hoursList = (ListView) child.findViewById(R.id.hours_list);
        hoursList.setAdapter(new HoursSceduleListAdapter(getActivity(), ActiveLifeApplication.getInstance().setUpDb().getHours()));

    }

    public void setMessagesList(final Bundle savedInstanceState) {
        View child = getLayoutInflater(savedInstanceState).inflate(R.layout.layout_member_details_messages, null);
        setLayoutParams(child);
        messagesList = (ListView) child.findViewById(R.id.messages_list);
        mNoMessages= (TextView) child.findViewById(R.id.no_messages);
        mFilterImageView = (CheckBox) child.findViewById(R.id.img_schedule_filter);
        mLocationSpinner = (Spinner) child.findViewById(R.id.location_spinner);
        mFilterLayout = (LinearLayout) child.findViewById(R.id.spinner_layout);
        mListLayout = (LinearLayout) child.findViewById(R.id.list_layout);
        mApplyButton = (Button) child.findViewById(R.id.btn_apply_filter);
        setLocationSpinnerData();
        mClearFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocationSpinner.setSelection(0);
                mMessagesDataResponseList=ActiveLifeApplication.getInstance().setUpDb().getMessages();
                if(mMessagesDataResponseList!=null&&mMessagesDataResponseList.size()>0) {
                    messagesList.setVisibility(View.VISIBLE);
                    mNoMessages.setVisibility(View.GONE);
                    messagesList.setAdapter(new MessagesListAdapter(getActivity(), mMessagesDataResponseList));
                }else{
                    messagesList.setVisibility(View.GONE);
                    mNoMessages.setVisibility(View.VISIBLE);
                }
            }
        });
        mApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((mLocationId == null || mLocationId.length() == 0)) {
                    Utilities.showToast(getActivity(), "Please select a branch to apply filter");
                    mFilterImageView.setChecked(true);
                } else {
                    mFilterImageView.setChecked(false);
                    ((MainActivity) getActivity()).showProgressDialog(getActivity());
                    ArrayList<MessageLocationData> data = ActiveLifeApplication.getInstance().setUpDb().getMessageLocationsById(mLocationId);
                    if (data != null && data.size() > 0) {
                        mMessagesDataResponseList = new ArrayList<MessagesData>();
                        mMessageLocationDataResponseList = new ArrayList<MessageLocationData>();
                        mMessageLocationDataResponseList.addAll(data);
                        for (int i = 0; i < data.size(); i++) {
                            MessagesData dts = ActiveLifeApplication.getInstance().setUpDb().getMessagesById(data.get(i).getMessage_id());
                            mMessagesDataResponseList.add(dts);
                        }
                    }

                    if(mMessagesDataResponseList!=null&&mMessagesDataResponseList.size()>0) {
                        messagesList.setVisibility(View.VISIBLE);
                        mNoMessages.setVisibility(View.GONE);
                        messagesList.setAdapter(new MessagesListAdapter(getActivity(), mMessagesDataResponseList));
                    }else{
                        messagesList.setVisibility(View.GONE);
                        mNoMessages.setVisibility(View.VISIBLE);
                    }
                    ((MainActivity) getActivity()).hideProgressDialog(getActivity());
                }
            }
        });

        mFilterImageView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mFilterLayout.setVisibility(View.VISIBLE);
                    mListLayout.setVisibility(View.GONE);
                } else {
                    mFilterLayout.setVisibility(View.GONE);
                    mListLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        mClearFilterButton = (Button) child.findViewById(R.id.clear_filter);
        mMessagesDataResponseList = new ArrayList<>();
        messagesList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (totalItemCount != 0) {
                    if ((firstVisibleItem + visibleItemCount + 1) > (totalItemCount - 2)) {

                        int remaining = totalItemCount % 20;
                        Log.i("remaining: ", ": " + remaining);
                        if (remaining == 0) {
                            offset = totalItemCount;
                            mHandler.removeCallbacks(sendUpdatesToUI);
                            mHandler.postDelayed(sendUpdatesToUI, 1000 * 2);
                        }
                    } else {
                        mHandler.removeCallbacks(sendUpdatesToUI);
                    }
                }
            }
        });
        messagesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent messsageIntent = new Intent(getActivity(), MessageDetailsActivity.class);
                messsageIntent.putExtra("title", mMessagesDataResponseList.get(position).getMessage_title());
                messsageIntent.putExtra("desc", mMessagesDataResponseList.get(position).getMessage_msg());
                String location = null;
                LocationsData data = ActiveLifeApplication.getInstance().setUpDb().getLocationById(mLocationId);
                if (location != null && location.length() > 0) {
                    location = location + ", " + data.getLocation_name();
                } else {
                    location = data.getLocation_name();
                }

                messsageIntent.putExtra("location", location);
                String dateTime = mMessagesDataResponseList.get(position).getMessage_send_at();
                messsageIntent.putExtra("date", Utils.getApplyiedDateType(dateTime, "yyyy-MM-dd'T'HH:mm:ss-HH:mm", "MMM dd, EEE"));
                messsageIntent.putExtra("time", Utils.getApplyiedDateType(dateTime, "yyyy-MM-dd'T'HH:mm:ss-HH:mm", "hh:mm a"));
                startActivity(messsageIntent);

            }
        });
        getMessagesList();
    }

    private void setLocationSpinnerData() {
        mLocationDataResponsesList = new ArrayList<>();
        LocationsData ld = new LocationsData();
        ld.setLocation_id(null);
        ld.setLocation_name("Choose Location");
        mLocationDataResponsesList.add(0, ld);
        mLocationDataResponsesList = ActiveLifeApplication.getInstance().setUpDb().getLocations();
        mLocationSpinner.setAdapter(new LocationsListAdapter(getActivity(), mLocationDataResponsesList));
        mLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                mLocationId = mLocationDataResponsesList.get(i).getLocation_id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        List<LocationData> date = ActiveLifeApplication.getInstance().setUpDb().getLocation();
        if (date != null && date.size() > 0) {
            mLocationSpinner.setSelection(date.get(0).getPostion());
        }
    }

    public void getMessagesList() {
        if (((MainActivity) getActivity()).checkIfInternet(getActivity())) {
            mApiInterface = ActiveLifeApplication.getInstance()
                    .getApiRequest();
            Call<List<MessagesDataResponse>> call = mApiInterface.getMessages(offset, Utilities.getSharedPrefernceData().retreiveIntValueFromSharedPreference(getActivity().getApplicationContext(), Utilities.getSharedPrefernceData().APP_DEFAULT_LOCATION_ID));
            call.enqueue(new Callback<List<MessagesDataResponse>>() {
                @Override
                public void onResponse(Call<List<MessagesDataResponse>> call, Response<List<MessagesDataResponse>> response) {


                    if (response.isSuccessful()) {
                        ActiveLifeApplication.getInstance().setUpDb().deleteMessageLocations();
                        ActiveLifeApplication.getInstance().setUpDb().deleteMessages();
                        for (int i = 0; i < response.body().size(); i++) {
                            MessagesData data = new MessagesData();
                            data.setMessage_id("" + response.body().get(i).getId());
                            data.setMessage_msg("" + response.body().get(i).getMessage());
                            data.setMessage_title("" + response.body().get(i).getTitle());
                            data.setMessage_send_at("" + response.body().get(i).getSendAt());
                            for (int j = 0; j < response.body().get(i).getLocations().size(); j++) {
                                MessageLocationData msl = new MessageLocationData();
                                msl.setMessage_id("" + response.body().get(i).getId());
                                msl.setMessage_msg("" + response.body().get(i).getMessage());
                                msl.setMessage_title("" + response.body().get(i).getTitle());
                                msl.setMessage_send_at("" + response.body().get(i).getSendAt());
                                msl.setLocation_id("" + response.body().get(i).getLocations().get(j).getId());
                                msl.setLocation_name("" + response.body().get(i).getLocations().get(j).getName());
                                msl.setLocation_address("" + response.body().get(i).getLocations().get(j).getAddress());
                                msl.setLocation_city("" + response.body().get(i).getLocations().get(j).getCity());
                                msl.setLocation_state("" + response.body().get(i).getLocations().get(j).getState());
                                msl.setLocation_zip("" + response.body().get(i).getLocations().get(j).getZipCode());
                                msl.setLocation_phone("" + response.body().get(i).getLocations().get(j).getPhone());
                                msl.setLocation_email("" + response.body().get(i).getLocations().get(j).getEmail());
                                mMessageLocationDataResponseList.add(msl);
                            }
                            mMessagesDataResponseList.add(data);
                        }
                        ActiveLifeApplication.getInstance().setUpDb().insertMessagesList(mMessagesDataResponseList);
                        ActiveLifeApplication.getInstance().setUpDb().insertMessageLocationList(mMessageLocationDataResponseList);
                        if(mMessagesDataResponseList!=null&&mMessagesDataResponseList.size()>0) {
                            messagesList.setVisibility(View.VISIBLE);
                            mNoMessages.setVisibility(View.GONE);
                            messagesList.setAdapter(new MessagesListAdapter(getActivity(), mMessagesDataResponseList));
                        }else{
                            messagesList.setVisibility(View.GONE);
                            mNoMessages.setVisibility(View.VISIBLE);
                        }
                        ((MainActivity) getActivity()).hideProgressDialog(getActivity());
                    } else {
                        ((MainActivity) getActivity()).hideProgressDialog(getActivity());
                        if (response.errorBody() != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                Utilities.showToast(getActivity(), "" + jsonObject.getString("message"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }


                    }

                }

                @Override
                public void onFailure(Call<List<MessagesDataResponse>> call, Throwable t) {
                    ((MainActivity) getActivity()).hideProgressDialog(getActivity());

                }
            });
            ((MainActivity) getActivity()).showProgressDialog(getActivity());
        }
    }

    Runnable sendUpdatesToUI = new Runnable() {
        public void run() {

            getMessagesList();

        }
    };

    @Override
    public void onDestroy() {
        try {
            mHandler.removeCallbacks(sendUpdatesToUI);
        } catch (Exception e) {

        }
        super.onDestroy();
    }

    public void setLayoutParams(View child) {
        mLayoutContainer.removeAllViews();
        mLayoutContainer.addView(child);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
        child.setLayoutParams(params);
    }
}
