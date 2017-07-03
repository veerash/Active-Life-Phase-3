package com.android.activelife.tampa.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.android.activelife.tampa.db.ScheduleDateData;
import com.android.activelife.tampa.db.ClassData;
import com.android.activelife.tampa.db.InstructorData;
import com.android.activelife.tampa.db.ScheduleData;
import com.android.activelife.tampa.db.HoursData;

import com.android.activelife.tampa.db.ScheduleDateDataDao;
import com.android.activelife.tampa.db.ClassDataDao;
import com.android.activelife.tampa.db.InstructorDataDao;
import com.android.activelife.tampa.db.ScheduleDataDao;
import com.android.activelife.tampa.db.HoursDataDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig scheduleDateDataDaoConfig;
    private final DaoConfig classDataDaoConfig;
    private final DaoConfig instructorDataDaoConfig;
    private final DaoConfig scheduleDataDaoConfig;
    private final DaoConfig hoursDataDaoConfig;

    private final ScheduleDateDataDao scheduleDateDataDao;
    private final ClassDataDao classDataDao;
    private final InstructorDataDao instructorDataDao;
    private final ScheduleDataDao scheduleDataDao;
    private final HoursDataDao hoursDataDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        scheduleDateDataDaoConfig = daoConfigMap.get(ScheduleDateDataDao.class).clone();
        scheduleDateDataDaoConfig.initIdentityScope(type);

        classDataDaoConfig = daoConfigMap.get(ClassDataDao.class).clone();
        classDataDaoConfig.initIdentityScope(type);

        instructorDataDaoConfig = daoConfigMap.get(InstructorDataDao.class).clone();
        instructorDataDaoConfig.initIdentityScope(type);

        scheduleDataDaoConfig = daoConfigMap.get(ScheduleDataDao.class).clone();
        scheduleDataDaoConfig.initIdentityScope(type);

        hoursDataDaoConfig = daoConfigMap.get(HoursDataDao.class).clone();
        hoursDataDaoConfig.initIdentityScope(type);

        scheduleDateDataDao = new ScheduleDateDataDao(scheduleDateDataDaoConfig, this);
        classDataDao = new ClassDataDao(classDataDaoConfig, this);
        instructorDataDao = new InstructorDataDao(instructorDataDaoConfig, this);
        scheduleDataDao = new ScheduleDataDao(scheduleDataDaoConfig, this);
        hoursDataDao = new HoursDataDao(hoursDataDaoConfig, this);

        registerDao(ScheduleDateData.class, scheduleDateDataDao);
        registerDao(ClassData.class, classDataDao);
        registerDao(InstructorData.class, instructorDataDao);
        registerDao(ScheduleData.class, scheduleDataDao);
        registerDao(HoursData.class, hoursDataDao);
    }
    
    public void clear() {
        scheduleDateDataDaoConfig.clearIdentityScope();
        classDataDaoConfig.clearIdentityScope();
        instructorDataDaoConfig.clearIdentityScope();
        scheduleDataDaoConfig.clearIdentityScope();
        hoursDataDaoConfig.clearIdentityScope();
    }

    public ScheduleDateDataDao getScheduleDateDataDao() {
        return scheduleDateDataDao;
    }

    public ClassDataDao getClassDataDao() {
        return classDataDao;
    }

    public InstructorDataDao getInstructorDataDao() {
        return instructorDataDao;
    }

    public ScheduleDataDao getScheduleDataDao() {
        return scheduleDataDao;
    }

    public HoursDataDao getHoursDataDao() {
        return hoursDataDao;
    }

}