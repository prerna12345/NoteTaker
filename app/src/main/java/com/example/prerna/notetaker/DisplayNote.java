package com.example.prerna.notetaker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;


public class DisplayNote extends AppCompatActivity {
    TextView mdisplay_Textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);


        }
        String desc = getIntent().getStringExtra("description");
        mdisplay_Textview = (TextView) findViewById(R.id.display_Textview);
        mdisplay_Textview.setText(desc);




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(DisplayNote.this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
