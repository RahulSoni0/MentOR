package com.ersubhadip.hackathon.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ersubhadip.hackathon.Classes.homeAdapter;
import com.ersubhadip.hackathon.R;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;


public class fragment_home extends Fragment {

    private FrameLayout ParentFrameLayout;
    private TextView explore;
    private homeAdapter homeAdapter;
    private RecyclerView homeRv;
    ArrayList<String> homeCourseTitle=new ArrayList<>();

   
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
        return view;
    }
     @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        
        super.onViewCreated(view, savedInstanceState);
        //initialising the main Activity Frame
         ParentFrameLayout=getActivity().findViewById(R.id.fragment_container);
        
        //operational statements
         homeCourseTitle.clear();  //to avoid duplicate items









         //todo:Realtime Database

         //Junk Code
         homeCourseTitle.add("Introduction to Kotlin");
         homeCourseTitle.add("Starting with Open Source");
         homeCourseTitle.add("DSA with JAVA");
         homeCourseTitle.add("Introduction to Python");
         homeCourseTitle.add("Master Course on Flutter and Dart");
         homeCourseTitle.add("Introduction to HTML5 and CSS3");
         //end Junk Code


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


}
