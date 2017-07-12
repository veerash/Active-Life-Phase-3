
package com.android.activelife.tampa.services.response.scheduledatedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleDateDataResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("instructor_id")
    @Expose
    private Integer instructorId;
    @SerializedName("area_id")
    @Expose
    private Object areaId;
    @SerializedName("schedule_type_id")
    @Expose
    private Integer scheduleTypeId;
    @SerializedName("class_id")
    @Expose
    private Integer classId;
    @SerializedName("location_id")
    @Expose
    private Integer locationId;
    @SerializedName("event_id")
    @Expose
    private Object eventId;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private Object endDate;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("monday")
    @Expose
    private Integer monday;
    @SerializedName("tuesday")
    @Expose
    private Integer tuesday;
    @SerializedName("wednesday")
    @Expose
    private Integer wednesday;
    @SerializedName("thursday")
    @Expose
    private Integer thursday;
    @SerializedName("friday")
    @Expose
    private Integer friday;
    @SerializedName("saturday")
    @Expose
    private Integer saturday;
    @SerializedName("sunday")
    @Expose
    private Integer sunday;
    @SerializedName("frequency")
    @Expose
    private String frequency;
    @SerializedName("is_cancelled")
    @Expose
    private Integer isCancelled;
    @SerializedName("is_reservable")
    @Expose
    private Integer isReservable;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("instructor")
    @Expose
    private Instructor instructor;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("get_class")
    @Expose
    private GetClass getClass;
    @SerializedName("schedule")
    @Expose
    private Schedule schedule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
    }

    public Object getAreaId() {
        return areaId;
    }

    public void setAreaId(Object areaId) {
        this.areaId = areaId;
    }

    public Integer getScheduleTypeId() {
        return scheduleTypeId;
    }

    public void setScheduleTypeId(Integer scheduleTypeId) {
        this.scheduleTypeId = scheduleTypeId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Object getEventId() {
        return eventId;
    }

    public void setEventId(Object eventId) {
        this.eventId = eventId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getMonday() {
        return monday;
    }

    public void setMonday(Integer monday) {
        this.monday = monday;
    }

    public Integer getTuesday() {
        return tuesday;
    }

    public void setTuesday(Integer tuesday) {
        this.tuesday = tuesday;
    }

    public Integer getWednesday() {
        return wednesday;
    }

    public void setWednesday(Integer wednesday) {
        this.wednesday = wednesday;
    }

    public Integer getThursday() {
        return thursday;
    }

    public void setThursday(Integer thursday) {
        this.thursday = thursday;
    }

    public Integer getFriday() {
        return friday;
    }

    public void setFriday(Integer friday) {
        this.friday = friday;
    }

    public Integer getSaturday() {
        return saturday;
    }

    public void setSaturday(Integer saturday) {
        this.saturday = saturday;
    }

    public Integer getSunday() {
        return sunday;
    }

    public void setSunday(Integer sunday) {
        this.sunday = sunday;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Integer getIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(Integer isCancelled) {
        this.isCancelled = isCancelled;
    }
    public Integer getIsReservable() {
        return isReservable;
    }

    public void setIsReservable(Integer isReservable) {
        this.isReservable = isReservable;
    }
    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public GetClass getGetClass() {
        return getClass;
    }

    public void setGetClass(GetClass getClass) {
        this.getClass = getClass;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

}
