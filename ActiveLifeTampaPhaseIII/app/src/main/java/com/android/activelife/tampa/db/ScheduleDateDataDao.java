package com.android.activelife.tampa.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SCHEDULE_DATE_DATA".
*/
public class ScheduleDateDataDao extends AbstractDao<ScheduleDateData, Long> {

    public static final String TABLENAME = "SCHEDULE_DATE_DATA";

    /**
     * Properties of entity ScheduleDateData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Schedule_id = new Property(1, int.class, "schedule_id", false, "SCHEDULE_ID");
        public final static Property Schedule_name = new Property(2, String.class, "schedule_name", false, "SCHEDULE_NAME");
        public final static Property Class_id = new Property(3, String.class, "class_id", false, "CLASS_ID");
        public final static Property Class_name = new Property(4, String.class, "class_name", false, "CLASS_NAME");
        public final static Property Schedule_type_id = new Property(5, String.class, "schedule_type_id", false, "SCHEDULE_TYPE_ID");
        public final static Property Schedule_type = new Property(6, String.class, "schedule_type", false, "SCHEDULE_TYPE");
        public final static Property Schedule_start_time = new Property(7, String.class, "schedule_start_time", false, "SCHEDULE_START_TIME");
        public final static Property Schedule_end_time = new Property(8, String.class, "schedule_end_time", false, "SCHEDULE_END_TIME");
        public final static Property Schedule_monday = new Property(9, Boolean.class, "schedule_monday", false, "SCHEDULE_MONDAY");
        public final static Property Schedule_tuesday = new Property(10, Boolean.class, "schedule_tuesday", false, "SCHEDULE_TUESDAY");
        public final static Property Schedule_wednesday = new Property(11, Boolean.class, "schedule_wednesday", false, "SCHEDULE_WEDNESDAY");
        public final static Property Schedule_thursday = new Property(12, Boolean.class, "schedule_thursday", false, "SCHEDULE_THURSDAY");
        public final static Property Schedule_friday = new Property(13, Boolean.class, "schedule_friday", false, "SCHEDULE_FRIDAY");
        public final static Property Schedule_saturday = new Property(14, Boolean.class, "schedule_saturday", false, "SCHEDULE_SATURDAY");
        public final static Property Schedule_sunday = new Property(15, Boolean.class, "schedule_sunday", false, "SCHEDULE_SUNDAY");
        public final static Property Schedule_frequency = new Property(16, String.class, "schedule_frequency", false, "SCHEDULE_FREQUENCY");
        public final static Property Is_cancelled = new Property(17, Boolean.class, "is_cancelled", false, "IS_CANCELLED");
        public final static Property Instructor_id = new Property(18, String.class, "instructor_id", false, "INSTRUCTOR_ID");
        public final static Property Instructor_name = new Property(19, String.class, "instructor_name", false, "INSTRUCTOR_NAME");
        public final static Property Location_id = new Property(20, String.class, "location_id", false, "LOCATION_ID");
        public final static Property Location_name = new Property(21, String.class, "location_name", false, "LOCATION_NAME");
    }


    public ScheduleDateDataDao(DaoConfig config) {
        super(config);
    }
    
    public ScheduleDateDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SCHEDULE_DATE_DATA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"SCHEDULE_ID\" INTEGER NOT NULL ," + // 1: schedule_id
                "\"SCHEDULE_NAME\" TEXT," + // 2: schedule_name
                "\"CLASS_ID\" TEXT," + // 3: class_id
                "\"CLASS_NAME\" TEXT," + // 4: class_name
                "\"SCHEDULE_TYPE_ID\" TEXT," + // 5: schedule_type_id
                "\"SCHEDULE_TYPE\" TEXT," + // 6: schedule_type
                "\"SCHEDULE_START_TIME\" TEXT," + // 7: schedule_start_time
                "\"SCHEDULE_END_TIME\" TEXT," + // 8: schedule_end_time
                "\"SCHEDULE_MONDAY\" INTEGER," + // 9: schedule_monday
                "\"SCHEDULE_TUESDAY\" INTEGER," + // 10: schedule_tuesday
                "\"SCHEDULE_WEDNESDAY\" INTEGER," + // 11: schedule_wednesday
                "\"SCHEDULE_THURSDAY\" INTEGER," + // 12: schedule_thursday
                "\"SCHEDULE_FRIDAY\" INTEGER," + // 13: schedule_friday
                "\"SCHEDULE_SATURDAY\" INTEGER," + // 14: schedule_saturday
                "\"SCHEDULE_SUNDAY\" INTEGER," + // 15: schedule_sunday
                "\"SCHEDULE_FREQUENCY\" TEXT," + // 16: schedule_frequency
                "\"IS_CANCELLED\" INTEGER," + // 17: is_cancelled
                "\"INSTRUCTOR_ID\" TEXT," + // 18: instructor_id
                "\"INSTRUCTOR_NAME\" TEXT," + // 19: instructor_name
                "\"LOCATION_ID\" TEXT," + // 20: location_id
                "\"LOCATION_NAME\" TEXT);"); // 21: location_name
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SCHEDULE_DATE_DATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ScheduleDateData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getSchedule_id());
 
        String schedule_name = entity.getSchedule_name();
        if (schedule_name != null) {
            stmt.bindString(3, schedule_name);
        }
 
        String class_id = entity.getClass_id();
        if (class_id != null) {
            stmt.bindString(4, class_id);
        }
 
        String class_name = entity.getClass_name();
        if (class_name != null) {
            stmt.bindString(5, class_name);
        }
 
        String schedule_type_id = entity.getSchedule_type_id();
        if (schedule_type_id != null) {
            stmt.bindString(6, schedule_type_id);
        }
 
        String schedule_type = entity.getSchedule_type();
        if (schedule_type != null) {
            stmt.bindString(7, schedule_type);
        }
 
        String schedule_start_time = entity.getSchedule_start_time();
        if (schedule_start_time != null) {
            stmt.bindString(8, schedule_start_time);
        }
 
        String schedule_end_time = entity.getSchedule_end_time();
        if (schedule_end_time != null) {
            stmt.bindString(9, schedule_end_time);
        }
 
        Boolean schedule_monday = entity.getSchedule_monday();
        if (schedule_monday != null) {
            stmt.bindLong(10, schedule_monday ? 1L: 0L);
        }
 
        Boolean schedule_tuesday = entity.getSchedule_tuesday();
        if (schedule_tuesday != null) {
            stmt.bindLong(11, schedule_tuesday ? 1L: 0L);
        }
 
        Boolean schedule_wednesday = entity.getSchedule_wednesday();
        if (schedule_wednesday != null) {
            stmt.bindLong(12, schedule_wednesday ? 1L: 0L);
        }
 
        Boolean schedule_thursday = entity.getSchedule_thursday();
        if (schedule_thursday != null) {
            stmt.bindLong(13, schedule_thursday ? 1L: 0L);
        }
 
        Boolean schedule_friday = entity.getSchedule_friday();
        if (schedule_friday != null) {
            stmt.bindLong(14, schedule_friday ? 1L: 0L);
        }
 
        Boolean schedule_saturday = entity.getSchedule_saturday();
        if (schedule_saturday != null) {
            stmt.bindLong(15, schedule_saturday ? 1L: 0L);
        }
 
        Boolean schedule_sunday = entity.getSchedule_sunday();
        if (schedule_sunday != null) {
            stmt.bindLong(16, schedule_sunday ? 1L: 0L);
        }
 
        String schedule_frequency = entity.getSchedule_frequency();
        if (schedule_frequency != null) {
            stmt.bindString(17, schedule_frequency);
        }
 
        Boolean is_cancelled = entity.getIs_cancelled();
        if (is_cancelled != null) {
            stmt.bindLong(18, is_cancelled ? 1L: 0L);
        }
 
        String instructor_id = entity.getInstructor_id();
        if (instructor_id != null) {
            stmt.bindString(19, instructor_id);
        }
 
        String instructor_name = entity.getInstructor_name();
        if (instructor_name != null) {
            stmt.bindString(20, instructor_name);
        }
 
        String location_id = entity.getLocation_id();
        if (location_id != null) {
            stmt.bindString(21, location_id);
        }
 
        String location_name = entity.getLocation_name();
        if (location_name != null) {
            stmt.bindString(22, location_name);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ScheduleDateData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getSchedule_id());
 
        String schedule_name = entity.getSchedule_name();
        if (schedule_name != null) {
            stmt.bindString(3, schedule_name);
        }
 
        String class_id = entity.getClass_id();
        if (class_id != null) {
            stmt.bindString(4, class_id);
        }
 
        String class_name = entity.getClass_name();
        if (class_name != null) {
            stmt.bindString(5, class_name);
        }
 
        String schedule_type_id = entity.getSchedule_type_id();
        if (schedule_type_id != null) {
            stmt.bindString(6, schedule_type_id);
        }
 
        String schedule_type = entity.getSchedule_type();
        if (schedule_type != null) {
            stmt.bindString(7, schedule_type);
        }
 
        String schedule_start_time = entity.getSchedule_start_time();
        if (schedule_start_time != null) {
            stmt.bindString(8, schedule_start_time);
        }
 
        String schedule_end_time = entity.getSchedule_end_time();
        if (schedule_end_time != null) {
            stmt.bindString(9, schedule_end_time);
        }
 
        Boolean schedule_monday = entity.getSchedule_monday();
        if (schedule_monday != null) {
            stmt.bindLong(10, schedule_monday ? 1L: 0L);
        }
 
        Boolean schedule_tuesday = entity.getSchedule_tuesday();
        if (schedule_tuesday != null) {
            stmt.bindLong(11, schedule_tuesday ? 1L: 0L);
        }
 
        Boolean schedule_wednesday = entity.getSchedule_wednesday();
        if (schedule_wednesday != null) {
            stmt.bindLong(12, schedule_wednesday ? 1L: 0L);
        }
 
        Boolean schedule_thursday = entity.getSchedule_thursday();
        if (schedule_thursday != null) {
            stmt.bindLong(13, schedule_thursday ? 1L: 0L);
        }
 
        Boolean schedule_friday = entity.getSchedule_friday();
        if (schedule_friday != null) {
            stmt.bindLong(14, schedule_friday ? 1L: 0L);
        }
 
        Boolean schedule_saturday = entity.getSchedule_saturday();
        if (schedule_saturday != null) {
            stmt.bindLong(15, schedule_saturday ? 1L: 0L);
        }
 
        Boolean schedule_sunday = entity.getSchedule_sunday();
        if (schedule_sunday != null) {
            stmt.bindLong(16, schedule_sunday ? 1L: 0L);
        }
 
        String schedule_frequency = entity.getSchedule_frequency();
        if (schedule_frequency != null) {
            stmt.bindString(17, schedule_frequency);
        }
 
        Boolean is_cancelled = entity.getIs_cancelled();
        if (is_cancelled != null) {
            stmt.bindLong(18, is_cancelled ? 1L: 0L);
        }
 
        String instructor_id = entity.getInstructor_id();
        if (instructor_id != null) {
            stmt.bindString(19, instructor_id);
        }
 
        String instructor_name = entity.getInstructor_name();
        if (instructor_name != null) {
            stmt.bindString(20, instructor_name);
        }
 
        String location_id = entity.getLocation_id();
        if (location_id != null) {
            stmt.bindString(21, location_id);
        }
 
        String location_name = entity.getLocation_name();
        if (location_name != null) {
            stmt.bindString(22, location_name);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ScheduleDateData readEntity(Cursor cursor, int offset) {
        ScheduleDateData entity = new ScheduleDateData( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // schedule_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // schedule_name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // class_id
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // class_name
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // schedule_type_id
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // schedule_type
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // schedule_start_time
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // schedule_end_time
            cursor.isNull(offset + 9) ? null : cursor.getShort(offset + 9) != 0, // schedule_monday
            cursor.isNull(offset + 10) ? null : cursor.getShort(offset + 10) != 0, // schedule_tuesday
            cursor.isNull(offset + 11) ? null : cursor.getShort(offset + 11) != 0, // schedule_wednesday
            cursor.isNull(offset + 12) ? null : cursor.getShort(offset + 12) != 0, // schedule_thursday
            cursor.isNull(offset + 13) ? null : cursor.getShort(offset + 13) != 0, // schedule_friday
            cursor.isNull(offset + 14) ? null : cursor.getShort(offset + 14) != 0, // schedule_saturday
            cursor.isNull(offset + 15) ? null : cursor.getShort(offset + 15) != 0, // schedule_sunday
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // schedule_frequency
            cursor.isNull(offset + 17) ? null : cursor.getShort(offset + 17) != 0, // is_cancelled
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // instructor_id
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // instructor_name
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // location_id
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21) // location_name
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ScheduleDateData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSchedule_id(cursor.getInt(offset + 1));
        entity.setSchedule_name(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setClass_id(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setClass_name(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSchedule_type_id(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setSchedule_type(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setSchedule_start_time(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setSchedule_end_time(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setSchedule_monday(cursor.isNull(offset + 9) ? null : cursor.getShort(offset + 9) != 0);
        entity.setSchedule_tuesday(cursor.isNull(offset + 10) ? null : cursor.getShort(offset + 10) != 0);
        entity.setSchedule_wednesday(cursor.isNull(offset + 11) ? null : cursor.getShort(offset + 11) != 0);
        entity.setSchedule_thursday(cursor.isNull(offset + 12) ? null : cursor.getShort(offset + 12) != 0);
        entity.setSchedule_friday(cursor.isNull(offset + 13) ? null : cursor.getShort(offset + 13) != 0);
        entity.setSchedule_saturday(cursor.isNull(offset + 14) ? null : cursor.getShort(offset + 14) != 0);
        entity.setSchedule_sunday(cursor.isNull(offset + 15) ? null : cursor.getShort(offset + 15) != 0);
        entity.setSchedule_frequency(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setIs_cancelled(cursor.isNull(offset + 17) ? null : cursor.getShort(offset + 17) != 0);
        entity.setInstructor_id(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setInstructor_name(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setLocation_id(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setLocation_name(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ScheduleDateData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ScheduleDateData entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ScheduleDateData entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
