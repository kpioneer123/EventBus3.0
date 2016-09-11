package com.losileeya.eventbusapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.losileeya.eventbusapp.event.EventBusEvents;

import org.greenrobot.eventbus.EventBus;


public class ThirdActivity extends AppCompatActivity {
    private ListView mListView;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        mListView = (ListView) findViewById(R.id.listView);
        adapter = new MyAdapter();
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //ThirdActivity 向 DetailActivity出传递数据
                EventBus.getDefault().postSticky(new EventBusEvents.ThirdEvent(adapter.getItem(position).toString()));
                Intent intent = new Intent(ThirdActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
    }

    class MyAdapter extends BaseAdapter {
        private String[] data = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public Object getItem(int position) {
            return data[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(ThirdActivity.this);
            textView.setText(data[position]);
            textView.setTextSize(17);
            textView.setPadding(10, 10, 10, 10);
            textView.setTextColor(Color.parseColor("#ff4500"));
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            return textView;
        }
    }



}