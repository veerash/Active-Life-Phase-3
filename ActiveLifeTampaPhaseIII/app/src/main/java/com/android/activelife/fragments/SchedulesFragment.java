package com.android.activelife.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.activelife.R;
import com.android.activelife.adpater.ClassesListAdapter;
import com.android.activelife.adpater.InstructorsListAdapter;
import com.android.activelife.adpater.LocationListAdapter;
import com.android.activelife.adpater.LocationsListAdapter;
import com.android.activelife.adpater.ScheduesDataDBRecyclerAdapter;
import com.android.activelife.adpater.ScheduesDataRecyclerAdapter;
import com.android.activelife.adpater.SchedulesListAdapter;
import com.android.activelife.appcontroller.ActiveLifeApplication;
import com.android.activelife.db.ClassData;
import com.android.activelife.db.InstructorData;
import com.android.activelife.db.LocationData;
import com.android.activelife.db.LocationsData;
import com.android.activelife.db.ScheduleData;
import com.android.activelife.db.ScheduleDateData;
import com.android.activelife.services.request.ApiRequest;
import com.android.activelife.services.response.scheduledatedata.ScheduleDateDataResponse;
import com.android.activelife.ui.MainActivity;
import com.android.activelife.util.Utilities;
import com.android.activelife.util.Utils;

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
    private ScrollView mFilterLayout;
    private LinearLayout mScheduleLayout;
    private CheckBox mFilterImageView;
    private Button mApplyButton, mClearFilterButton;
    private RecyclerView mSchedulesList;
    private ApiRequest mApiInterface;
    private List<ScheduleDateData> data;
    private List<LocationsData> mLocationDataResponsesList;
    private Spinner mSchedulesSpinner, mClassSpinner, mInstructorsSpinner, mLocationSpinner;
    private String mScheduleId, mClassId, mInstructorId, mLocationId;
    private ArrayList<ScheduleData> mScheduleDatas;
    private ArrayList<InstructorData> mInstructorDatas;
    private ArrayList<ClassData> mClassDatas;
    private long startTime=040000,endTime=230000;
    private RangeSeekBar mRangeSeekbar;
    private TextView mNoMessages, mRightThumb, mLeftThumb;
    private String shceduleDate;

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
        data = new ArrayList<>();
        mLocationDataResponsesList=new ArrayList<>();
        mScheduleDatas = new ArrayList<>();
        mInstructorDatas = new ArrayList<>();
        mClassDatas = new ArrayList<>();
        mNoMessages= (TextView) rootView.findViewById(R.id.no_messages);
        mRangeSeekbar = (RangeSeekBar) rootView.findViewById(R.id.rangeSeekBar);
        textView = (TextView) rootView.findViewById(R.id.section_label);
        mRightThumb = (TextView) rootView.findViewById(R.id.right_thumb);
        mLeftThumb = (TextView) rootView.findViewById(R.id.left_thumb);
        mFilterLayout = (ScrollView) rootView.findViewById(R.id.filter_schedules);
        mScheduleLayout = (LinearLayout) rootView.findViewById(R.id.main_schedules);
        mLocationSpinner = (Spinner) rootView.findViewById(R.id.location_spinner);
        mSchedulesSpinner = (Spinner) rootView.findViewById(R.id.schedules_spinner);
        mClassSpinner = (Spinner) rootView.findViewById(R.id.classes_spinner);
        mInstructorsSpinner = (Spinner) rootView.findViewById(R.id.instructors_spinner);
        mFilterImageView = (CheckBox) rootView.findViewById(R.id.img_schedule_filter);
        mApplyButton = (Button) rootView.findViewById(R.id.btn_apply_filter);
        mClearFilterButton= (Button) rootView.findViewById(R.id.clear_filter);
        mSchedulesList = (RecyclerView) rootView.findViewById(R.id.schedules_list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mSchedulesList.setLayoutManager(manager);
        DividerItemDecoration item = new DividerItemDecoration(getActivity(), manager.getOrientation());
        item.setDrawable(getResources().getDrawable(R.drawable.divider));
        mSchedulesList.addItemDecoration(item);
        textView = (TextView) rootView.findViewById(R.id.section_label);
        mScheduleDatas.addAll(ActiveLifeApplication.getInstance().setUpDb().getSchedules());
        mClassDatas.addAll(ActiveLifeApplication.getInstance().setUpDb().getClasses());
        mInstructorDatas.addAll(ActiveLifeApplication.getInstance().setUpDb().getInstructors());
        mClearFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRangeSeekbar.setRangeValues(4,23);
                mLocationSpinner.setSelection(0);
                mSchedulesSpinner.setSelection(0);
                mClassSpinner.setSelection(0);
                mInstructorsSpinner.setSelection(0);
                mLocationId=null;
                mScheduleId=null;
                mClassId=null;
                mInstructorId=null;
                data=ActiveLifeApplication.getInstance().setUpDb().getScheduleDate();
                mFilterImageView.setChecked(false);
                if(data!=null&&data.size()>0){
                    mSchedulesList.setVisibility(View.VISIBLE);
                    mNoMessages.setVisibility(View.GONE);
                    mSchedulesList.setAdapter(new ScheduesDataDBRecyclerAdapter(getActivity(),data,shceduleDate));
                }else{
                    mSchedulesList.setVisibility(View.GONE);
                    mNoMessages.setVisibility(View.VISIBLE);
                }
            }
        });

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
        mRangeSeekbar.setRangeValues(4, 23);
        mRangeSeekbar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
                int minValues=Integer.parseInt(minValue.toString());
                int maxValues=Integer.parseInt(maxValue.toString());
                startTime = Long.parseLong(minValue.toString() + "0000");
                endTime = Long.parseLong(maxValue.toString() + "0000");

                if(minValues<12){
                    if(minValues<10){
                        mLeftThumb.setText("0"+minValues + ":00 AM");
                    }else {
                        mLeftThumb.setText(minValues + ":00 AM");
                    }
                }else{
                    int localValue=(minValues-12);
                    if(localValue<10){
                        mLeftThumb.setText("0"+localValue + ":00 PM");
                    }else {
                        mLeftThumb.setText(localValue + ":00 PM");
                    }
                }
                if(maxValues<12){
                    if(maxValues<10){
                        mRightThumb.setText("0"+maxValues + ":00 AM");
                    }else {
                        mRightThumb.setText(maxValues+":00 AM");
                    }

                }else{
                    int localValue=(maxValues-12);
                    if(localValue<10){
                        mRightThumb.setText("0"+localValue + ":00 PM");
                    }else {
                        mRightThumb.setText((maxValues-12)+":00 PM");
                    }

                }

            }
        });
        mApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((mLocationId == null || mLocationId.length() == 0) &&(mScheduleId == null || mScheduleId.length() == 0) && (mClassId == null || mClassId.length() == 0) && (mInstructorId == null || mInstructorId.length() == 0) && startTime == 000000 && endTime == 240000) {
                    Utilities.showToast(getActivity(), "Please select a category to apply filter");
                    mFilterImageView.setChecked(true);
                } else {
                    data = ActiveLifeApplication.getInstance().setUpDb().getScheduleDateOfId(mLocationId,mScheduleId, mClassId, mInstructorId, startTime, endTime);
                    if (data == null)
                        data = new ArrayList<ScheduleDateData>();
                    if(data!=null&&data.size()>0){
                        mSchedulesList.setVisibility(View.VISIBLE);
                        mNoMessages.setVisibility(View.GONE);
                        mSchedulesList.setAdapter(new ScheduesDataDBRecyclerAdapter(getActivity(),data,shceduleDate));
                    }else{
                        mSchedulesList.setVisibility(View.GONE);
                        mNoMessages.setVisibility(View.VISIBLE);
                    }
                    mFilterImageView.setChecked(false);
                }
            }
        });
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date dt = new Date();
        textView.setText("" + Utils.getApplyiedDateType(df.format(dt), "MM/dd/yyyy HH:mm:ss", "EEEE MMM dd"));

        setLocationSpinnerData();
        setScheduleSpinnerData();
        setClassSpinnerData();
        setInstructorSpinnerData();
        /** end after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        /** start before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);


        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(rootView, R.id.calendarView)
                .startDate(startDate.getTime())
                .endDate(endDate.getTime())
                .datesNumberOnScreen(5)
                .dayNameFormat("EEE")     // WeekDay text format
                .dayNumberFormat("dd")
                .textColor(Color.LTGRAY, Color.BLACK)
                .selectedDateBackground(Color.TRANSPARENT)  // Background color of the selected date cell.
                .selectorColor(Color.parseColor("#0a63ab"))   // Color of the selection indicator bar (default to colorAccent).
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Date date, int position) {
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                textView.setText("" + Utils.getApplyiedDateType(df.format(date), "MM/dd/yyyy HH:mm:ss", "EEEE MMM dd"));
                shceduleDate=Utils.getApplyiedDateType(df.format(date), "MM/dd/yyyy HH:mm:ss", "yyyy-MM-dd");
                getScheduleDateData(shceduleDate);
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
        shceduleDate=Utils.getApplyiedDateType(df.format(dt), "MM/dd/yyyy HH:mm:ss", "yyyy-MM-dd");
        getScheduleDateData(shceduleDate);
        return rootView;
    }

    private void setInstructorSpinnerData() {
        InstructorData id = new InstructorData();
        id.setInstructor_id(null);
        id.setInstructor_name("Choose Instructor");
        mInstructorDatas.add(0, id);
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
    }

    private void setClassSpinnerData() {
        ClassData cd = new ClassData();
        cd.setClass_id(null);
        cd.setClass_name("Choose Class");
        cd.setClass_description(null);
        mClassDatas.add(0, cd);
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
    }

    private void setScheduleSpinnerData() {
        ScheduleData sd = new ScheduleData();
        sd.setSchedule_type_id(null);
        sd.setSchedule_type("Choose Schedule");
        mScheduleDatas.add(0, sd);
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
    }

    private void setLocationSpinnerData() {
        LocationsData ld = new LocationsData();
        ld.setLocation_id(null);
        ld.setLocation_name("Choose Location");

        mLocationDataResponsesList = ActiveLifeApplication.getInstance().setUpDb().getLocations();
        mLocationDataResponsesList.add(0, ld);
        mLocationSpinner.setAdapter(new LocationListAdapter(getActivity(), mLocationDataResponsesList));
        mLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
               mLocationId=mLocationDataResponsesList.get(i).getLocation_id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        List<LocationData> date = ActiveLifeApplication.getInstance().setUpDb().getLocation();
        if (date != null && date.size() > 0) {
            mLocationSpinner.setSelection(date.get(0).getPostion()+1);
            mLocationId=date.get(0).getLocation_id();
        }
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
                        data=new ArrayList<ScheduleDateData>();
                        for (int i = 0; i < response.body().size(); i++) {
                            String startTime = response.body().get(i).getStartTime();
                            String endTime = response.body().get(i).getEndTime();
                            startTime = startTime.replaceAll(":", "");
                            endTime = endTime.replaceAll(":", "");
                            long start = Long.parseLong(startTime);
                            long end = Long.parseLong(endTime);
                            ScheduleDateData dateData = new ScheduleDateData();
                            dateData.setSchedule_id(response.body().get(i).getId());
                            dateData.setSchedule_name(response.body().get(i).getGetClass().getName());
                            dateData.setClass_id("" + response.body().get(i).getGetClass().getId());
                            dateData.setClass_name(response.body().get(i).getGetClass().getName());
                            dateData.setClass_desc(response.body().get(i).getGetClass().getDescription());
                            dateData.setSchedule_type_id("" + response.body().get(i).getScheduleTypeId());
                            dateData.setSchedule_type(response.body().get(i).getSchedule().getName());
                            dateData.setSchedule_start_date(response.body().get(i).getStartDate());
                            dateData.setSchedule_start_time(response.body().get(i).getStartTime());
                            dateData.setSchedule_end_time(response.body().get(i).getEndTime());
                            dateData.setSchedule_start_time_long(start);
                            dateData.setSchedule_end_time_long(end);
                            dateData.setSchedule_monday(response.body().get(i).getMonday());
                            dateData.setSchedule_tuesday(response.body().get(i).getTuesday());
                            dateData.setSchedule_wednesday(response.body().get(i).getWednesday());
                            dateData.setSchedule_thursday(response.body().get(i).getThursday());
                            dateData.setSchedule_friday(response.body().get(i).getFriday());
                            dateData.setSchedule_saturday(response.body().get(i).getSaturday());
                            dateData.setSchedule_sunday(response.body().get(i).getSunday());
                            dateData.setSchedule_frequency(response.body().get(i).getFrequency());
                            dateData.setIs_cancelled(response.body().get(i).getIsCancelled());
                            dateData.setIs_cancelled(response.body().get(i).getIsCancelled());
                            dateData.setIs_reservable(response.body().get(i).getIsReservable());
                            dateData.setInstructor_name(response.body().get(i).getInstructor().getName());
                            dateData.setLocation_id("" + response.body().get(i).getLocationId());
                            dateData.setLocation_name(response.body().get(i).getLocation().getName());
                            data.add(dateData);

                        }
                        ActiveLifeApplication.getInstance().setUpDb().insertSchedulesDateList(data);
                        if ((mLocationId == null || mLocationId.length() == 0) &&(mScheduleId == null || mScheduleId.length() == 0) && (mClassId == null || mClassId.length() == 0) && (mInstructorId == null || mInstructorId.length() == 0) && startTime == 040000 && endTime == 230000) {
                            mSchedulesList.setAdapter(new ScheduesDataRecyclerAdapter(getActivity(), response.body(),shceduleDate));
                        } else {
                            data = ActiveLifeApplication.getInstance().setUpDb().getScheduleDateOfId(mLocationId,mScheduleId, mClassId, mInstructorId, startTime, endTime);
                            if (data == null)
                                data = new ArrayList<ScheduleDateData>();
                            if(data!=null&&data.size()>0){
                                mSchedulesList.setVisibility(View.VISIBLE);
                                mNoMessages.setVisibility(View.GONE);
                                mSchedulesList.setAdapter(new ScheduesDataDBRecyclerAdapter(getActivity(),data,shceduleDate));
                            }else{
                                mSchedulesList.setVisibility(View.GONE);
                                mNoMessages.setVisibility(View.VISIBLE);
                            }
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
                public void onFailure(Call<List<ScheduleDateDataResponse>> call, Throwable t) {
                    ((MainActivity) getActivity()).hideProgressDialog(getActivity());
                }
            });
            ((MainActivity) getActivity()).showProgressDialog(getActivity());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1000:
                if(resultCode==getActivity().RESULT_OK){
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    Date dt = new Date();
                    getScheduleDateData(Utils.getApplyiedDateType(df.format(dt), "MM/dd/yyyy HH:mm:ss", "yyyy-MM-dd"));
                }
                break;
        }
    }
}
