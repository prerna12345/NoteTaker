package com.example.prerna.notetaker;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShowDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dialog);
        new AlertDialog.Builder(ShowDialog.this)
                .setTitle("NoteTaker")
                .setMessage("You have a reminder for today.")
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ShowDialog.this, Reminder.class);
                        PendingIntent sender = PendingIntent.getBroadcast(ShowDialog.this, 0, intent, 0);
                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                        alarmManager.cancel(sender);
                        Ringtone ringtone = RingtoneManager.getRingtone(ShowDialog.this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
                        ringtone.stop();
                        finish();
                    }
                })
                .setNegativeButton("Show", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(new Intent(ShowDialog.this,MainActivity.class));
                    }
                }).show();
    }
}
