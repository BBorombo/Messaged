package com.example.phantom.lvmessage_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Erwan on 12/11/2015.
 */
public abstract class BaseDAO {

    protected final static int VERSION = 1;

    protected final static String NAME = "messages.db";

    protected SQLiteDatabase db = null;
    protected MessageOpenHelper dbHandler =null;

    public BaseDAO(Context context){
        this.dbHandler = new MessageOpenHelper(context, NAME, null, VERSION);
    }

    public void open(){
        db = dbHandler.getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    public SQLiteDatabase getDb(){
        return db;
    }
}
