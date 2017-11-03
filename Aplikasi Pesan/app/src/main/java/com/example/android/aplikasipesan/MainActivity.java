package com.example.android.aplikasipesan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "List";
    Database myDatabase;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.List);
        myDatabase = new Database(this);

        tampilList();
    }
    private void tampilList() {
        Log.d(TAG, "tampilList: Displaying data in List");
        Cursor data = myDatabase.getData();
        List<String> listData = new ArrayList<>();

        int i = 1;
        while(data.moveToNext()){
            listData.add(data.getString(1));
            listData.add(data.getString(2));
            i++;
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);
    }

    public void tambah(View view) {
        Intent it;
        it = new Intent(this, Main2Activity.class);
        startActivity(it);
    }
}
