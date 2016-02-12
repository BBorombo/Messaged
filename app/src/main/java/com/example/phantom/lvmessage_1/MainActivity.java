package com.example.phantom.lvmessage_1;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Iterator;


public class MainActivity extends AppCompatActivity {

    private HSSFWorkbook classeur;
    private TextView message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Récupération des champs, ajout de la police
        TextView day_msg = (TextView) findViewById(R.id.day_msg);
        message = (TextView) findViewById(R.id.message);

        Typeface font = Typeface.createFromAsset(getAssets(), "Lobster 1.4.otf");

        day_msg.setTypeface(font);
        message.setTypeface(font);

        openExcelFIle();
        setMessage();

        }

    public void setMessage(){
        Calendar now = Calendar.getInstance();
        HSSFSheet sheet = classeur.getSheetAt(0);
        Iterator i = sheet.rowIterator();
        boolean found = false;
        i.next();
        while(i.hasNext() && !found){
            HSSFRow tmp = (HSSFRow) i.next();
            if(isToday(now,tmp.getCell(1).toString())){
                found = true;
                this.message.setText(tmp.getCell(2).toString());
            }
        }
    }

    public boolean isToday(Calendar now, String date){
        boolean res = false;
        String[] tmp = date.split("/");
        int day = now.get(Calendar.DAY_OF_MONTH);
        int month = now.get(Calendar.MONTH) + 1;
        Log.d("Day_Tab",tmp[0]);
        Log.d("Month_Tab", tmp[1]);
        int dayTab = Integer.valueOf(tmp[0]);
        int monthTab = Integer.valueOf(tmp[1]);
        if ((dayTab == day) && (monthTab == month)){
            res = true;
        }
        return res;
    }

    public void openExcelFIle(){

        final AssetManager assetManager = getAssets();
        try {
            InputStream input = assetManager.open("Messages_Appli.xls");
            POIFSFileSystem myFileSystem = new POIFSFileSystem(input);
            this.classeur = new HSSFWorkbook(myFileSystem);
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
