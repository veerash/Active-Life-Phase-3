package com.android.activelife.tampa.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.identityscope.IdentityScopeType;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/**
 * Master of DAO (schema version 1): knows all DAOs.
 */
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(Database db, boolean ifNotExists) {
        ScheduleDateDataDao.createTable(db, ifNotExists);
        ClassDataDao.createTable(db, ifNotExists);
        InstructorDataDao.createTable(db, ifNotExists);
        ScheduleDataDao.createTable(db, ifNotExists);
        HoursDataDao.createTable(db, ifNotExists);
        LocationDataDao.createTable(db, ifNotExists);
        DefaultLocationDataDao.createTable(db, ifNotExists);
        LocationsDataDao.createTable(db, ifNotExists);
        MessagesDataDao.createTable(db, ifNotExists);
        MemberDataDao.createTable(db, ifNotExists);
        MessageLocationDataDao.createTable(db, ifNotExists);
    }

    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(Database db, boolean ifExists) {
        ScheduleDateDataDao.dropTable(db, ifExists);
        ClassDataDao.dropTable(db, ifExists);
        InstructorDataDao.dropTable(db, ifExists);
        ScheduleDataDao.dropTable(db, ifExists);
        HoursDataDao.dropTable(db, ifExists);
        LocationDataDao.dropTable(db, ifExists);
        DefaultLocationDataDao.dropTable(db, ifExists);
        LocationsDataDao.dropTable(db, ifExists);
        MessagesDataDao.dropTable(db, ifExists);
        MemberDataDao.dropTable(db, ifExists);
        MessageLocationDataDao.dropTable(db, ifExists);
    }

    /**
     * WARNING: Drops all table on Upgrade! Use only during development.
     * Convenience method using a {@link DevOpenHelper}.
     */
    public static DaoSession newDevSession(Context context, String name) {
        Database db = new DevOpenHelper(context, name).getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }

    public DaoMaster(SQLiteDatabase db) {
        this(new StandardDatabase(db));
    }

    public DaoMaster(Database db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(ScheduleDateDataDao.class);
        registerDaoClass(ClassDataDao.class);
        registerDaoClass(InstructorDataDao.class);
        registerDaoClass(ScheduleDataDao.class);
        registerDaoClass(HoursDataDao.class);
        registerDaoClass(LocationDataDao.class);
        registerDaoClass(DefaultLocationDataDao.class);
        registerDaoClass(LocationsDataDao.class);
        registerDaoClass(MessagesDataDao.class);
        registerDaoClass(MemberDataDao.class);
        registerDaoClass(MessageLocationDataDao.class);
    }

    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }

    /**
     * Calls {@link #createAllTables(Database, boolean)} in {@link #onCreate(Database)} -
     */
    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String name) {
            super(context, name, SCHEMA_VERSION);
        }

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(Database db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }

    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name) {
            super(context, name);
        }

        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

}
