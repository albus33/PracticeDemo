package com.example.administrator.pulltorefreshlistviewdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    ListView lv_listview;
    List<String> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_listview = (ListView) findViewById(R.id.lv_listview);

        datas = new ArrayList<String>();

        MyAdapter myAdapter = new MyAdapter(this, datas);
        lv_listview.setAdapter(myAdapter);
        for (int i = 0; i < 10; i++) {
            datas.add("数据" + i);

        }
        myAdapter.notifyDataSetChanged();
    }
}
