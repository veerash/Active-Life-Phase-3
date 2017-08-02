package com.android.activelife.tampa.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.adpater.MembersListAdapter;
import com.android.activelife.tampa.appcontroller.ActiveLifeApplication;
import com.android.activelife.tampa.db.MemberData;
import com.android.activelife.tampa.services.request.ApiRequest;

import java.util.ArrayList;

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
    private RecyclerView mMembersListView;
    private String mParam1;
    private String mParam2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<MemberData> data;
    private TextView mNoMessages;
    private ImageView addDetails, closeDetails;
    private View cardView;
    private RelativeLayout mListLayout;
    private Button addButton;
    TextView memberEditName, memberEditId;

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
        data = new ArrayList<>();

        cardView = view.findViewById(R.id.add_edit_member_layout);
        addDetails = (ImageView) view.findViewById(R.id.add_member);
        closeDetails = (ImageView) view.findViewById(R.id.close_add_member);
        mNoMessages = (TextView) view.findViewById(R.id.no_messages);
        mListLayout = (RelativeLayout) view.findViewById(R.id.list_layout);
        mMembersListView = (RecyclerView) view.findViewById(R.id.members_list);
        addButton = (Button) cardView.findViewById(R.id.add_member);
        memberEditId = (TextView) cardView.findViewById(R.id.member_id_edit_text);
        memberEditName = (TextView) cardView.findViewById(R.id.member_name_edit_text);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mMembersListView.setLayoutManager(manager);
        DividerItemDecoration item = new DividerItemDecoration(getActivity(), manager.getOrientation());
        item.setDrawable(getResources().getDrawable(R.drawable.divider_members));
        mMembersListView.addItemDecoration(item);
        addDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardView.setVisibility(View.VISIBLE);
                closeDetails.setVisibility(View.VISIBLE);
                mListLayout.setVisibility(View.GONE);
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (memberEditId.getText() == null || memberEditId.getText().length() == 0) {
                    memberEditId.setError("Please enter id");
                } else if (memberEditName.getText() == null || memberEditName.getText().length() == 0) {
                    memberEditName.setError("Please enter name");
                } else {
                    memberEditId.setError(null);
                    memberEditName.setError(null);
                    ActiveLifeApplication.getInstance().setUpDb().insertOrReplaceMember(memberEditId.getText().toString(), memberEditName.getText().toString());
                    setMembersData();
                }
            }
        });
        closeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDetails.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                mListLayout.setVisibility(View.VISIBLE);
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(memberEditName.getWindowToken(), 0);
            }
        });
        setMembersData();
        return view;
    }

    public void setMembersData() {
        closeDetails.setVisibility(View.GONE);
        cardView.setVisibility(View.GONE);
        mListLayout.setVisibility(View.VISIBLE);
        data = ActiveLifeApplication.getInstance().setUpDb().getMembers();
        if (data != null && data.size() > 0) {
            mMembersListView.setVisibility(View.VISIBLE);
            mNoMessages.setVisibility(View.GONE);
            mMembersListView.setAdapter(new MembersListAdapter(getActivity(), data, this));
        } else {
            mMembersListView.setVisibility(View.GONE);
            mNoMessages.setVisibility(View.VISIBLE);
        }
    }


}