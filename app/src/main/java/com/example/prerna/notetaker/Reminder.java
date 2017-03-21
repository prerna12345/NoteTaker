package com.example.prerna.notetaker;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class Reminder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        Toolbar mreminder_toolbar = (Toolbar) findViewById(R.id.Reminder_toolbar);

        setSupportActionBar(mreminder_toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }


        Button btnSetAlarm = (Button) findViewById(R.id.btn_set_alarm);
        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("WrongConstant")
            @Override
            public void onClick(View v) {
                Intent i = new Intent("alarm.reminderactivity");
                PendingIntent operation = PendingIntent.getActivity(getBaseContext(), 0, i, Intent.FLAG_ACTIVITY_NEW_TASK);

                AlarmManager alarmManager = (AlarmManager) getBaseContext().getSystemService(ALARM_SERVICE);

                DatePicker dpDate = (DatePicker) findViewById(R.id.dp_date);

                TimePicker tpTime = (TimePicker) findViewById(R.id.tp_time);

                int year = dpDate.getYear();
                int month = dpDate.getMonth();
                int day = dpDate.getDayOfMonth();
                int hour = tpTime.getCurrentHour();
                int minute = tpTime.getCurrentMinute();

                GregorianCalendar calendar = new GregorianCalendar(year, month, day, hour, minute);

                long alarm_time = calendar.getTimeInMillis();
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarm_time, operation);
                Intent intent = new Intent(Reminder.this, MainActivity.class);
                startAlert(alarm_time);
                Toast.makeText(getBaseContext(), "Reminder saved.", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }


        });


        Button btnQuitAlarm = (Button) findViewById(R.id.btn_quit_alarm);
        btnQuitAlarm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reminder.this, MainActivity.class);
                startActivity(intent);


            }
        });


    }

    public void startAlert(long i) {
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,  i, pendingIntent);



    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}