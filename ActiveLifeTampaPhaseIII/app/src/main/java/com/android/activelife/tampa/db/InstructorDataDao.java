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
 * DAO for table "INSTRUCTOR_DATA".
*/
public class InstructorDataDao extends AbstractDao<InstructorData, Long> {

    public static final String TABLENAME = "INSTRUCTOR_DATA";

    /**
     * Properties of entity InstructorData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Instructor_id = new Property(1, String.class, "instructor_id", false, "INSTRUCTOR_ID");
        public final static Property Instructor_name = new Property(2, String.class, "instructor_name", false, "INSTRUCTOR_NAME");
    }


    public InstructorDataDao(DaoConfig config) {
        super(config);
    }
    
    public InstructorDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"INSTRUCTOR_DATA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"INSTRUCTOR_ID\" TEXT," + // 1: instructor_id
                "\"INSTRUCTOR_NAME\" TEXT);"); // 2: instructor_name
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"INSTRUCTOR_DATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, InstructorData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String instructor_id = entity.getInstructor_id();
        if (instructor_id != null) {
            stmt.bindString(2, instructor_id);
        }
 
        String instructor_name = entity.getInstructor_name();
        if (instructor_name != null) {
            stmt.bindString(3, instructor_name);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, InstructorData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String instructor_id = entity.getInstructor_id();
        if (instructor_id != null) {
            stmt.bindString(2, instructor_id);
        }
 
        String instructor_name = entity.getInstructor_name();
        if (instructor_name != null) {
            stmt.bindString(3, instructor_name);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public InstructorData readEntity(Cursor cursor, int offset) {
        InstructorData entity = new InstructorData( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // instructor_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // instructor_name
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, InstructorData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setInstructor_id(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setInstructor_name(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(InstructorData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(InstructorData entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(InstructorData entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
