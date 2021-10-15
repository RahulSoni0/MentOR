package com.ersubhadip.hackathon.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.ersubhadip.hackathon.Classes.ViewpagerAdapter;
import com.ersubhadip.hackathon.Fragments.ebooksFragment;
import com.ersubhadip.hackathon.Fragments.overViewFragment;
import com.ersubhadip.hackathon.Fragments.videoFragment;
import com.ersubhadip.hackathon.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TabedActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager2 viewPager;
    private ViewpagerAdapter adapter;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabed);
        tab=findViewById(R.id.tabMain);
        viewPager=findViewById(R.id.vp2);
        toolbar=findViewById(R.id.tabToolBar);
        adapter=new ViewpagerAdapter(getSupportFragmentManager(),getLifecycle());
        setSupportActionBar(toolbar);

        ViewpagerAdapter.tabTitleList.clear();//to avoid duplicate fragments
        ViewpagerAdapter.tabFragmentList.clear();//to avoid duplicate fragments

        //adding fragments to adapter
        adapter.addFragments(new overViewFragment(),"Course OverView");
        adapter.addFragments(new ebooksFragment(),"E-Books");
        adapter.addFragments(new videoFragment(),"Video Tutorials");
        //end

        //setting Action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Open Source");
        //end

        //setting ViewPagerAdapter
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        //end

        //setting tabLayout
        TabLayoutMediator mediator=new TabLayoutMediator(tab, viewPager, true,true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(ViewpagerAdapter.tabTitleList.get(position));
            }
        });

        mediator.attach();
        //end


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){

            finish();
            return  true;

        }else{

            return false;

        }
    }
}