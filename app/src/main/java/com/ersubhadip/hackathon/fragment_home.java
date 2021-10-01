package com.ersubhadip.hackathon;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class fragment_home extends Fragment {

    private RecyclerView homeRv;
    private List<HomeRvModel> homeRvList;//ek baar check kar lena ki yahan bhi initalize kar sakte hain ya only inside onViewCreated
   
    public fragment_home() {
        // Required empty public constructor
    }

   
    //##########  shayad is method ki zaroorat nahi hai so I am commenting it out ############
    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_home, container, false);
       homeRv=view.findViewById(R.id.homeRv);//homeRv ko intialize kar diya

        //initialise views
        return view;
}
     @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        
         super.onViewCreated(view, savedInstanceState);

         homeRvList=new ArrayList<>();
         homeRvList.add(new HomeRvModel("Arihant","DC Pandey",R.drawable.temp1));
         homeRvList.add(new HomeRvModel("Cengage","BM Sharma",R.drawable.temp2));
         homeRvList.add(new HomeRvModel("Dhanpat Rai","Premchand",R.drawable.temp1));
         homeRvList.add(new HomeRvModel("Concise","S chand",R.drawable.temp3));
         homeRvList.add(new HomeRvModel("Polity","Laxmikant",R.drawable.temp3));
         homeRvList.add(new HomeRvModel("Canberra","Vibhu Pandey",R.drawable.temp1));
         homeRvList.add(new HomeRvModel("Cengage","BM Sharma",R.drawable.temp2));
         homeRvList.add(new HomeRvModel("Dhanpat Rai","Premchand",R.drawable.temp1));
         homeRvList.add(new HomeRvModel("Cengage","BM Sharma",R.drawable.temp3));
         homeRvList.add(new HomeRvModel("Dhanpat Rai","Premchand",R.drawable.temp3));

         HomeRvAdapter adapter = new HomeRvAdapter(homeRvList);
         //LinearLayoutManager  poora copy paste:-----
//
//         LinearLayoutManager manager = new LinearLayoutManager(getContext());
//         manager.setOrientation(RecyclerView.HORIZONTAL);
//         homeRv.setLayoutManager(manager);
//         homeRv.setAdapter(adapter);
//         adapter.notifyDataSetChanged();

         //Humko Grid banana hai
         GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
         homeRv.setLayoutManager(gridLayoutManager);
         homeRv.setAdapter(adapter);
         homeRv.hasFixedSize();//pata nahi kya karti hai ye line

        
    }
}
