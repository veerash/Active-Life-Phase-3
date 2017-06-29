package com.android.activelife.tampa.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.adpater.MembersListAdapter;
import com.android.activelife.tampa.appcontroller.ActiveLifeApplication;
import com.android.activelife.tampa.services.request.ApiRequest;
import com.android.activelife.tampa.services.response.instructordata.InstructorDataResponse;
import com.android.activelife.tampa.ui.MainActivity;
import com.android.activelife.tampa.util.Utilities;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class MemberFragment extends Fragment {
    private ApiRequest mApiInterface;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private ListView mMembersListView;
    private String mParam1;
    private String mParam2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MemberFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MemberFragment newInstance(String param1, String param2) {
        MemberFragment fragment = new MemberFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member, container, false);

        mMembersListView = (ListView) view.findViewById(R.id.members_list);
        getInstructorsData();
        return view;
    }

    public void getInstructorsData() {
        if (((MainActivity) getActivity()).checkIfInternet(getActivity())) {
            mApiInterface = ActiveLifeApplication.getInstance()
                    .getApiRequest();
            Call<List<InstructorDataResponse>> call = mApiInterface.getInstructors();
            call.enqueue(new Callback<List<InstructorDataResponse>>() {
                @Override
                public void onResponse(Call<List<InstructorDataResponse>> call, Response<List<InstructorDataResponse>> response) {
                    ((MainActivity) getActivity()).hideProgressDialog(getActivity());
                    if (response.isSuccessful()) {
                        mMembersListView.setAdapter(new MembersListAdapter(getActivity(), response.body()));
                    } else {
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
                public void onFailure(Call<List<InstructorDataResponse>> call, Throwable t) {

                }
            });
            ((MainActivity) getActivity()).showProgressDialog(getActivity());
        }
    }


}