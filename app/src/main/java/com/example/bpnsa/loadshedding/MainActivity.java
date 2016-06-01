package com.example.bpnsa.loadshedding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    public static final String DEFAULT = "N/A";
    Toolbar toolbar;
    private String groupname[] = {"Group 1", "Group 2", "Group 3", "Group 4", "Group 5", "Group 6", "Group 7"};
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < 7; i++) {

            GroupFragment gf = new GroupFragment();
            Bundle b = new Bundle();
            b.putInt("gid", i + 1);
            gf.setArguments(b);
            viewPagerAdapter.addFragmentAndTitle(gf, groupname[i]);
        }


        if (viewPager != null)
            viewPager.setAdapter(viewPagerAdapter);

        if (tabLayout != null)
            tabLayout.setupWithViewPager(viewPager);

    }
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getSharedPreferences("GroupNumber", Context.MODE_PRIVATE);
        String number = sharedPreferences.getString("position", DEFAULT);
        if(number.equals(DEFAULT))
        {
            Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
        }
        else {
            viewPager.setCurrentItem(Integer.parseInt(number));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.setting:

                startActivity(new Intent(this, PopUp.class));
                return true;

        }

        return super.onOptionsItemSelected(item);

    }

}



