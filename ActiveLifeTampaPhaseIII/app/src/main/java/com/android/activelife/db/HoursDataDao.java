package com.android.activelife.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HOURS_DATA".
*/
public class HoursDataDao extends AbstractDao<HoursData, Long> {

    public static final String TABLENAME = "HOURS_DATA";

    /**
     * Properties of entity HoursData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Hours_name = new Property(1, String.class, "hours_name", false, "HOURS_NAME");
        public final static Property Hour_monday_start_time = new Property(2, String.class, "hour_monday_start_time", false, "HOUR_MONDAY_START_TIME");
        public final static Property Hour_monday_end_time = new Property(3, String.class, "hour_monday_end_time", false, "HOUR_MONDAY_END_TIME");
        public final static Property Hour_tuesday_start_time = new Property(4, String.class, "hour_tuesday_start_time", false, "HOUR_TUESDAY_START_TIME");
        public final static Property Hour_tuesday_end_time = new Property(5, String.class, "hour_tuesday_end_time", false, "HOUR_TUESDAY_END_TIME");
        public final static Property Hour_wednesday_start_time = new Property(6, String.class, "hour_wednesday_start_time", false, "HOUR_WEDNESDAY_START_TIME");
        public final static Property Hour_wednesday_end_time = new Property(7, String.class, "hour_wednesday_end_time", false, "HOUR_WEDNESDAY_END_TIME");
        public final static Property Hour_thursday_start_time = new Property(8, String.class, "hour_thursday_start_time", false, "HOUR_THURSDAY_START_TIME");
        public final static Property Hour_thursday_end_time = new Property(9, String.class, "hour_thursday_end_time", false, "HOUR_THURSDAY_END_TIME");
        public final static Property Hour_friday_start_time = new Property(10, String.class, "hour_friday_start_time", false, "HOUR_FRIDAY_START_TIME");
        public final static Property Hour_friday_end_time = new Property(11, String.class, "hour_friday_end_time", false, "HOUR_FRIDAY_END_TIME");
        public final static Property Hour_saturday_start_time = new Property(12, String.class, "hour_saturday_start_time", false, "HOUR_SATURDAY_START_TIME");
        public final static Property Hour_saturday_end_time = new Property(13, String.class, "hour_saturday_end_time", false, "HOUR_SATURDAY_END_TIME");
        public final static Property Hour_sunday_start_time = new Property(14, String.class, "hour_sunday_start_time", false, "HOUR_SUNDAY_START_TIME");
        public final static Property Hour_sunday_end_time = new Property(15, String.class, "hour_sunday_end_time", false, "HOUR_SUNDAY_END_TIME");
    }


    public HoursDataDao(DaoConfig config) {
        super(config);
    }
    
    public HoursDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HOURS_DATA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"HOURS_NAME\" TEXT," + // 1: hours_name
                "\"HOUR_MONDAY_START_TIME\" TEXT," + // 2: hour_monday_start_time
                "\"HOUR_MONDAY_END_TIME\" TEXT," + // 3: hour_monday_end_time
                "\"HOUR_TUESDAY_START_TIME\" TEXT," + // 4: hour_tuesday_start_time
                "\"HOUR_TUESDAY_END_TIME\" TEXT," + // 5: hour_tuesday_end_time
                "\"HOUR_WEDNESDAY_START_TIME\" TEXT," + // 6: hour_wednesday_start_time
                "\"HOUR_WEDNESDAY_END_TIME\" TEXT," + // 7: hour_wednesday_end_time
                "\"HOUR_THURSDAY_START_TIME\" TEXT," + // 8: hour_thursday_start_time
                "\"HOUR_THURSDAY_END_TIME\" TEXT," + // 9: hour_thursday_end_time
                "\"HOUR_FRIDAY_START_TIME\" TEXT," + // 10: hour_friday_start_time
                "\"HOUR_FRIDAY_END_TIME\" TEXT," + // 11: hour_friday_end_time
                "\"HOUR_SATURDAY_START_TIME\" TEXT," + // 12: hour_saturday_start_time
                "\"HOUR_SATURDAY_END_TIME\" TEXT," + // 13: hour_saturday_end_time
                "\"HOUR_SUNDAY_START_TIME\" TEXT," + // 14: hour_sunday_start_time
                "\"HOUR_SUNDAY_END_TIME\" TEXT);"); // 15: hour_sunday_end_time
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HOURS_DATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HoursData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String hours_name = entity.getHours_name();
        if (hours_name != null) {
            stmt.bindString(2, hours_name);
        }
 
        String hour_monday_start_time = entity.getHour_monday_start_time();
        if (hour_monday_start_time != null) {
            stmt.bindString(3, hour_monday_start_time);
        }
 
        String hour_monday_end_time = entity.getHour_monday_end_time();
        if (hour_monday_end_time != null) {
            stmt.bindString(4, hour_monday_end_time);
        }
 
        String hour_tuesday_start_time = entity.getHour_tuesday_start_time();
        if (hour_tuesday_start_time != null) {
            stmt.bindString(5, hour_tuesday_start_time);
        }
 
        String hour_tuesday_end_time = entity.getHour_tuesday_end_time();
        if (hour_tuesday_end_time != null) {
            stmt.bindString(6, hour_tuesday_end_time);
        }
 
        String hour_wednesday_start_time = entity.getHour_wednesday_start_time();
        if (hour_wednesday_start_time != null) {
            stmt.bindString(7, hour_wednesday_start_time);
        }
 
        String hour_wednesday_end_time = entity.getHour_wednesday_end_time();
        if (hour_wednesday_end_time != null) {
            stmt.bindString(8, hour_wednesday_end_time);
        }
 
        String hour_thursday_start_time = entity.getHour_thursday_start_time();
        if (hour_thursday_start_time != null) {
            stmt.bindString(9, hour_thursday_start_time);
        }
 
        String hour_thursday_end_time = entity.getHour_thursday_end_time();
        if (hour_thursday_end_time != null) {
            stmt.bindString(10, hour_thursday_end_time);
        }
 
        String hour_friday_start_time = entity.getHour_friday_start_time();
        if (hour_friday_start_time != null) {
            stmt.bindString(11, hour_friday_start_time);
        }
 
        String hour_friday_end_time = entity.getHour_friday_end_time();
        if (hour_friday_end_time != null) {
            stmt.bindString(12, hour_friday_end_time);
        }
 
        String hour_saturday_start_time = entity.getHour_saturday_start_time();
        if (hour_saturday_start_time != null) {
            stmt.bindString(13, hour_saturday_start_time);
        }
 
        String hour_saturday_end_time = entity.getHour_saturday_end_time();
        if (hour_saturday_end_time != null) {
            stmt.bindString(14, hour_saturday_end_time);
        }
 
        String hour_sunday_start_time = entity.getHour_sunday_start_time();
        if (hour_sunday_start_time != null) {
            stmt.bindString(15, hour_sunday_start_time);
        }
 
        String hour_sunday_end_time = entity.getHour_sunday_end_time();
        if (hour_sunday_end_time != null) {
            stmt.bindString(16, hour_sunday_end_time);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HoursData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String hours_name = entity.getHours_name();
        if (hours_name != null) {
            stmt.bindString(2, hours_name);
        }
 
        String hour_monday_start_time = entity.getHour_monday_start_time();
        if (hour_monday_start_time != null) {
            stmt.bindString(3, hour_monday_start_time);
        }
 
        String hour_monday_end_time = entity.getHour_monday_end_time();
        if (hour_monday_end_time != null) {
            stmt.bindString(4, hour_monday_end_time);
        }
 
        String hour_tuesday_start_time = entity.getHour_tuesday_start_time();
        if (hour_tuesday_start_time != null) {
            stmt.bindString(5, hour_tuesday_start_time);
        }
 
        String hour_tuesday_end_time = entity.getHour_tuesday_end_time();
        if (hour_tuesday_end_time != null) {
            stmt.bindString(6, hour_tuesday_end_time);
        }
 
        String hour_wednesday_start_time = entity.getHour_wednesday_start_time();
        if (hour_wednesday_start_time != null) {
            stmt.bindString(7, hour_wednesday_start_time);
        }
 
        String hour_wednesday_end_time = entity.getHour_wednesday_end_time();
        if (hour_wednesday_end_time != null) {
            stmt.bindString(8, hour_wednesday_end_time);
        }
 
        String hour_thursday_start_time = entity.getHour_thursday_start_time();
        if (hour_thursday_start_time != null) {
            stmt.bindString(9, hour_thursday_start_time);
        }
 
        String hour_thursday_end_time = entity.getHour_thursday_end_time();
        if (hour_thursday_end_time != null) {
            stmt.bindString(10, hour_thursday_end_time);
        }
 
        String hour_friday_start_time = entity.getHour_friday_start_time();
        if (hour_friday_start_time != null) {
            stmt.bindString(11, hour_friday_start_time);
        }
 
        String hour_friday_end_time = entity.getHour_friday_end_time();
        if (hour_friday_end_time != null) {
            stmt.bindString(12, hour_friday_end_time);
        }
 
        String hour_saturday_start_time = entity.getHour_saturday_start_time();
        if (hour_saturday_start_time != null) {
            stmt.bindString(13, hour_saturday_start_time);
        }
 
        String hour_saturday_end_time = entity.getHour_saturday_end_time();
        if (hour_saturday_end_time != null) {
            stmt.bindString(14, hour_saturday_end_time);
        }
 
        String hour_sunday_start_time = entity.getHour_sunday_start_time();
        if (hour_sunday_start_time != null) {
            stmt.bindString(15, hour_sunday_start_time);
        }
 
        String hour_sunday_end_time = entity.getHour_sunday_end_time();
        if (hour_sunday_end_time != null) {
            stmt.bindString(16, hour_sunday_end_time);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public HoursData readEntity(Cursor cursor, int offset) {
        HoursData entity = new HoursData( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // hours_name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // hour_monday_start_time
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // hour_monday_end_time
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // hour_tuesday_start_time
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // hour_tuesday_end_time
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // hour_wednesday_start_time
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // hour_wednesday_end_time
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // hour_thursday_start_time
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // hour_thursday_end_time
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // hour_friday_start_time
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // hour_friday_end_time
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // hour_saturday_start_time
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // hour_saturday_end_time
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // hour_sunday_start_time
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15) // hour_sunday_end_time
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, HoursData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setHours_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setHour_monday_start_time(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setHour_monday_end_time(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setHour_tuesday_start_time(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setHour_tuesday_end_time(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setHour_wednesday_start_time(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setHour_wednesday_end_time(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setHour_thursday_start_time(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setHour_thursday_end_time(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setHour_friday_start_time(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setHour_friday_end_time(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setHour_saturday_start_time(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setHour_saturday_end_time(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setHour_sunday_start_time(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setHour_sunday_end_time(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(HoursData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(HoursData entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(HoursData entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
