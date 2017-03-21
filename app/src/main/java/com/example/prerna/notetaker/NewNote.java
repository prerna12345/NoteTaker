package com.example.prerna.notetaker;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class NewNote extends AppCompatActivity {

    EditText metDescription;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         metDescription = (EditText) findViewById(R.id.et_description);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);


        }
    }


        public void listdata(MenuItem item) {
            Intent intent = new Intent(NewNote.this, CheckboxesActivity.class);
            startActivity(intent);
        }

        public void reminder(MenuItem item) {
        Intent intent = new Intent(NewNote.this, Reminder.class);
        startActivity(intent);
    }




        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_newnote, menu);
            return true;
        }



     @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
         if (id == android.R.id.home) {
            startActivity(new Intent(NewNote.this, MainActivity.class));
        }
        if(id == R.id.item_Save){
            String description = metDescription.getText().toString();
            saveNote(description,true);

        }
        return super.onOptionsItemSelected(item);

        }
public void saveNote(String description, boolean set){
    DatabaseHandler name = new DatabaseHandler(NewNote.this);
    if(!description.isEmpty()) {
        name.insertLabel(description);
        finish();
        startActivity(new Intent(NewNote.this, MainActivity.class));
    } else if(set){
        new AlertDialog.Builder(NewNote.this)
                .setTitle("NoteTaker")
                .setMessage("Please write something")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }
}
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String description = metDescription.getText().toString();
        saveNote(description,false);

    }



    }
