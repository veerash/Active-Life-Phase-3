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
 * DAO for table "CLASS_DATA".
*/
public class ClassDataDao extends AbstractDao<ClassData, Long> {

    public static final String TABLENAME = "CLASS_DATA";

    /**
     * Properties of entity ClassData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Class_id = new Property(1, String.class, "class_id", false, "CLASS_ID");
        public final static Property Class_name = new Property(2, String.class, "class_name", false, "CLASS_NAME");
        public final static Property Class_description = new Property(3, String.class, "class_description", false, "CLASS_DESCRIPTION");
    }


    public ClassDataDao(DaoConfig config) {
        super(config);
    }
    
    public ClassDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CLASS_DATA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CLASS_ID\" TEXT," + // 1: class_id
                "\"CLASS_NAME\" TEXT," + // 2: class_name
                "\"CLASS_DESCRIPTION\" TEXT);"); // 3: class_description
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CLASS_DATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ClassData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String class_id = entity.getClass_id();
        if (class_id != null) {
            stmt.bindString(2, class_id);
        }
 
        String class_name = entity.getClass_name();
        if (class_name != null) {
            stmt.bindString(3, class_name);
        }
 
        String class_description = entity.getClass_description();
        if (class_description != null) {
            stmt.bindString(4, class_description);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ClassData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String class_id = entity.getClass_id();
        if (class_id != null) {
            stmt.bindString(2, class_id);
        }
 
        String class_name = entity.getClass_name();
        if (class_name != null) {
            stmt.bindString(3, class_name);
        }
 
        String class_description = entity.getClass_description();
        if (class_description != null) {
            stmt.bindString(4, class_description);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ClassData readEntity(Cursor cursor, int offset) {
        ClassData entity = new ClassData( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // class_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // class_name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // class_description
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ClassData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setClass_id(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setClass_name(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setClass_description(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ClassData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ClassData entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ClassData entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}