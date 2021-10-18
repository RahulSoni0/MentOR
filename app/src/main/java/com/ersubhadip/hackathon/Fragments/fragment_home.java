package com.ersubhadip.hackathon.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ersubhadip.hackathon.Classes.homeAdapter;
import com.ersubhadip.hackathon.Classes.homePosterAdapter;
import com.ersubhadip.hackathon.R;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class fragment_home extends Fragment {

    private FrameLayout ParentFrameLayout;
    private TextView explore;
    private homeAdapter homeAdapter;
    private RecyclerView homeRv;
    private ViewPager slider;
    ArrayList<String> homeCourseTitle=new ArrayList<>();
    ArrayList<String> homePosterList=new ArrayList<>();
    private homePosterAdapter posterAdapter;
    ArrayList<String> arrangedPosterList=new ArrayList<>();
    int currentPage;
    final static int DELAY_TIME=2000;
    final static int PERIOD_TIME=2000;
    Timer timer;
    private  TextView slidingText;
    String t="Welcome to the best application ever which is here to provide best courses with fully experienced instructors. Here we also provide chatBots and various other best support systems which will help user to communicate with us.";

   
    public fragment_home() {
        // Required empty public constructor
    }

   

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //initialise views
        homeRv=view.findViewById(R.id.homeRecyclerView);
        explore=view.findViewById(R.id.exploreTv);
        slider=view.findViewById(R.id.homeVp);
        slidingText=view.findViewById(R.id.slidingText);
        return view;
    }
     @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        
        super.onViewCreated(view, savedInstanceState);
        //initialising the main Activity Frame
         ParentFrameLayout=getActivity().findViewById(R.id.fragment_container);


         //text sliding implementation
         slidingText.setText(t);
         slidingText.setSelected(true);

         //end
        
        //operational statements
         homeCourseTitle.clear();  //to avoid duplicate items
         homePosterList.clear();  //to avoid duplicate items









         //todo:Realtime Database

         //Junk Code
         homeCourseTitle.add("Introduction to Kotlin");
         homeCourseTitle.add("Starting with Open Source");
         homeCourseTitle.add("DSA with JAVA");
         homeCourseTitle.add("Introduction to Python");
         homeCourseTitle.add("Master Course on Flutter and Dart");
         homeCourseTitle.add("Introduction to HTML5 and CSS3");
         //end Junk Code

         //Junk Code
         homePosterList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/Sliders%2Ffirst.jpg?alt=media&token=2dd49e53-e09a-49d3-8023-718c875abfdc");
         homePosterList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/Sliders%2Fsecond.jpg?alt=media&token=629caf42-d2f3-43f2-a025-f7469fe29be8");
         homePosterList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/Sliders%2Fthird.jpg?alt=media&token=69c350dd-58d7-47b3-ae72-ac583288ded2");
         //end Junk Code

         setPosters(homePosterList); //calling poster slider function


         //Setting Adapter and Grid Layout
         homeAdapter=new homeAdapter(homeCourseTitle);
         GridLayoutManager manager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
         manager.setOrientation(RecyclerView.VERTICAL);
         homeRv.setLayoutManager(manager);
         homeRv.setAdapter(homeAdapter);
         homeAdapter.notifyDataSetChanged();
         //end









         //explore Button Onclick Changing Fragment
         explore.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                 fragmentTransaction.setCustomAnimations(R.anim.slide_in,R.anim.fade_out);
                 fragmentTransaction.replace(ParentFrameLayout.getId(),new fragment_book());
                 fragmentTransaction.commit();
                 //todo:bottom nav indicator to course

             }
         });
        //end
    }

    private  void setPosters(ArrayList<String> l){

        if(timer!=null){
            timer.cancel();

        }

        currentPage=1;  //-->first url of main list
        //applying logic to make sliders infinite so modifying list
                for (int i=0;i<l.size();i++){
                    arrangedPosterList.add(l.get(i));
                }

                arrangedPosterList.add(0,l.get(l.size()-1));
                arrangedPosterList.add(l.get(0));

        //setting Adapter for poster Slider
        posterAdapter=new homePosterAdapter(arrangedPosterList);
        slider.setAdapter(posterAdapter);
        slider.setPageMargin(56);

        slider.setCurrentItem(currentPage);
        //end

        autoSliding(arrangedPosterList);

        ViewPager.OnPageChangeListener listener=new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if(state==ViewPager.SCROLL_STATE_IDLE){

                    loopingInfinitely(arrangedPosterList);

                }

            }
        };

        slider.addOnPageChangeListener(listener);

        slider.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                loopingInfinitely(arrangedPosterList);
                stopSlidingOnTouch();

                if(motionEvent.getAction()==MotionEvent.ACTION_UP){

                    autoSliding(arrangedPosterList);

                }
                return false;
            }
        });

    }

    private void loopingInfinitely(ArrayList<String> l){

        if(currentPage==l.size()-1){

            currentPage=1;
            slider.setCurrentItem(currentPage,false);

        }else if(currentPage==0){

            currentPage=l.size()-2;
            slider.setCurrentItem(currentPage,false);

        }

    }

    private void autoSliding(ArrayList<String> l){

        final Handler handler=new Handler();
        final Runnable run=new Runnable() {
            @Override
            public void run() {

                if(currentPage>=l.size()){

                    currentPage=0;

                }

                slider.setCurrentItem(currentPage++,true);

            }
        };

        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(run);

            }
        },DELAY_TIME,PERIOD_TIME);

    }

    private void stopSlidingOnTouch(){

        timer.cancel();

    }


}
