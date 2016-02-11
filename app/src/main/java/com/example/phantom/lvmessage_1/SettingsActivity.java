package com.example.phantom.lvmessage_1;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Erwan on 11/11/2015.
 */
public class SettingsActivity  extends Activity{

    private int heure = 9;
    private int minute = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);

        //Récupère le bouton
        FloatingActionButton back = (FloatingActionButton) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getApplicationContext().getSharedPreferences("AppPreferences", 0);

                SharedPreferences.Editor edit = sp.edit();
                edit.putInt("Heure", heure);
                edit.putInt("Minute", minute);
                edit.commit();
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Récupère le texte
        TextView textTime = (TextView) findViewById(R.id.text_time);
        final TextView time_= (TextView) findViewById(R.id.time_);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("AppPreferences", 0);
        if (sp.contains("Heure") && sp.contains("Minute")){
            heure = sp.getInt("Heure", 0);
            minute = sp.getInt("Minute", 0);
        }
        makeText(time_);

        //On applique la police
        Typeface font = Typeface.createFromAsset(getAssets(), "Lobster 1.4.otf");
        textTime.setTypeface(font);
        time_.setTypeface(font);

        time_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                //int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                //int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(SettingsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        heure = selectedHour;
                        minute = selectedMinute;
                        makeText(time_);
                    }
                }, heure, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    public void makeText(TextView tv) {
        if (minute == 0) {
            String min = String.valueOf(minute) + "0";
            tv.setText(heure + ":" + min);
        }else{
            tv.setText(heure + ":" + minute);
        }

    }
}
