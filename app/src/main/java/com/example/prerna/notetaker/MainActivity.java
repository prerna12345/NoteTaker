package com.example.prerna.notetaker;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<String> list, image;
    Context context;
    EditText editText;
    ArrayAdapter<String> adapter;
    EditText inputSearch;
    ListView mlv_Notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        list = new ArrayList<String>();
        image = new ArrayList<>();
        context = this;
        list.add("Prerna");
        mlv_Notes = (ListView) findViewById(R.id.lv_notes);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        DatabaseHandler name = new DatabaseHandler(MainActivity.this);
        inputSearch = (EditText) findViewById(R.id.inputSearch);


        list = name.getAllLabels();
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);

registerForContextMenu(mlv_Notes);
        mlv_Notes.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    finish();
                    startActivity(new Intent(MainActivity.this, NewNote.class));
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        });
         mlv_Notes.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DisplayNote.class);
                intent.putExtra("description", " " + parent.getItemAtPosition(position));
                startActivity(intent);

            }

        });

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                MainActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
    }
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId() == R.id.delete_note){
            Toast.makeText(MainActivity.this,"We will delete it soon."+mlv_Notes.getItemAtPosition(info.position),Toast.LENGTH_LONG).show();
            DatabaseHandler dbh = new DatabaseHandler(MainActivity.this);
            dbh.deleteNote(mlv_Notes.getItemAtPosition(info.position).toString());
            list.remove(info.position);
            adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
            mlv_Notes.setAdapter(adapter);

        }
        return super.onContextItemSelected(item);
    }
}
