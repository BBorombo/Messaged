package com.example.phantom.lvmessage_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Erwan on 12/11/2015.
 */
public class MessageDAO extends BaseDAO {

    public static final String MESSAGE_TABLE_NAME = "Message";

    //Description des colonnes
    public static final String COLUMN_ID = "ID";
    public static final int NUM_COLUMN_ID = 0;
    public static final String COLUMN_MESSAGE = "MESSAGE";
    public static final int NUM_COLUMN_MESSAGE = 1;
    public static final String COLUMN_AUTEUR = "AUTEUR";
    public static final int NUM_COLUMN_AUTEUR = 2;

    public MessageDAO(Context context) {
        super(context);
    }

    public void ajouter(Message m){
        ContentValues value = new ContentValues();
        value.put(MessageDAO.COLUMN_MESSAGE, m.getMessage());
        value.put(MessageDAO.COLUMN_AUTEUR, m.getAuteur());
        db.insert(MessageDAO.MESSAGE_TABLE_NAME, null, value);
        Log.d("insert", "ok");
    }

    public void supprimer(int id){
        db.delete(MESSAGE_TABLE_NAME,COLUMN_ID + " = ?", new String[] {String.valueOf(id)});
    }

    public void modifier(Message m){

    }

    public Message selectionner(int id){
        Message m;
        Cursor c = db.rawQuery("SELECT * FROM Message WHERE id = "+ id ,null);
        Log.d("select","ok");
        c.moveToFirst();
        m = new Message(c.getString(1));
        return m;
    }
}
