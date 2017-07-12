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
        public final static Property Class_desc = new Property(5, String.class, "class_desc", false, "CLASS_DESC");
        public final static Property Schedule_type_id = new Property(6, String.class, "schedule_type_id", false, "SCHEDULE_TYPE_ID");
        public final static Property Schedule_type = new Property(7, String.class, "schedule_type", false, "SCHEDULE_TYPE");
        public final static Property Schedule_start_date = new Property(8, String.class, "schedule_start_date", false, "SCHEDULE_START_DATE");
        public final static Property Schedule_start_time = new Property(9, String.class, "schedule_start_time", false, "SCHEDULE_START_TIME");
        public final static Property Schedule_end_time = new Property(10, String.class, "schedule_end_time", false, "SCHEDULE_END_TIME");
        public final static Property Schedule_start_time_long = new Property(11, Long.class, "schedule_start_time_long", false, "SCHEDULE_START_TIME_LONG");
        public final static Property Schedule_end_time_long = new Property(12, Long.class, "schedule_end_time_long", false, "SCHEDULE_END_TIME_LONG");
        public final static Property Schedule_monday = new Property(13, Integer.class, "schedule_monday", false, "SCHEDULE_MONDAY");
        public final static Property Schedule_tuesday = new Property(14, Integer.class, "schedule_tuesday", false, "SCHEDULE_TUESDAY");
        public final static Property Schedule_wednesday = new Property(15, Integer.class, "schedule_wednesday", false, "SCHEDULE_WEDNESDAY");
        public final static Property Schedule_thursday = new Property(16, Integer.class, "schedule_thursday", false, "SCHEDULE_THURSDAY");
        public final static Property Schedule_friday = new Property(17, Integer.class, "schedule_friday", false, "SCHEDULE_FRIDAY");
        public final static Property Schedule_saturday = new Property(18, Integer.class, "schedule_saturday", false, "SCHEDULE_SATURDAY");
        public final static Property Schedule_sunday = new Property(19, Integer.class, "schedule_sunday", false, "SCHEDULE_SUNDAY");
        public final static Property Schedule_frequency = new Property(20, String.class, "schedule_frequency", false, "SCHEDULE_FREQUENCY");
        public final static Property Is_cancelled = new Property(21, Integer.class, "is_cancelled", false, "IS_CANCELLED");
        public final static Property Is_reservable = new Property(22, Integer.class, "is_reservable", false, "IS_RESERVABLE");
        public final static Property Instructor_id = new Property(23, String.class, "instructor_id", false, "INSTRUCTOR_ID");
        public final static Property Instructor_name = new Property(24, String.class, "instructor_name", false, "INSTRUCTOR_NAME");
        public final static Property Location_id = new Property(25, String.class, "location_id", false, "LOCATION_ID");
        public final static Property Location_name = new Property(26, String.class, "location_name", false, "LOCATION_NAME");
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
                "\"CLASS_DESC\" TEXT," + // 5: class_desc
                "\"SCHEDULE_TYPE_ID\" TEXT," + // 6: schedule_type_id
                "\"SCHEDULE_TYPE\" TEXT," + // 7: schedule_type
                "\"SCHEDULE_START_DATE\" TEXT," + // 8: schedule_start_date
                "\"SCHEDULE_START_TIME\" TEXT," + // 9: schedule_start_time
                "\"SCHEDULE_END_TIME\" TEXT," + // 10: schedule_end_time
                "\"SCHEDULE_START_TIME_LONG\" INTEGER," + // 11: schedule_start_time_long
                "\"SCHEDULE_END_TIME_LONG\" INTEGER," + // 12: schedule_end_time_long
                "\"SCHEDULE_MONDAY\" INTEGER," + // 13: schedule_monday
                "\"SCHEDULE_TUESDAY\" INTEGER," + // 14: schedule_tuesday
                "\"SCHEDULE_WEDNESDAY\" INTEGER," + // 15: schedule_wednesday
                "\"SCHEDULE_THURSDAY\" INTEGER," + // 16: schedule_thursday
                "\"SCHEDULE_FRIDAY\" INTEGER," + // 17: schedule_friday
                "\"SCHEDULE_SATURDAY\" INTEGER," + // 18: schedule_saturday
                "\"SCHEDULE_SUNDAY\" INTEGER," + // 19: schedule_sunday
                "\"SCHEDULE_FREQUENCY\" TEXT," + // 20: schedule_frequency
                "\"IS_CANCELLED\" INTEGER," + // 21: is_cancelled
                "\"IS_RESERVABLE\" INTEGER," + // 22: is_reservable
                "\"INSTRUCTOR_ID\" TEXT," + // 23: instructor_id
                "\"INSTRUCTOR_NAME\" TEXT," + // 24: instructor_name
                "\"LOCATION_ID\" TEXT," + // 25: location_id
                "\"LOCATION_NAME\" TEXT);"); // 26: location_name
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
 
        String class_desc = entity.getClass_desc();
        if (class_desc != null) {
            stmt.bindString(6, class_desc);
        }
 
        String schedule_type_id = entity.getSchedule_type_id();
        if (schedule_type_id != null) {
            stmt.bindString(7, schedule_type_id);
        }
 
        String schedule_type = entity.getSchedule_type();
        if (schedule_type != null) {
            stmt.bindString(8, schedule_type);
        }
 
        String schedule_start_date = entity.getSchedule_start_date();
        if (schedule_start_date != null) {
            stmt.bindString(9, schedule_start_date);
        }
 
        String schedule_start_time = entity.getSchedule_start_time();
        if (schedule_start_time != null) {
            stmt.bindString(10, schedule_start_time);
        }
 
        String schedule_end_time = entity.getSchedule_end_time();
        if (schedule_end_time != null) {
            stmt.bindString(11, schedule_end_time);
        }
 
        Long schedule_start_time_long = entity.getSchedule_start_time_long();
        if (schedule_start_time_long != null) {
            stmt.bindLong(12, schedule_start_time_long);
        }
 
        Long schedule_end_time_long = entity.getSchedule_end_time_long();
        if (schedule_end_time_long != null) {
            stmt.bindLong(13, schedule_end_time_long);
        }
 
        Integer schedule_monday = entity.getSchedule_monday();
        if (schedule_monday != null) {
            stmt.bindLong(14, schedule_monday);
        }
 
        Integer schedule_tuesday = entity.getSchedule_tuesday();
        if (schedule_tuesday != null) {
            stmt.bindLong(15, schedule_tuesday);
        }
 
        Integer schedule_wednesday = entity.getSchedule_wednesday();
        if (schedule_wednesday != null) {
            stmt.bindLong(16, schedule_wednesday);
        }
 
        Integer schedule_thursday = entity.getSchedule_thursday();
        if (schedule_thursday != null) {
            stmt.bindLong(17, schedule_thursday);
        }
 
        Integer schedule_friday = entity.getSchedule_friday();
        if (schedule_friday != null) {
            stmt.bindLong(18, schedule_friday);
        }
 
        Integer schedule_saturday = entity.getSchedule_saturday();
        if (schedule_saturday != null) {
            stmt.bindLong(19, schedule_saturday);
        }
 
        Integer schedule_sunday = entity.getSchedule_sunday();
        if (schedule_sunday != null) {
            stmt.bindLong(20, schedule_sunday);
        }
 
        String schedule_frequency = entity.getSchedule_frequency();
        if (schedule_frequency != null) {
            stmt.bindString(21, schedule_frequency);
        }
 
        Integer is_cancelled = entity.getIs_cancelled();
        if (is_cancelled != null) {
            stmt.bindLong(22, is_cancelled);
        }
 
        Integer is_reservable = entity.getIs_reservable();
        if (is_reservable != null) {
            stmt.bindLong(23, is_reservable);
        }
 
        String instructor_id = entity.getInstructor_id();
        if (instructor_id != null) {
            stmt.bindString(24, instructor_id);
        }
 
        String instructor_name = entity.getInstructor_name();
        if (instructor_name != null) {
            stmt.bindString(25, instructor_name);
        }
 
        String location_id = entity.getLocation_id();
        if (location_id != null) {
            stmt.bindString(26, location_id);
        }
 
        String location_name = entity.getLocation_name();
        if (location_name != null) {
            stmt.bindString(27, location_name);
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
 
        String class_desc = entity.getClass_desc();
        if (class_desc != null) {
            stmt.bindString(6, class_desc);
        }
 
        String schedule_type_id = entity.getSchedule_type_id();
        if (schedule_type_id != null) {
            stmt.bindString(7, schedule_type_id);
        }
 
        String schedule_type = entity.getSchedule_type();
        if (schedule_type != null) {
            stmt.bindString(8, schedule_type);
        }
 
        String schedule_start_date = entity.getSchedule_start_date();
        if (schedule_start_date != null) {
            stmt.bindString(9, schedule_start_date);
        }
 
        String schedule_start_time = entity.getSchedule_start_time();
        if (schedule_start_time != null) {
            stmt.bindString(10, schedule_start_time);
        }
 
        String schedule_end_time = entity.getSchedule_end_time();
        if (schedule_end_time != null) {
            stmt.bindString(11, schedule_end_time);
        }
 
        Long schedule_start_time_long = entity.getSchedule_start_time_long();
        if (schedule_start_time_long != null) {
            stmt.bindLong(12, schedule_start_time_long);
        }
 
        Long schedule_end_time_long = entity.getSchedule_end_time_long();
        if (schedule_end_time_long != null) {
            stmt.bindLong(13, schedule_end_time_long);
        }
 
        Integer schedule_monday = entity.getSchedule_monday();
        if (schedule_monday != null) {
            stmt.bindLong(14, schedule_monday);
        }
 
        Integer schedule_tuesday = entity.getSchedule_tuesday();
        if (schedule_tuesday != null) {
            stmt.bindLong(15, schedule_tuesday);
        }
 
        Integer schedule_wednesday = entity.getSchedule_wednesday();
        if (schedule_wednesday != null) {
            stmt.bindLong(16, schedule_wednesday);
        }
 
        Integer schedule_thursday = entity.getSchedule_thursday();
        if (schedule_thursday != null) {
            stmt.bindLong(17, schedule_thursday);
        }
 
        Integer schedule_friday = entity.getSchedule_friday();
        if (schedule_friday != null) {
            stmt.bindLong(18, schedule_friday);
        }
 
        Integer schedule_saturday = entity.getSchedule_saturday();
        if (schedule_saturday != null) {
            stmt.bindLong(19, schedule_saturday);
        }
 
        Integer schedule_sunday = entity.getSchedule_sunday();
        if (schedule_sunday != null) {
            stmt.bindLong(20, schedule_sunday);
        }
 
        String schedule_frequency = entity.getSchedule_frequency();
        if (schedule_frequency != null) {
            stmt.bindString(21, schedule_frequency);
        }
 
        Integer is_cancelled = entity.getIs_cancelled();
        if (is_cancelled != null) {
            stmt.bindLong(22, is_cancelled);
        }
 
        Integer is_reservable = entity.getIs_reservable();
        if (is_reservable != null) {
            stmt.bindLong(23, is_reservable);
        }
 
        String instructor_id = entity.getInstructor_id();
        if (instructor_id != null) {
            stmt.bindString(24, instructor_id);
        }
 
        String instructor_name = entity.getInstructor_name();
        if (instructor_name != null) {
            stmt.bindString(25, instructor_name);
        }
 
        String location_id = entity.getLocation_id();
        if (location_id != null) {
            stmt.bindString(26, location_id);
        }
 
        String location_name = entity.getLocation_name();
        if (location_name != null) {
            stmt.bindString(27, location_name);
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
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // class_desc
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // schedule_type_id
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // schedule_type
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // schedule_start_date
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // schedule_start_time
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // schedule_end_time
            cursor.isNull(offset + 11) ? null : cursor.getLong(offset + 11), // schedule_start_time_long
            cursor.isNull(offset + 12) ? null : cursor.getLong(offset + 12), // schedule_end_time_long
            cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13), // schedule_monday
            cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14), // schedule_tuesday
            cursor.isNull(offset + 15) ? null : cursor.getInt(offset + 15), // schedule_wednesday
            cursor.isNull(offset + 16) ? null : cursor.getInt(offset + 16), // schedule_thursday
            cursor.isNull(offset + 17) ? null : cursor.getInt(offset + 17), // schedule_friday
            cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18), // schedule_saturday
            cursor.isNull(offset + 19) ? null : cursor.getInt(offset + 19), // schedule_sunday
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // schedule_frequency
            cursor.isNull(offset + 21) ? null : cursor.getInt(offset + 21), // is_cancelled
            cursor.isNull(offset + 22) ? null : cursor.getInt(offset + 22), // is_reservable
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // instructor_id
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // instructor_name
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // location_id
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26) // location_name
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
        entity.setClass_desc(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setSchedule_type_id(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setSchedule_type(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setSchedule_start_date(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setSchedule_start_time(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setSchedule_end_time(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setSchedule_start_time_long(cursor.isNull(offset + 11) ? null : cursor.getLong(offset + 11));
        entity.setSchedule_end_time_long(cursor.isNull(offset + 12) ? null : cursor.getLong(offset + 12));
        entity.setSchedule_monday(cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13));
        entity.setSchedule_tuesday(cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14));
        entity.setSchedule_wednesday(cursor.isNull(offset + 15) ? null : cursor.getInt(offset + 15));
        entity.setSchedule_thursday(cursor.isNull(offset + 16) ? null : cursor.getInt(offset + 16));
        entity.setSchedule_friday(cursor.isNull(offset + 17) ? null : cursor.getInt(offset + 17));
        entity.setSchedule_saturday(cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18));
        entity.setSchedule_sunday(cursor.isNull(offset + 19) ? null : cursor.getInt(offset + 19));
        entity.setSchedule_frequency(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setIs_cancelled(cursor.isNull(offset + 21) ? null : cursor.getInt(offset + 21));
        entity.setIs_reservable(cursor.isNull(offset + 22) ? null : cursor.getInt(offset + 22));
        entity.setInstructor_id(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setInstructor_name(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setLocation_id(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setLocation_name(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
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
