package com.example.phantom.lvmessage_1;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Phantom on 16/11/2015.
 */
public class BackgroundProcess {

    public static String DB_PATH = "/data/data/com.example.phantom.lvmessage_1/databases/";
    public static String DB_NAME = "test.db";
    public MessageDAO mDAO;

    public BackgroundProcess(MessageDAO mDAO){
        this.mDAO = mDAO;
        this.mDAO.open();
        ajouts();
        Message msg = select();
        Log.d("Msg",msg.getMessage());
        mDAO.close();
    }

    public void ajouts(){
        Message msg1 = new Message("Salut 1");
        Message msg2 = new Message("Salut 2");
        Message msg3 = new Message("Salut 3");
        this.mDAO.ajouter(msg1);
        this.mDAO.ajouter(msg2);
        this.mDAO.ajouter(msg3);
    }

    public Message select(){
        Message msg = mDAO.selectionner(2);
        return msg;
    }


    



    /*
    public void copyDataBase(Context myContext) throws IOException {

        // crée le repertoire
        File f = new File(DB_PATH);
        f.mkdirs();

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

    }*/
}
