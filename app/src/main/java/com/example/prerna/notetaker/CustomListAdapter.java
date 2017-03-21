package com.example.prerna.notetaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Optional;


public class CustomListAdapter extends BaseAdapter{
    ArrayList<String> result, checklist;
    Context context;


    private static LayoutInflater inflater=null;
    private Optional filter;

    public CustomListAdapter(Context mainActivity, ArrayList<String> prgmNameList, ArrayList<String> checkbox) {


        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mainActivity;
        checklist = checkbox;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public Optional getFilter() {
        return filter;
    }

    public void setFilter(Optional filter) {
        this.filter = filter;
    }

    public class Holder
    {
        EditText tv;
        CheckBox cb;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DatabaseHandler name = new DatabaseHandler(context);

        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.checkboxes_list, null);
        holder.tv=(EditText) rowView.findViewById(R.id.et_editText);
        holder.cb=(CheckBox) rowView.findViewById(R.id.cb_checkBox);
        boolean set = Integer.parseInt(checklist.get(position))==1 ?true:false;
        holder.cb.setChecked(set);
        holder.tv.setText(result.get(position));

        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            }
        });
        return rowView;
    }
}