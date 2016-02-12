package com.example.phantom.lvmessage_1;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    private HSSFWorkbook classeur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Récupération des champs, ajout de la police
        TextView day_msg = (TextView) findViewById(R.id.day_msg);
        TextView message = (TextView) findViewById(R.id.message);

        Typeface font = Typeface.createFromAsset(getAssets(), "Lobster 1.4.otf");

        day_msg.setTypeface(font);
        message.setTypeface(font);

        }

    public void openExcelFIle(){

        final AssetManager assetManager = getAssets();
        try {
            InputStream input = assetManager.open("Messages_Appli.xlsx");
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
