package com.example.phantom.lvmessage_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Erwan on 12/11/2015.
 */
public class MessageOpenHelper extends SQLiteOpenHelper {

    //Version de la base
    private static final int DATABASE_VERSION = 1;

    //Nom de la base
    private static final String MESSAGE_BASE_NAME = "messages.db";

    //Nom de la table
    public static final String MESSAGE_TABLE_NAME = "Message";

    //Description des colonnes
    public static final String COLUMN_ID = "ID";
    public static final int NUM_COLUMN_ID = 0;
    public static final String COLUMN_MESSAGE = "MESSAGE";
    public static final int NUM_COLUMN_MESSAGE = 1;
    public static final String COLUMN_AUTEUR = "AUTEUR";
    public static final int NUM_COLUMN_AUTEUR = 2;

    //Requête SQL de création de la base
    private static final String REQUETE_CREATION_BDD ="CREATE TABLE " +
            MESSAGE_TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_MESSAGE + " TEXT NOT NULL, " +  COLUMN_AUTEUR + " TEXT);";


    public MessageOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, MESSAGE_BASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQUETE_CREATION_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > DATABASE_VERSION){
            db.execSQL("DROP TABLE " + MESSAGE_TABLE_NAME + " ;");
            onCreate(db);
        }
    }
}
