package com.android.activelife.tampa.db;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "SCHEDULE_DATE_DATA".
 */
@Entity
public class ScheduleDateData {

    @Id(autoincrement = true)
    private Long id;
    private int schedule_id;
    private String schedule_name;
    private String class_id;
    private String class_name;
    private String schedule_type_id;
    private String schedule_type;
    private String schedule_start_time;
    private String schedule_end_time;
    private Boolean schedule_monday;
    private Boolean schedule_tuesday;
    private Boolean schedule_wednesday;
    private Boolean schedule_thursday;
    private Boolean schedule_friday;
    private Boolean schedule_saturday;
    private Boolean schedule_sunday;
    private String schedule_frequency;
    private Boolean is_cancelled;
    private String instructor_id;
    private String instructor_name;
    private String location_id;
    private String location_name;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public ScheduleDateData() {
    }

    public ScheduleDateData(Long id) {
        this.id = id;
    }

    @Generated
    public ScheduleDateData(Long id, int schedule_id, String schedule_name, String class_id, String class_name, String schedule_type_id, String schedule_type, String schedule_start_time, String schedule_end_time, Boolean schedule_monday, Boolean schedule_tuesday, Boolean schedule_wednesday, Boolean schedule_thursday, Boolean schedule_friday, Boolean schedule_saturday, Boolean schedule_sunday, String schedule_frequency, Boolean is_cancelled, String instructor_id, String instructor_name, String location_id, String location_name) {
        this.id = id;
        this.schedule_id = schedule_id;
        this.schedule_name = schedule_name;
        this.class_id = class_id;
        this.class_name = class_name;
        this.schedule_type_id = schedule_type_id;
        this.schedule_type = schedule_type;
        this.schedule_start_time = schedule_start_time;
        this.schedule_end_time = schedule_end_time;
        this.schedule_monday = schedule_monday;
        this.schedule_tuesday = schedule_tuesday;
        this.schedule_wednesday = schedule_wednesday;
        this.schedule_thursday = schedule_thursday;
        this.schedule_friday = schedule_friday;
        this.schedule_saturday = schedule_saturday;
        this.schedule_sunday = schedule_sunday;
        this.schedule_frequency = schedule_frequency;
        this.is_cancelled = is_cancelled;
        this.instructor_id = instructor_id;
        this.instructor_name = instructor_name;
        this.location_id = location_id;
        this.location_name = location_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getSchedule_name() {
        return schedule_name;
    }

    public void setSchedule_name(String schedule_name) {
        this.schedule_name = schedule_name;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getSchedule_type_id() {
        return schedule_type_id;
    }

    public void setSchedule_type_id(String schedule_type_id) {
        this.schedule_type_id = schedule_type_id;
    }

    public String getSchedule_type() {
        return schedule_type;
    }

    public void setSchedule_type(String schedule_type) {
        this.schedule_type = schedule_type;
    }

    public String getSchedule_start_time() {
        return schedule_start_time;
    }

    public void setSchedule_start_time(String schedule_start_time) {
        this.schedule_start_time = schedule_start_time;
    }

    public String getSchedule_end_time() {
        return schedule_end_time;
    }

    public void setSchedule_end_time(String schedule_end_time) {
        this.schedule_end_time = schedule_end_time;
    }

    public Boolean getSchedule_monday() {
        return schedule_monday;
    }

    public void setSchedule_monday(Boolean schedule_monday) {
        this.schedule_monday = schedule_monday;
    }

    public Boolean getSchedule_tuesday() {
        return schedule_tuesday;
    }

    public void setSchedule_tuesday(Boolean schedule_tuesday) {
        this.schedule_tuesday = schedule_tuesday;
    }

    public Boolean getSchedule_wednesday() {
        return schedule_wednesday;
    }

    public void setSchedule_wednesday(Boolean schedule_wednesday) {
        this.schedule_wednesday = schedule_wednesday;
    }

    public Boolean getSchedule_thursday() {
        return schedule_thursday;
    }

    public void setSchedule_thursday(Boolean schedule_thursday) {
        this.schedule_thursday = schedule_thursday;
    }

    public Boolean getSchedule_friday() {
        return schedule_friday;
    }

    public void setSchedule_friday(Boolean schedule_friday) {
        this.schedule_friday = schedule_friday;
    }

    public Boolean getSchedule_saturday() {
        return schedule_saturday;
    }

    public void setSchedule_saturday(Boolean schedule_saturday) {
        this.schedule_saturday = schedule_saturday;
    }

    public Boolean getSchedule_sunday() {
        return schedule_sunday;
    }

    public void setSchedule_sunday(Boolean schedule_sunday) {
        this.schedule_sunday = schedule_sunday;
    }

    public String getSchedule_frequency() {
        return schedule_frequency;
    }

    public void setSchedule_frequency(String schedule_frequency) {
        this.schedule_frequency = schedule_frequency;
    }

    public Boolean getIs_cancelled() {
        return is_cancelled;
    }

    public void setIs_cancelled(Boolean is_cancelled) {
        this.is_cancelled = is_cancelled;
    }

    public String getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(String instructor_id) {
        this.instructor_id = instructor_id;
    }

    public String getInstructor_name() {
        return instructor_name;
    }

    public void setInstructor_name(String instructor_name) {
        this.instructor_name = instructor_name;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
