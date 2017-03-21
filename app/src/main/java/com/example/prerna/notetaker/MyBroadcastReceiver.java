package com.example.prerna.notetaker;


import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {
        mp=MediaPlayer.create(context, R.raw.shapeofyou  );
        mp.start();
        Intent mIntent1 = new Intent(context,ShowDialog.class);
        mIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mIntent1);
    }
}
