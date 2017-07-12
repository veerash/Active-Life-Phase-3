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
 * DAO for table "MESSAGES_DATA".
*/
public class MessagesDataDao extends AbstractDao<MessagesData, Long> {

    public static final String TABLENAME = "MESSAGES_DATA";

    /**
     * Properties of entity MessagesData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Message_id = new Property(1, String.class, "message_id", false, "MESSAGE_ID");
        public final static Property Message_title = new Property(2, String.class, "message_title", false, "MESSAGE_TITLE");
        public final static Property Message_msg = new Property(3, String.class, "message_msg", false, "MESSAGE_MSG");
        public final static Property Message_send_at = new Property(4, String.class, "message_send_at", false, "MESSAGE_SEND_AT");
    }


    public MessagesDataDao(DaoConfig config) {
        super(config);
    }
    
    public MessagesDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MESSAGES_DATA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"MESSAGE_ID\" TEXT," + // 1: message_id
                "\"MESSAGE_TITLE\" TEXT," + // 2: message_title
                "\"MESSAGE_MSG\" TEXT," + // 3: message_msg
                "\"MESSAGE_SEND_AT\" TEXT);"); // 4: message_send_at
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MESSAGES_DATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MessagesData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String message_id = entity.getMessage_id();
        if (message_id != null) {
            stmt.bindString(2, message_id);
        }
 
        String message_title = entity.getMessage_title();
        if (message_title != null) {
            stmt.bindString(3, message_title);
        }
 
        String message_msg = entity.getMessage_msg();
        if (message_msg != null) {
            stmt.bindString(4, message_msg);
        }
 
        String message_send_at = entity.getMessage_send_at();
        if (message_send_at != null) {
            stmt.bindString(5, message_send_at);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MessagesData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String message_id = entity.getMessage_id();
        if (message_id != null) {
            stmt.bindString(2, message_id);
        }
 
        String message_title = entity.getMessage_title();
        if (message_title != null) {
            stmt.bindString(3, message_title);
        }
 
        String message_msg = entity.getMessage_msg();
        if (message_msg != null) {
            stmt.bindString(4, message_msg);
        }
 
        String message_send_at = entity.getMessage_send_at();
        if (message_send_at != null) {
            stmt.bindString(5, message_send_at);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public MessagesData readEntity(Cursor cursor, int offset) {
        MessagesData entity = new MessagesData( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // message_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // message_title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // message_msg
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // message_send_at
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MessagesData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMessage_id(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMessage_title(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMessage_msg(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setMessage_send_at(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(MessagesData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(MessagesData entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MessagesData entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
