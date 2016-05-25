package com.example.bpnsa.loadshedding;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private String groupname[] = {"Group 1", "Group 2", "Group 3", "Group 4", "Group 5", "Group 6", "Group 7"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < 7; i++) {

            GroupFragment gf = new GroupFragment();
            Bundle b = new Bundle();
            b.putInt("gid", i+1);
            gf.setArguments(b);

            viewPagerAdapter.addFragmentAndTitle(gf, groupname[i]);
        }


        if(viewPager!=null)
        viewPager.setAdapter(viewPagerAdapter);

        if(tabLayout!=null)
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.setting)
        {

            setting(item);

        }
        return  true;
    }

    public void setting(MenuItem item)
    {

    }

}



