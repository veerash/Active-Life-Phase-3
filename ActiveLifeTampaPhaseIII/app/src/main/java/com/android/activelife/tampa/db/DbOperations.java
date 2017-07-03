package com.android.activelife.tampa.db;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

import java.util.ArrayList;

/**
 * Created by vsatrasala on 7/3/2017.
 */

public class DbOperations {
    public static final boolean ENCRYPTED = true;
    private DaoSession daoSession;
    private DaoMaster jDaoMaster;
    private ScheduleDateDataDao jScheduleDateDataDao;
    private ScheduleDateData jScheduleDateData;
    private ScheduleDataDao jScheduleDataDao;
    private ScheduleData jScheduleData;
    private InstructorDataDao jInstructorDataDao;
    private InstructorData jInstructorData;
    private ClassDataDao jClassDataDao;
    private ClassData jClassData;
    private HoursDataDao jHoursDataDao;
    private HoursData jHoursData;

    public void setupDB(Context ctx) {
        try {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(ctx, "tampa-al"); //The users-db here is the name of our database.
            Database db = helper.getWritableDb();
            jDaoMaster = new DaoMaster(db);
            daoSession = jDaoMaster.newSession();
            jScheduleDateDataDao = daoSession.getScheduleDateDataDao();
            jScheduleDataDao = daoSession.getScheduleDataDao();
            jInstructorDataDao = daoSession.getInstructorDataDao();
            jClassDataDao = daoSession.getClassDataDao();
            jHoursDataDao = daoSession.getHoursDataDao();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public DaoSession getDaoSession() {
        if (daoSession == null) {
            daoSession = jDaoMaster.newSession();

        }
        return daoSession;
    }

    public ArrayList<ScheduleDateData> getScheduleDate() {
        if (jScheduleDateDataDao == null) {
            jScheduleDateDataDao = getDaoSession().getScheduleDateDataDao();

        }
        return (ArrayList<ScheduleDateData>) jScheduleDateDataDao
                .queryBuilder().build().list();
    }

    public void deleteScheduleDate() {
        try {
            if (jScheduleDateDataDao == null) {
                jScheduleDateDataDao = getDaoSession().getScheduleDateDataDao();

            }
            jScheduleDateDataDao.deleteAll();
        } catch (Exception e) {

        }
    }
    public void insertSchedules(String schedule_type_id, String schedule_type) {
        try {
            if (jScheduleDataDao == null) {
                jScheduleDataDao = getDaoSession().getScheduleDataDao();

            }
            jScheduleData = new ScheduleData(null, schedule_type_id, schedule_type);
            jScheduleDataDao.insert(jScheduleData);
        } catch (Exception e) {

        }

    }
    public ArrayList<ScheduleData> getSchedules() {
        if (jScheduleDataDao == null) {
            jScheduleDataDao = getDaoSession().getScheduleDataDao();

        }
        return (ArrayList<ScheduleData>) jScheduleDataDao
                .queryBuilder().build().list();
    }

    public void deleteSchedules() {
        try {
            if (jScheduleDataDao == null) {
                jScheduleDataDao = getDaoSession().getScheduleDataDao();

            }
            jScheduleDataDao.deleteAll();
        } catch (Exception e) {

        }
    }
    public void insertInstructors(String instructor_id, String instructor_name) {
        try {
            if (jInstructorDataDao == null) {
                jInstructorDataDao = getDaoSession().getInstructorDataDao();

            }
            jInstructorData = new InstructorData(null, instructor_id, instructor_name);
            jInstructorDataDao.insert(jInstructorData);
        } catch (Exception e) {

        }

    }
    public ArrayList<InstructorData> getInstructors() {
        if (jInstructorDataDao == null) {
            jInstructorDataDao = getDaoSession().getInstructorDataDao();

        }
        return (ArrayList<InstructorData>) jInstructorDataDao
                .queryBuilder().build().list();
    }

    public void deleteInstructors() {
        try {
            if (jInstructorDataDao == null) {
                jInstructorDataDao = getDaoSession().getInstructorDataDao();

            }
            jInstructorDataDao.deleteAll();
        } catch (Exception e) {

        }
    }

    public void insertClasses(String class_id, String class_name, String class_description) {
        try {
            if (jClassDataDao == null) {
                jClassDataDao = getDaoSession().getClassDataDao();
            }
            jClassData = new ClassData(null, class_id, class_name, class_description);
            jClassDataDao.insert(jClassData);
        } catch (Exception e) {

        }

    }

    public ArrayList<ClassData> getClasses() {
        if (jClassDataDao == null) {
            jClassDataDao = getDaoSession().getClassDataDao();

        }
        return (ArrayList<ClassData>) jClassDataDao
                .queryBuilder().build().list();
    }

    public void deleteClass() {
        try {
            if (jClassDataDao == null) {
                jClassDataDao = getDaoSession().getClassDataDao();

            }
            jClassDataDao.deleteAll();
        } catch (Exception e) {

        }
    }

    public void insertHoursDao(String hours_name, String hour_monday_start_time, String hour_monday_end_time, String hour_tuesday_start_time, String hour_tuesday_end_time, String hour_wednesday_start_time, String hour_wednesday_end_time, String hour_thursday_start_time, String hour_thursday_end_time, String hour_friday_start_time, String hour_friday_end_time, String hour_saturday_start_time, String hour_saturday_end_time, String hour_sunday_start_time, String hour_sunday_end_time) {
        try {
            if (jHoursDataDao == null) {
                jHoursDataDao = getDaoSession().getHoursDataDao();
            }
            jHoursData = new HoursData(null, hours_name, hour_monday_start_time, hour_monday_end_time, hour_tuesday_start_time, hour_tuesday_end_time, hour_wednesday_start_time, hour_wednesday_end_time, hour_thursday_start_time, hour_thursday_end_time, hour_friday_start_time, hour_friday_end_time, hour_saturday_start_time, hour_saturday_end_time, hour_sunday_start_time, hour_sunday_end_time);
            jHoursDataDao.insert(jHoursData);
        } catch (Exception e) {

        }

    }

    public ArrayList<HoursData> getHours() {
        if (jHoursDataDao == null) {
            jHoursDataDao = getDaoSession().getHoursDataDao();
        }
        return (ArrayList<HoursData>) jHoursDataDao
                .queryBuilder().build().list();
    }

    public void deleteHours() {
        try {
            if (jHoursDataDao == null) {
                jHoursDataDao = getDaoSession().getHoursDataDao();

            }
            jHoursDataDao.deleteAll();
        } catch (Exception e) {

        }
    }

}