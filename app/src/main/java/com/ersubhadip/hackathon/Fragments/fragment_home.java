package com.ersubhadip.hackathon.Fragments;

import android.app.Dialog;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ersubhadip.hackathon.Classes.homePosterAdapter;
import com.ersubhadip.hackathon.Classes.home_adapter;
import com.ersubhadip.hackathon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import in.codeshuffle.typewriterview.TypeWriterView;
import nl.joery.animatedbottombar.AnimatedBottomBar;


public class fragment_home extends Fragment {

    private FrameLayout ParentFrameLayout;
    private TextView explore;
    private home_adapter homeAdapter;
    private AnimatedBottomBar BottomBar;
    private RecyclerView homeRv;
    ArrayList<String> homeCourseurl=new ArrayList<>();
    ArrayList<Integer> id=new ArrayList<>();

    private ViewPager slider;
    ArrayList<String> homePosterList=new ArrayList<>();
    private homePosterAdapter posterAdapter;
    ArrayList<String> arrangedPosterList=new ArrayList<>();
    int currentPage;
    final static int DELAY_TIME=2000;
    final static int PERIOD_TIME=2000;
    Timer timer;
    private  TextView slidingText;
    String t="Welcome to the best application ever which is here to provide best courses with fully experienced instructors. Here we also provide chatBots and various other best support systems which will help user to communicate with us.";
    private TypeWriterView name;
    private FloatingActionButton fabBtn;

    FirebaseAuth auth;
    FirebaseFirestore store;

   
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
        name=view.findViewById(R.id.userName);
        auth=FirebaseAuth.getInstance();
        store=FirebaseFirestore.getInstance();
        fabBtn=view.findViewById(R.id.chatBot);
        return view;

    }
     @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        
        super.onViewCreated(view, savedInstanceState);
        //initialising the main Activity Frame
         ParentFrameLayout=getActivity().findViewById(R.id.fragment_container);
         BottomBar=getActivity().findViewById(R.id.animatedBottomBar);

         //setting up dialog for home popup
         Dialog dialog=new Dialog(getContext());
         dialog.setCancelable(false);
         dialog.setContentView(R.layout.home_main_dialog);
         dialog.getWindow().setBackgroundDrawable(getActivity().getDrawable(R.drawable.main_dialog_bg));
         dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
         ImageView i=dialog.findViewById(R.id.close_dialog);

         dialog.show();
         i.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 dialog.dismiss();

             }
         });
         //end


         //text sliding implementation
         slidingText.setText(t);
         slidingText.setSelected(true);

         //end
        
        //operational statements
         homeCourseurl.clear();  //to avoid duplicate items
         homePosterList.clear();  //to avoid duplicate items
         id.clear();  //to avoid duplicate items



         //Fetching user name
         store.collection("users").document(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
             @Override
             public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                   DocumentSnapshot snapshot = task.getResult();
                   String fetchedName=(String)snapshot.get("name");
                   fetchedName=fetchedName.trim();
                 //setting up typeWriterText
                 name.setDelay(2);
                 name.setWithMusic(false);
                 if(fetchedName.indexOf(" ")!=-1){

                     name.animateText("Hello, "+fetchedName.substring(0,fetchedName.indexOf(" ")));

                 }else{

                     name.animateText("Hello, "+fetchedName);

                 }


             }
         });
         //end

         fabBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 //todo:chatBot
                 Toast.makeText(getContext(), "Under development", Toast.LENGTH_SHORT).show();

             }
         });


         //todo:Realtime Database
         //Junk Code
         homeCourseurl.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fjs.jpg?alt=media&token=b41d2292-36d5-4df7-93f3-d898c08cda5d");
         homeCourseurl.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fjs.jpg?alt=media&token=b41d2292-36d5-4df7-93f3-d898c08cda5d");
         homeCourseurl.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fjs.jpg?alt=media&token=b41d2292-36d5-4df7-93f3-d898c08cda5d");
         homeCourseurl.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fjs.jpg?alt=media&token=b41d2292-36d5-4df7-93f3-d898c08cda5d");
         homeCourseurl.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fjs.jpg?alt=media&token=b41d2292-36d5-4df7-93f3-d898c08cda5d");
         homeCourseurl.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fjs.jpg?alt=media&token=b41d2292-36d5-4df7-93f3-d898c08cda5d");
         //end Junk Code

         //hardcoded url's for sliders
         homePosterList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/Sliders%2Ffirst.jpg?alt=media&token=2dd49e53-e09a-49d3-8023-718c875abfdc");
         homePosterList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/Sliders%2Fsecond.jpg?alt=media&token=629caf42-d2f3-43f2-a025-f7469fe29be8");
         homePosterList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/Sliders%2Fthird.jpg?alt=media&token=69c350dd-58d7-47b3-ae72-ac583288ded2");
         //end

         setPosters(homePosterList); //calling poster slider function


         //Setting Adapter and Grid Layout
         homeAdapter=new home_adapter(homeCourseurl,id);
         GridLayoutManager manager=new GridLayoutManager(getContext(),2);
         manager.setOrientation(RecyclerView.VERTICAL);
         homeRv.setLayoutManager(manager);
         homeRv.setAdapter(homeAdapter);
         homeAdapter.notifyDataSetChanged();
         //end
//         homePosterList.add("xSCa");
//
//       homePosterList.add("xSCa");
//       homePosterList.add("xSCa");
//       homePosterList.add("xSCa");
//
//         homePosterList.add("xSCa");
//
//         posterAdapter=new homePosterAdapter(homePosterList);
//         slider.setPageMargin(20);
//         slider.setAdapter(posterAdapter);
//









         //explore Button Onclick Changing Fragment
         explore.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                 fragmentTransaction.setCustomAnimations(R.anim.slide_in,R.anim.fade_out);
                 fragmentTransaction.replace(ParentFrameLayout.getId(),new fragment_book());
                 fragmentTransaction.commit();
                 BottomBar.selectTabById(R.id.courses,true);

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
