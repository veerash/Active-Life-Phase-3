package com.android.activelife.tampa.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.adpater.HoursSceduleListAdapter;
import com.android.activelife.tampa.adpater.MessagesListAdapter;

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
    private RadioButton mMessagessRB, mDonateRB, mHoursRB, mContactRB, mProgramsRB;
    private RelativeLayout mLayoutContainer;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


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
        setLayoutParams(child);
    }

    public void setDonateDetails(final Bundle savedInstanceState) {
        View child = getLayoutInflater(savedInstanceState).inflate(R.layout.layout_member_details_programs_donate, null);
        setLayoutParams(child);
    }

    public void setContactData(final Bundle savedInstanceState) {
        View child = getLayoutInflater(savedInstanceState).inflate(R.layout.layout_member_details_contact, null);
        setLayoutParams(child);
    }

    public void setHoursList(final Bundle savedInstanceState) {
        View child = getLayoutInflater(savedInstanceState).inflate(R.layout.layout_member_details_hours, null);
        setLayoutParams(child);
        ListView hoursList = (ListView) child.findViewById(R.id.hours_list);
        hoursList.setAdapter(new HoursSceduleListAdapter(getActivity()));

    }

    public void setMessagesList(final Bundle savedInstanceState) {
        View child = getLayoutInflater(savedInstanceState).inflate(R.layout.layout_member_details_messages, null);
        setLayoutParams(child);
        ListView messagesList = (ListView) child.findViewById(R.id.messages_list);
        messagesList.setAdapter(new MessagesListAdapter(getActivity()));

        messagesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                View child = getLayoutInflater(savedInstanceState).inflate(R.layout.layout_message_details, null);
                setLayoutParams(child);
                ImageView backButton = (ImageView) child.findViewById(R.id.back_button);
                backButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setMessagesList(savedInstanceState);
                    }
                });

            }
        });
    }

    public void setLayoutParams(View child) {
        mLayoutContainer.removeAllViews();
        mLayoutContainer.addView(child);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
        child.setLayoutParams(params);
    }
}