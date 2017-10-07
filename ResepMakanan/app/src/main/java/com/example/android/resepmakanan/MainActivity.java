package com.example.android.resepmakanan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView r1;
    String s1[],s2[];
    MyOwnAdapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r1 = (RecyclerView) findViewById(R.id.myRecycler);
        s1 = getResources().getStringArray(R.array.recipe_name);
        s2 = getResources().getStringArray(R.array.desc);

        ad = new MyOwnAdapter(this,s1,s2);

        r1.setAdapter(ad);
        r1.setLayoutManager(new LinearLayoutManager(this));
    }
}
