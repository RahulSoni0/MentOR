package com.ersubhadip.hackathon.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.ersubhadip.hackathon.Classes.ViewpagerAdapter;
import com.ersubhadip.hackathon.Classes.booksRvAdapter;
import com.ersubhadip.hackathon.Fragments.ebooksFragment;
import com.ersubhadip.hackathon.Fragments.overViewFragment;
import com.ersubhadip.hackathon.Fragments.videoFragment;
import com.ersubhadip.hackathon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class TabedActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager2 viewPager;
    private ViewpagerAdapter adapter;
    private Toolbar toolbar;
    int t= booksRvAdapter.type;
    FirebaseFirestore s;
    String title;


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

        s=FirebaseFirestore.getInstance();
        s.collection("courses").orderBy("orderNumber").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot snapshot:task.getResult()) {



                    if((Long)snapshot.get("orderNumber")==Long.valueOf(t+1)){

                        //fetch name from that particular snap
                        title=(String) snapshot.get("name");
                        getSupportActionBar().setTitle(title);

                        //end

                        break;
                    }
                }
            }
        });
        //end

        //todo:doubt related to oop java public static int and int related to fetching data

        //setting ViewPagerAdapter
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        //end

        //setting tabLayout
        TabLayoutMediator mediator=new TabLayoutMediator(tab, viewPager, true,true,new TabLayoutMediator.TabConfigurationStrategy() {
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