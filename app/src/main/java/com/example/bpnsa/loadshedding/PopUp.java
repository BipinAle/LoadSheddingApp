package com.example.bpnsa.loadshedding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class PopUp extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        RecyclerView rc= (RecyclerView) findViewById(R.id.GrpList);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(new GrpNameAdapter(this));

//        DisplayMetrics displayMetrics=new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//
//        int width=displayMetrics.widthPixels;
//        int height=displayMetrics.heightPixels;
//
//        getWindow().setLayout((int)(width*.8),(int)(height*.8));

    }
}
