package com.example.prerna.notetaker;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class CheckboxesActivity extends AppCompatActivity {

    CheckBox checkBox;
    EditText editText;
    ListView listView;
    ArrayList<String> list, checklist;
    Cursor cursor;
    CustomListAdapter customListAdapter;
    DatabaseListHandler handler;
    LinearLayout mLinearLayout;
    ArrayList<View> viewsList;
    int count=0,maxid=1;
    EditText minputSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkboxes);

        Toolbar mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        minputSearch = (EditText) findViewById(R.id.inputSearch1);
        setSupportActionBar(mtoolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }


        list = new ArrayList<>();
        checklist = new ArrayList<>();
        handler = new DatabaseListHandler(CheckboxesActivity.this);
        listView = (ListView) findViewById(R.id.lv_checkboxes);
        viewsList = new ArrayList<>();
        cursor = handler.getAllLabels();

        if (cursor != null) {

            if (cursor.moveToFirst()) {
                do {
                    checklist.add(cursor.getString(cursor.getColumnIndex("alwar")));
                    list.add(cursor.getString(cursor.getColumnIndex("jaipur")));
                    maxid = cursor.getInt(cursor.getColumnIndex("list_number"));
                } while (cursor.moveToNext());
            }

            cursor.close();
            customListAdapter = new CustomListAdapter(CheckboxesActivity.this, list, checklist);

            listView.setAdapter(customListAdapter);

        }

        minputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
              //CheckboxesActivity.this.customListAdapter.getFilter().filter(cs);
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
        @Override
        public boolean onSupportNavigateUp() {
            onBackPressed();
            return true;
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_checkboxes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.addItem) {
            listView.setVisibility(View.GONE); count++;
            mLinearLayout = new LinearLayout(CheckboxesActivity.this);
            mLinearLayout = (LinearLayout) findViewById(R.id.mLinearlayout);
            LinearLayout hLinearLayout = new LinearLayout(CheckboxesActivity.this);
            hLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            hLinearLayout.setWeightSum(4);
            checkBox = new CheckBox(CheckboxesActivity.this);
            editText = new EditText(CheckboxesActivity.this);

            editText.setText("");
            editText.setId(R.id.search+count);
            editText.setMinimumWidth(400);
            hLinearLayout.addView(checkBox);
            hLinearLayout.addView(editText);
            mLinearLayout.addView(hLinearLayout);
            viewsList.add(hLinearLayout);
        }
        if (id == R.id.cb_save) {
            listView.setVisibility(View.VISIBLE);
            for (int i=1; i<=count; i++) {
                editText = (EditText) findViewById(R.id.search + i);
                int set = (checkBox.isChecked()) ? 1 : 0;
                handler.insertLabel(set, editText.getText().toString(), maxid);
            }
            cursor = handler.getAllLabels();

            if (cursor != null){

                if (cursor.moveToFirst()) {
                    do {
                        checklist.add(cursor.getString(cursor.getColumnIndex("alwar")));
                        list.add(cursor.getString(cursor.getColumnIndex("jaipur")));
                    } while (cursor.moveToNext());
                }

                cursor.close();
                if(((LinearLayout) mLinearLayout).getChildCount() > 0)
                    ((LinearLayout) mLinearLayout).removeAllViews();
                customListAdapter = new CustomListAdapter(CheckboxesActivity.this, list, checklist);

               listView.setAdapter(customListAdapter);
            }


            else {

                Toast.makeText(CheckboxesActivity.this, "cursor is null", Toast.LENGTH_LONG).show();

            }

        }
            return super.onOptionsItemSelected(item);
        }


}