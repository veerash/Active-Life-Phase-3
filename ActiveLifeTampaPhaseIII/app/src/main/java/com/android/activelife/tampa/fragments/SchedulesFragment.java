package com.android.activelife.tampa.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.activelife.tampa.R;
import com.android.activelife.tampa.adpater.ClassesListAdapter;
import com.android.activelife.tampa.adpater.InstructorsListAdapter;
import com.android.activelife.tampa.adpater.SchedulesDateListAdapter;
import com.android.activelife.tampa.adpater.SchedulesListAdapter;
import com.android.activelife.tampa.appcontroller.ActiveLifeApplication;
import com.android.activelife.tampa.db.ClassData;
import com.android.activelife.tampa.db.InstructorData;
import com.android.activelife.tampa.db.ScheduleData;
import com.android.activelife.tampa.db.ScheduleDateData;
import com.android.activelife.tampa.services.request.ApiRequest;
import com.android.activelife.tampa.services.response.scheduledatedata.ScheduleDateDataResponse;
import com.android.activelife.tampa.ui.MainActivity;
import com.android.activelife.tampa.ui.ScheduleContainerActivity;
import com.android.activelife.tampa.util.Utilities;
import com.android.activelife.tampa.util.Utils;

import org.florescu.android.rangeseekbar.RangeSeekBar;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vsatrasala on 3/1/2017.
 */

public class SchedulesFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private TextView textView;
    private String mParam1;
    private String mParam2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LinearLayout mFilterLayout;
    private LinearLayout mScheduleLayout;
    private CheckBox mFilterImageView;
    private Button mApplyButton;
    private ListView mSchedulesList;
    private ApiRequest mApiInterface;
    private List<ScheduleDateData> data;
    private Spinner mSchedulesSpinner, mClassSpinner, mInstructorsSpinner;
    private String mScheduleId, mClassId, mInstructorId;
    private ArrayList<ScheduleData> mScheduleDatas;
    private ArrayList<InstructorData> mInstructorDatas;
    private ArrayList<ClassData> mClassDatas;
    private long startTime=000000,endTime=240000;
    private RangeSeekBar mRangeSeekbar;

    public SchedulesFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static SchedulesFragment newInstance(String param1, String param2) {
        SchedulesFragment fragment = new SchedulesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        data=new ArrayList<>();
        mScheduleDatas = new ArrayList<>();
        mInstructorDatas = new ArrayList<>();
        mClassDatas = new ArrayList<>();
        mRangeSeekbar= (RangeSeekBar) rootView.findViewById(R.id.rangeSeekBar);
        textView= (TextView) rootView.findViewById(R.id.section_label);
        mFilterLayout = (LinearLayout) rootView.findViewById(R.id.filter_schedules);
        mScheduleLayout = (LinearLayout) rootView.findViewById(R.id.main_schedules);
        mSchedulesSpinner = (Spinner) rootView.findViewById(R.id.schedules_spinner);
        mClassSpinner = (Spinner) rootView.findViewById(R.id.classes_spinner);
        mInstructorsSpinner = (Spinner) rootView.findViewById(R.id.instructors_spinner);
        mFilterImageView = (CheckBox) rootView.findViewById(R.id.img_schedule_filter);
        mApplyButton = (Button) rootView.findViewById(R.id.btn_apply_filter);
        mSchedulesList = (ListView) rootView.findViewById(R.id.schedules_list);
        mScheduleDatas.addAll(ActiveLifeApplication.getInstance().setUpDb().getSchedules());
        mClassDatas.addAll(ActiveLifeApplication.getInstance().setUpDb().getClasses());
        mInstructorDatas.addAll(ActiveLifeApplication.getInstance().setUpDb().getInstructors());
        mFilterImageView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mFilterLayout.setVisibility(View.VISIBLE);
                    mScheduleLayout.setVisibility(View.GONE);
                } else {
                    mFilterLayout.setVisibility(View.GONE);
                    mScheduleLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        mRangeSeekbar.setRangeValues(0, 24);
        mRangeSeekbar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
                startTime=Long.parseLong(minValue.toString()+"0000");
                endTime=Long.parseLong(maxValue.toString()+"0000");



            }
        });
        mApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFilterImageView.setChecked(false);
                if ((mScheduleId == null || mScheduleId.length() == 0) && (mClassId == null || mClassId.length() == 0) && (mInstructorId == null || mInstructorId.length() == 0)) {
                    Utilities.showToast(getActivity(), "Please select a category to apply filter");
                } else {
                    data=ActiveLifeApplication.getInstance().setUpDb().getScheduleDateOfId(mScheduleId, mClassId, mInstructorId, startTime, endTime);
                    mSchedulesList.setAdapter(new SchedulesDateListAdapter(getActivity(), data));
                }
            }
        });
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date dt=new Date();
        textView.setText("" + Utils.getApplyiedDateType(df.format(dt), "MM/dd/yyyy HH:mm:ss", "EEEE MMM dd"));
        getScheduleDateData(Utils.getApplyiedDateType(df.format(dt), "MM/dd/yyyy HH:mm:ss", "yyyy-MM-dd"));
        mSchedulesSpinner.setAdapter(new SchedulesListAdapter(getActivity(), mScheduleDatas));
        mSchedulesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mScheduleId = mScheduleDatas.get(position).getSchedule_type_id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mClassSpinner.setAdapter(new ClassesListAdapter(getActivity(), mClassDatas));
        mClassSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mClassId = mClassDatas.get(position).getClass_id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mInstructorsSpinner.setAdapter(new InstructorsListAdapter(getActivity(), mInstructorDatas));
        mInstructorsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mInstructorId = mInstructorDatas.get(position).getInstructor_id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /** end after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        /** start before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);


//            HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(rootView, R.id.calendarView)
//                    .startDate(startDate.getTime())
//                    .endDate(endDate.getTime())
//                    .build();
        textView = (TextView) rootView.findViewById(R.id.section_label);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(rootView, R.id.calendarView)
                .startDate(startDate.getTime())
                .endDate(endDate.getTime())
                .datesNumberOnScreen(5)   // Number of Dates cells shown on screen (Recommended 5)
                .dayFormat("EEE")     // WeekDay text format
                .dayNumberFormat("dd")  // Date format
                .textColor(Color.LTGRAY, Color.WHITE)    // Text color for none selected Dates, Text color for selected Date.
                .selectedDateBackground(Color.TRANSPARENT)  // Background color of the selected date cell.
                .selectorColor(Color.RED)   // Color of the selection indicator bar (default to colorAccent).
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Date date, int position) {
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                textView.setText("" + Utils.getApplyiedDateType(df.format(date), "MM/dd/yyyy HH:mm:ss", "EEEE MMM dd"));
                getScheduleDateData(Utils.getApplyiedDateType(df.format(date), "MM/dd/yyyy HH:mm:ss", "yyyy-MM-dd"));
            }

            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView,
                                         int dx, int dy) {

            }

            @Override
            public boolean onDateLongClicked(Date date, int position) {
                return true;
            }
        });

        return rootView;
    }

    public void getScheduleDateData(final String date) {
        if (((MainActivity) getActivity()).checkIfInternet(getActivity())) {
            mApiInterface = ActiveLifeApplication.getInstance()
                    .getApiRequest();
            Call<List<ScheduleDateDataResponse>> call = mApiInterface.getScheduleDate(date);
            call.enqueue(new Callback<List<ScheduleDateDataResponse>>() {
                @Override
                public void onResponse(Call<List<ScheduleDateDataResponse>> call, Response<List<ScheduleDateDataResponse>> response) {

                    if (response.isSuccessful()) {
                        ActiveLifeApplication.getInstance().setUpDb().deleteScheduleDate();

                        for (int i = 0; i < response.body().size(); i++) {
                            String startTime = response.body().get(i).getStartTime();
                            String endTime = response.body().get(i).getEndTime();
                            startTime = startTime.replaceAll(":", "");
                            endTime = endTime.replaceAll(":", "");
                            long start = Long.parseLong(startTime);
                            long end = Long.parseLong(endTime);
                            boolean isMonday=false,isTuesday=false,isWednesday=false, isThursday=false, isFriday=false, isSaturday=false, isSunday =false, isCancelled=false;
                            if(response.body().get(i).getMonday()==1){
                                isMonday=true;
                            }
                            if(response.body().get(i).getTuesday()==1){
                                isTuesday=true;
                            }if(response.body().get(i).getWednesday()==1){
                                isWednesday=true;
                            }if(response.body().get(i).getThursday()==1){
                                isThursday=true;
                            }if(response.body().get(i).getFriday()==1){
                                isFriday=true;
                            }if(response.body().get(i).getSaturday()==1){
                                isSaturday=true;
                            }if(response.body().get(i).getSunday()==1){
                                isSunday=true;
                            }if(response.body().get(i).getIsCancelled()==1){
                                isCancelled=true;
                            }
                            ActiveLifeApplication.getInstance().setUpDb().insertSchedulesDate(response.body().get(i).getId(), response.body().get(i).getGetClass().getName(), "" + response.body().get(i).getGetClass().getId(), response.body().get(i).getGetClass().getName(), response.body().get(i).getGetClass().getDescription(), "" + response.body().get(i).getScheduleTypeId(), response.body().get(i).getSchedule().getName(), response.body().get(i).getStartDate(), response.body().get(i).getStartTime(), response.body().get(i).getEndTime(), start, end, isMonday, isTuesday, isWednesday, isThursday, isFriday, isSaturday, isSunday, response.body().get(i).getFrequency(), isCancelled, "" + response.body().get(i).getInstructorId(), response.body().get(i).getInstructor().getName(), "" + response.body().get(i).getLocationId(), response.body().get(i).getLocation().getName());
                        }
                        data=ActiveLifeApplication.getInstance().setUpDb().getScheduleDate();
                        mSchedulesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent scheduleDetailIntent = new Intent(getActivity(), ScheduleContainerActivity.class);
                                scheduleDetailIntent.putExtra("schedule_id", data.get(position).getId());
                                scheduleDetailIntent.putExtra("schedule_name", data.get(position).getSchedule_name());
                                startActivity(scheduleDetailIntent);
                            }
                        });
                        mSchedulesList.setAdapter(new SchedulesDateListAdapter(getActivity(), data));
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
                public void onFailure(Call<List<ScheduleDateDataResponse>> call, Throwable t) {
                    ((MainActivity) getActivity()).hideProgressDialog(getActivity());
                }
            });
            ((MainActivity) getActivity()).showProgressDialog(getActivity());
        }
    }
}
