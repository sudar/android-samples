package com.sudarmuthu.android.homework.week2;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {
    private List<String> strings;
    private LayoutInflater mInflater;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
        mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        strings = new ArrayList<String>();
        
        strings.add("This");
        strings.add("is");
        strings.add("a");
        strings.add("long");
        strings.add("list");
        strings.add("which");
        strings.add("will");
        strings.add("be");
        strings.add("displayed");
        strings.add("in");
        strings.add("the");
        strings.add("screen");
        strings.add("with");
        strings.add("one");
        strings.add("word");
        strings.add("in");
        strings.add("a");
        strings.add("line");
        
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, strings) {
        	@Override
        	public View getView(int position, View convertView, ViewGroup parent) {
        		View row;
        		        		
        		if (null == convertView) {
        			row = mInflater.inflate(R.layout.list_item, null);
        		} else {
        			row = convertView;
        		}
        		
        		TextView tv = (TextView) row.findViewById(android.R.id.text1);
        		tv.setText(getItem(position));
        		
        		return row;
        	}
        });
    }
}