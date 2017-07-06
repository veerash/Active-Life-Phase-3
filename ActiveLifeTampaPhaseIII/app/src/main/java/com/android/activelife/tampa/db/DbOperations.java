package com.android.activelife.tampa.db;

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

    public void insertSchedulesDate(int schedule_id, String schedule_name, String class_id, String class_name, String class_desc, String schedule_type_id, String schedule_type, String schedule_start_date, String schedule_start_time, String schedule_end_time, Long schedule_start_time_long, Long schedule_end_time_long, Boolean schedule_monday, Boolean schedule_tuesday, Boolean schedule_wednesday, Boolean schedule_thursday, Boolean schedule_friday, Boolean schedule_saturday, Boolean schedule_sunday, String schedule_frequency, Boolean is_cancelled, String instructor_id, String instructor_name, String location_id, String location_name) {
        try {
            if (jScheduleDateDataDao == null) {
                jScheduleDateDataDao = getDaoSession().getScheduleDateDataDao();

            }
            jScheduleDateData = new ScheduleDateData(null, schedule_id, schedule_name, class_id, class_name, class_desc, schedule_type_id, schedule_type, schedule_start_date, schedule_start_time, schedule_end_time, schedule_start_time_long, schedule_end_time_long, schedule_monday, schedule_tuesday, schedule_wednesday, schedule_thursday, schedule_friday, schedule_saturday, schedule_sunday, schedule_frequency, is_cancelled, instructor_id, instructor_name, location_id, location_name);
            jScheduleDateDataDao.insert(jScheduleDateData);
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

    public List<ScheduleDateData> getScheduleDateOfId(String schedule_id, String class_id, String instructor_id, Long starttime, Long endtime) {
        try {
            if (jScheduleDateDataDao == null) {
                jScheduleDateDataDao = getDaoSession().getScheduleDateDataDao();

            }
            QueryBuilder<ScheduleDateData> qb = jScheduleDateDataDao.queryBuilder();
            List<ScheduleDateData> list = new ArrayList<>();
            if (schedule_id != null & class_id != null && instructor_id != null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            }
            // four dimens
            else if (schedule_id != null & class_id != null && instructor_id != null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime)));
            } else if (schedule_id != null & class_id != null && instructor_id != null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (schedule_id != null & class_id != null && instructor_id == null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (schedule_id != null & class_id == null && instructor_id != null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (schedule_id == null & class_id != null && instructor_id != null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Class_id.eq(class_id),
                        qb.and(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            }
            //Three dimens
            else if (schedule_id != null & class_id != null && instructor_id != null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id)));
            } else if (schedule_id != null & class_id != null && instructor_id == null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (schedule_id != null & class_id != null && instructor_id == null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Class_id.eq(class_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime)));
            } else if (schedule_id != null & class_id == null && instructor_id != null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime)));
            } else if (schedule_id != null & class_id == null && instructor_id != null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (schedule_id != null & class_id == null && instructor_id == null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_id.eq(schedule_id),
                        qb.and(ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (schedule_id == null & class_id == null && instructor_id != null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id),
                        qb.and(ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (schedule_id == null & class_id != null && instructor_id == null && starttime != null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Class_id.eq(class_id),
                        qb.and(ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (schedule_id == null & class_id != null && instructor_id != null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Class_id.eq(class_id),
                        qb.and(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime)));
            } else if (schedule_id == null & class_id != null && instructor_id != null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Class_id.eq(class_id),
                        qb.and(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id), ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime)));
            }
            //two dimens
            else if (schedule_id != null & class_id != null && instructor_id == null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_id.eq(schedule_id),
                        ScheduleDateDataDao.Properties.Class_id.eq(class_id));
            } else if (schedule_id != null & class_id == null && instructor_id != null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_id.eq(schedule_id),
                        ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id));
            } else if (schedule_id != null & class_id == null && instructor_id == null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_id.eq(schedule_id),
                        ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime));
            } else if (schedule_id != null & class_id == null && instructor_id == null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_id.eq(schedule_id),
                        ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime));
            } else if (schedule_id == null & class_id != null && instructor_id != null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Class_id.eq(class_id),
                        ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id));
            } else if (schedule_id == null & class_id != null && instructor_id == null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Class_id.eq(class_id),
                        ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime));
            } else if (schedule_id == null & class_id != null && instructor_id == null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Class_id.eq(class_id),
                        ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime));
            } else if (schedule_id == null & class_id == null && instructor_id != null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id),
                        ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime));
            } else if (schedule_id == null & class_id == null && instructor_id != null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id),
                        ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime));
            } else if (schedule_id == null & class_id == null && instructor_id != null && starttime != null && endtime == null) {
                qb.where(
                        ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime), ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime));
            }

            //one dimens
            else if (schedule_id != null & class_id == null && instructor_id == null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_id.eq(schedule_id));
            } else if (schedule_id == null & class_id != null && instructor_id == null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Class_id.eq(class_id));
            } else if (schedule_id == null & class_id == null && instructor_id != null && starttime == null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Instructor_id.eq(instructor_id));
            } else if (schedule_id == null & class_id == null && instructor_id == null && starttime != null && endtime == null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_start_time_long.ge(starttime));
            } else if (schedule_id == null & class_id == null && instructor_id == null && starttime == null && endtime != null) {
                qb.where(ScheduleDateDataDao.Properties.Schedule_end_time_long.le(endtime));
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
