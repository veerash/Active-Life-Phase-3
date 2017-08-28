package com.android.activelife.db;

import android.content.Context;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

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
    private LocationsDataDao jLocationsDataDao;
    private LocationsData jLocationsData;
    private LocationDataDao jLocationDataDao;
    private LocationData jLocationData;
    private DefaultLocationDataDao jDefaultLocationDataDao;
    private DefaultLocationData jDefaultLocationData;
    private MemberDataDao jMemberDataDao;
    private MemberData jMemberData;
    private MessagesDataDao jMessagesDataDao;
    private MessagesData jMessagesData;
    private MessageLocationDataDao jMessageLocationDataDao;
    private MessageLocationData jMessageLocationData;

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
            jLocationsDataDao = daoSession.getLocationsDataDao();
            jDefaultLocationDataDao = daoSession.getDefaultLocationDataDao();
            jLocationDataDao = daoSession.getLocationDataDao();
            jMemberDataDao = daoSession.getMemberDataDao();
            jMessagesDataDao = daoSession.getMessagesDataDao();
            jMessageLocationDataDao = daoSession.getMessageLocationDataDao();
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

    public void insertSchedulesDate(int schedule_id, String schedule_name, String class_id, String class_name, String class_desc, String schedule_type_id, String schedule_type, String schedule_start_date, String schedule_start_time, String schedule_end_time, Long schedule_start_time_long, Long schedule_end_time_long, int schedule_monday, int schedule_tuesday, int schedule_wednesday, int schedule_thursday, int schedule_friday, int schedule_saturday, int schedule_sunday, String schedule_frequency, int is_cancelled, int is_reservable, String instructor_id, String instructor_name, String location_id, String location_name) {
        try {
            if (jScheduleDateDataDao == null) {
                jScheduleDateDataDao = getDaoSession().getScheduleDateDataDao();

            }
            jScheduleDateData = new ScheduleDateData(null, schedule_id, schedule_name, class_id, class_name, class_desc, schedule_type_id, schedule_type, schedule_start_date, schedule_start_time, schedule_end_time, schedule_start_time_long, schedule_end_time_long, schedule_monday, schedule_tuesday, schedule_wednesday, schedule_thursday, schedule_friday, schedule_saturday, schedule_sunday, schedule_frequency, is_cancelled, is_reservable, instructor_id, instructor_name, location_id, location_name);
            jScheduleDateDataDao.insert(jScheduleDateData);
        } catch (Exception e) {

        }

    }

    public void insertSchedulesDateList(List<ScheduleDateData> list) {
        try {
            if (jScheduleDateDataDao == null) {
                jScheduleDateDataDao = getDaoSession().getScheduleDateDataDao();

            }
            jScheduleDateDataDao.insertInTx(list);
        } catch (Exception e) {

        }
    }

    public ArrayList<ScheduleDateData> getScheduleDate() {
        if (jScheduleDateDataDao == null) {
            jScheduleDateDataDao = getDaoSession().getScheduleDateDataDao();

        }
        return (ArrayList<ScheduleDateData>) jScheduleDateDataDao
                .queryBuilder().build().list();
    }

    public List<ScheduleDateData> getScheduleDateOfId(String location_id, String schedule_id, String class_id, String instructor_id, Long starttime, Long endtime) {
        try {
            if (jScheduleDateDataDao == null) {
                jScheduleDateDataDao = getDaoSession().getScheduleDateDataDao();

            }
            QueryBuilder<ScheduleDateData> qb = jScheduleDateDataDao.queryBuilder();
            List<ScheduleDateData> list = new ArrayList<>();
            if (location_id != null && schedule_id != null && class_id != null && instructor_id != null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Location_id.eq(location_id), ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            }
            // five dimens
            else if (location_id != null && schedule_id != null && class_id != null && instructor_id != null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Location_id.eq(location_id), ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime)));
            } else if (location_id != null && schedule_id != null && class_id != null && instructor_id != null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Location_id.eq(location_id), ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id != null && schedule_id != null && class_id != null && instructor_id == null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Location_id.eq(location_id), ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id != null && schedule_id != null && class_id == null && instructor_id != null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Location_id.eq(location_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id != null && schedule_id == null && class_id != null && instructor_id != null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Location_id.eq(location_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id == null && schedule_id != null && class_id != null && instructor_id != null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            }
            // four dimens
            else if (location_id != null && schedule_id != null && class_id != null && instructor_id != null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Location_id.eq(location_id), ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id)));
            } else if (location_id != null && schedule_id != null && class_id != null && instructor_id == null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Location_id.eq(location_id), ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime)));
            } else if (location_id != null && schedule_id != null && class_id == null && instructor_id != null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Location_id.eq(location_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime)));
            } else if (location_id != null && schedule_id == null && class_id != null && instructor_id != null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Location_id.eq(location_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime)));
            } else if (location_id == null && schedule_id != null && class_id != null && instructor_id != null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime)));
            } else if (location_id != null && schedule_id != null && class_id != null && instructor_id == null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Location_id.eq(location_id), ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id != null && schedule_id != null && class_id == null && instructor_id != null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Location_id.eq(location_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id != null && schedule_id == null && class_id != null && instructor_id != null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Location_id.eq(location_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id == null && schedule_id != null && class_id != null && instructor_id != null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id != null && schedule_id != null && class_id == null && instructor_id == null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Location_id.eq(location_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id != null && schedule_id == null && class_id != null && instructor_id == null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Location_id.eq(location_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id == null && schedule_id != null && class_id != null && instructor_id == null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id != null && schedule_id == null && class_id == null && instructor_id != null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Location_id.eq(location_id),
                        qb.and(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id == null && schedule_id != null && class_id == null && instructor_id != null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id == null && schedule_id == null && class_id != null && instructor_id != null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Class_id.eq(class_id),
                        qb.and(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            }
            //three dimens
            else if (location_id != null && schedule_id != null && class_id != null && instructor_id == null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Location_id.eq(location_id), ScheduleDateDataDao.Properties.Class_id.eq(class_id)));
            } else if (location_id != null && schedule_id != null && class_id == null && instructor_id == null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Location_id.eq(location_id), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id != null && schedule_id == null && class_id == null && instructor_id == null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Location_id.eq(location_id),
                        qb.and(ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id == null && schedule_id == null && class_id == null && instructor_id != null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id),
                        qb.and(ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id != null && schedule_id != null && class_id == null && instructor_id == null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Location_id.eq(location_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime)));
            } else if (location_id != null && schedule_id == null && class_id != null && instructor_id != null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Location_id.eq(location_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id)));
            } else if (location_id != null && schedule_id != null && class_id == null && instructor_id != null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Location_id.eq(location_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id)));
            } else if (location_id != null && schedule_id == null && class_id != null && instructor_id == null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Location_id.eq(location_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime)));
            } else if (location_id != null && schedule_id == null && class_id != null && instructor_id == null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Location_id.eq(location_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id != null && schedule_id == null && class_id == null && instructor_id != null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Location_id.eq(location_id),
                        qb.and(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime)));
            } else if (location_id != null && schedule_id == null && class_id == null && instructor_id != null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Location_id.eq(location_id),
                        qb.and(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id == null && schedule_id != null && class_id != null && instructor_id == null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime)));
            } else if (location_id == null && schedule_id != null && class_id == null && instructor_id != null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime)));
            } else if (location_id == null && schedule_id != null && class_id == null && instructor_id == null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id == null && schedule_id != null && class_id != null && instructor_id != null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id)));
            } else if (location_id == null && schedule_id != null && class_id != null && instructor_id == null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime)));
            } else if (location_id == null && schedule_id != null && class_id == null && instructor_id != null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (location_id == null && schedule_id == null && class_id != null && instructor_id != null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Class_id.eq(class_id),
                        qb.and(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime)));
            } else if (location_id == null && schedule_id == null && class_id != null && instructor_id != null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Class_id.eq(class_id),
                        qb.and(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            }
            // two dimens
            else if (location_id != null && schedule_id != null && class_id == null && instructor_id == null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id), ScheduleDateDataDao.Properties.Location_id.eq(location_id));
            } else if (location_id != null && schedule_id == null && class_id == null && instructor_id == null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Location_id.eq(location_id), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime));
            } else if (location_id != null && schedule_id == null && class_id == null && instructor_id == null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Location_id.eq(location_id), ScheduleDateDataDao.Properties.Schedule_start_time.ge(starttime));
            } else if (location_id != null && schedule_id == null && class_id == null && instructor_id != null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Location_id.eq(location_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id));
            } else if (location_id != null && schedule_id == null && class_id != null && instructor_id == null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Location_id.eq(location_id), ScheduleDateDataDao.Properties.Class_id.eq(class_id));
            } else if (location_id == null && schedule_id != null && class_id != null && instructor_id == null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id), ScheduleDateDataDao.Properties.Class_id.eq(class_id));
            } else if (location_id == null && schedule_id != null && class_id == null && instructor_id != null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id));
            } else if (location_id == null && schedule_id == null && class_id == null && instructor_id == null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime));
            } else if (location_id == null && schedule_id == null && class_id == null && instructor_id == null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id), ScheduleDateDataDao.Properties.Schedule_start_time.ge(starttime));
            } else if (location_id == null && schedule_id == null && class_id != null && instructor_id != null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id));
            } else if (location_id == null && schedule_id == null && class_id != null && instructor_id == null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime));
            } else if (location_id == null && schedule_id == null && class_id != null && instructor_id == null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime));
            } else if (location_id == null && schedule_id == null && class_id == null && instructor_id != null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime));
            } else if (location_id == null && schedule_id == null && class_id == null && instructor_id != null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime));
            } else if (location_id == null && schedule_id == null && class_id == null && instructor_id == null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime));
            }
            //one dimens
            else if (location_id == null && schedule_id == null && class_id == null && instructor_id == null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime));
            } else if (location_id == null && schedule_id == null && class_id == null && instructor_id == null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime));
            } else if (location_id == null && schedule_id == null && class_id == null && instructor_id != null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id));
            } else if (location_id == null && schedule_id == null && class_id != null && instructor_id == null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Class_id.eq(class_id));
            } else if (location_id == null && schedule_id != null && class_id == null && instructor_id == null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_type_id.eq(schedule_id));
            } else if (location_id != null && schedule_id == null && class_id == null && instructor_id == null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Location_id.eq(location_id));
            }
            list = qb.list();
            if (list != null && list.size() > 0) {
                return list;

            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

    public ScheduleDateData getScheduleDateOfId(String schedule_id) {
        try {
            if (jScheduleDateDataDao == null) {
                jScheduleDateDataDao = getDaoSession().getScheduleDateDataDao();

            }
            List<ScheduleDateData> list = jScheduleDateDataDao
                    .queryBuilder()
                    .where(ScheduleDateDataDao.Properties.Schedule_id
                            .eq(schedule_id)).build().list();
            if (list != null && list.size() > 0) {
                return list.get(0);

            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

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

    public void insertLocations(
            String location_id, String location_name, String location_address, String location_city, String location_state, String location_zip, String location_phone, String location_email, String location_program_link, String location_donate_link) {
        try {
            if (jLocationsDataDao == null) {
                jLocationsDataDao = getDaoSession().getLocationsDataDao();

            }
            jLocationsData = new LocationsData(null, location_id, location_name, location_address, location_city, location_state, location_zip, location_phone, location_email, location_program_link, location_donate_link);
            jLocationsDataDao.insert(jLocationsData);
        } catch (Exception e) {

        }

    }

    public void insertLocations(ArrayList<LocationsData> data) {
        try {
            if (jLocationsDataDao == null) {
                jLocationsDataDao = getDaoSession().getLocationsDataDao();

            }
            jLocationsDataDao.insertInTx(data);
        } catch (Exception e) {

        }

    }

    public ArrayList<LocationsData> getLocations() {
        if (jLocationsDataDao == null) {
            jLocationsDataDao = getDaoSession().getLocationsDataDao();

        }
        return (ArrayList<LocationsData>) jLocationsDataDao
                .queryBuilder().build().list();
    }

    public LocationsData getLocationById(String location_id) {
        if (jLocationsDataDao == null) {
            jLocationsDataDao = getDaoSession().getLocationsDataDao();

        }
        QueryBuilder<LocationsData> qb = jLocationsDataDao.queryBuilder();
        qb.where(LocationsDataDao.Properties.Location_id
                .eq(location_id));
        return qb.build().list().get(0);
    }

    public void deleteLocations() {
        try {
            if (jLocationsDataDao == null) {
                jLocationsDataDao = getDaoSession().getLocationsDataDao();

            }
            jLocationsDataDao.deleteAll();
        } catch (Exception e) {

        }
    }

    public void insertLocation(int position, String location_id, String location_name, String location_address, String location_city, String location_state, String location_zip, String location_phone, String location_email, String location_program_link, String location_donate_link) {
        try {
            if (jLocationDataDao == null) {
                jLocationDataDao = getDaoSession().getLocationDataDao();

            }
            jLocationData = new LocationData(null, position, location_id, location_name, location_address, location_city, location_state, location_zip, location_phone, location_email, location_program_link, location_donate_link);
            jLocationDataDao.insert(jLocationData);
        } catch (Exception e) {

        }

    }

    public ArrayList<LocationData> getLocation() {
        if (jLocationDataDao == null) {
            jLocationDataDao = getDaoSession().getLocationDataDao();

        }
        return (ArrayList<LocationData>) jLocationDataDao
                .queryBuilder().build().list();
    }

    public void deleteLocation() {
        try {
            if (jLocationDataDao == null) {
                jLocationDataDao = getDaoSession().getLocationDataDao();

            }
            jLocationDataDao.deleteAll();
        } catch (Exception e) {

        }
    }

    public void insertDefaultLocation(int position, String location_id, String location_name, String location_address, String location_city, String location_state, String location_zip, String location_phone, String location_email, String location_program_link, String location_donate_link) {
        try {
            if (jDefaultLocationDataDao == null) {
                jDefaultLocationDataDao = getDaoSession().getDefaultLocationDataDao();

            }
            jDefaultLocationData = new DefaultLocationData(null, position, location_id, location_name, location_address, location_city, location_state, location_zip, location_phone, location_email, location_program_link, location_donate_link);
            jDefaultLocationDataDao.insert(jDefaultLocationData);
        } catch (Exception e) {

        }

    }

    public ArrayList<DefaultLocationData> getDefaultLocations() {
        if (jDefaultLocationDataDao == null) {
            jDefaultLocationDataDao = getDaoSession().getDefaultLocationDataDao();

        }
        return (ArrayList<DefaultLocationData>) jDefaultLocationDataDao
                .queryBuilder().build().list();
    }

    public void deleteDefaultLocations() {
        try {
            if (jDefaultLocationDataDao == null) {
                jDefaultLocationDataDao = getDaoSession().getDefaultLocationDataDao();

            }
            jDefaultLocationDataDao.deleteAll();
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

    public void insertMessageDao(String message_id, String message_title, String message_msg, String message_send_at) {
        try {
            if (jMessagesDataDao == null) {
                jMessagesDataDao = getDaoSession().getMessagesDataDao();
            }
            jMessagesData = new MessagesData(null, message_id, message_title, message_msg, message_send_at);
            jMessagesDataDao.insert(jMessagesData);
        } catch (Exception e) {

        }

    }

    public void insertMessagesList(List<MessagesData> messages) {
        try {
            if (jMessagesDataDao == null) {
                jMessagesDataDao = getDaoSession().getMessagesDataDao();
            }
            jMessagesDataDao.insertInTx(messages);
        } catch (Exception e) {

        }

    }


    public ArrayList<MessagesData> getMessages() {
        if (jMessagesDataDao == null) {
            jMessagesDataDao = getDaoSession().getMessagesDataDao();
        }
        return (ArrayList<MessagesData>) jMessagesDataDao
                .queryBuilder().build().list();
    }

    public MessagesData getMessagesById(String message_id) {
        if (jMessagesDataDao == null) {
            jMessagesDataDao = getDaoSession().getMessagesDataDao();
        }
        QueryBuilder<MessagesData> qb = jMessagesDataDao.queryBuilder();
        qb.where(MessagesDataDao.Properties.Message_id.eq(message_id));
        return qb.build().list().get(0);
    }

    public void deleteMessages() {
        try {
            if (jMessagesDataDao == null) {
                jMessagesDataDao = getDaoSession().getMessagesDataDao();
            }
            jMessagesDataDao.deleteAll();
        } catch (Exception e) {

        }
    }

    public void insertMessageLocation(String message_id, String message_title, String message_msg, String message_send_at, String location_id, String location_name, String location_address, String location_city, String location_state, String location_zip, String location_phone, String location_email) {

        try {
            if (jMessageLocationDataDao == null) {
                jMessageLocationDataDao = getDaoSession().getMessageLocationDataDao();
            }
            jMessageLocationData = new MessageLocationData(null, message_id, message_title, message_msg, message_send_at, location_id, location_name, location_address, location_city, location_state, location_zip, location_phone, location_email);
            jMessageLocationDataDao.insert(jMessageLocationData);
        } catch (Exception e) {

        }

    }

    public void insertMessageLocationList(List<MessageLocationData> messages) {
        try {
            if (jMessageLocationDataDao == null) {
                jMessageLocationDataDao = getDaoSession().getMessageLocationDataDao();
            }
            jMessageLocationDataDao.insertInTx(messages);
        } catch (Exception e) {

        }

    }

    public ArrayList<MessageLocationData> getMessageLocations() {
        if (jMessageLocationDataDao == null) {
            jMessageLocationDataDao = getDaoSession().getMessageLocationDataDao();
        }
        return (ArrayList<MessageLocationData>) jMessageLocationDataDao
                .queryBuilder().build().list();
    }

    public ArrayList<MessageLocationData> getMessageLocationsById(String location_id) {
        if (jMessageLocationDataDao == null) {
            jMessageLocationDataDao = getDaoSession().getMessageLocationDataDao();
        }
        QueryBuilder<MessageLocationData> qb = jMessageLocationDataDao.queryBuilder();
        qb.where(MessageLocationDataDao.Properties.Location_id.eq(location_id));
        return (ArrayList<MessageLocationData>) qb.build().list();
    }

    public void deleteMessageLocations() {
        try {
            if (jMessageLocationDataDao == null) {
                jMessageLocationDataDao = getDaoSession().getMessageLocationDataDao();
            }
            jMessageLocationDataDao.deleteAll();
        } catch (Exception e) {

        }
    }

    public void insertOrReplaceMember(String member_id, String member_name) {

        try {
            if (jMemberDataDao == null) {
                jMemberDataDao = getDaoSession().getMemberDataDao();
            }
            jMemberData = new MemberData(member_id, member_name);
            jMemberDataDao.insertOrReplaceInTx(jMemberData);
        } catch (Exception e) {

        }

    }
    public void updateMember(String oldMemberid,String member_id, String member_name) {

        try {
            if (jMemberDataDao == null) {
                jMemberDataDao = getDaoSession().getMemberDataDao();
            }
//            jMemberData = new MemberData(member_id, member_name);
//            jMemberDataDao.insertOrReplaceInTx(jMemberData);
            deleteMemberDataById(oldMemberid);
            insertOrReplaceMember(member_id,member_name);
        } catch (Exception e) {

        }

    }
    public ArrayList<MemberData> getMembers() {
        if (jMemberDataDao == null) {
            jMemberDataDao = getDaoSession().getMemberDataDao();
        }
        return (ArrayList<MemberData>) jMemberDataDao
                .queryBuilder().build().list();
    }

    public void deleteMemberDataById(String member_id) {
        try {
            if (jMemberDataDao == null) {
                jMemberDataDao = getDaoSession().getMemberDataDao();
            }
            jMemberDataDao
                    .queryBuilder()
                    .where(MemberDataDao.Properties.Member_id
                            .eq(member_id)).buildDelete()
                    .executeDeleteWithoutDetachingEntities();
        } catch (Exception e) {

        }
    }

    public void deleteMembers() {
        try {
            if (jMemberDataDao == null) {
                jMemberDataDao = getDaoSession().getMemberDataDao();
            }
            jMemberDataDao.deleteAll();
        } catch (Exception e) {

        }
    }

}
