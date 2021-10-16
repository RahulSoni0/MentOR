package com.ersubhadip.hackathon.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ersubhadip.hackathon.Classes.booksRvAdapter;
import com.ersubhadip.hackathon.R;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class fragment_book extends Fragment {

    private ArrayList<String> bannerList= new ArrayList<>();
    private RecyclerView booksRV;
    private booksRvAdapter booksAdapter;




   
    public fragment_book() {
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
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        //initialisation
        booksRV=view.findViewById(R.id.booksRecyclerView);
        //end
        return view;
    }
     @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        
        super.onViewCreated(view, savedInstanceState);
       //operational statements
         bannerList.clear();   //to avoid duplicate elements due to multiple clicks





         //todo:Fetch data from firebase
         //JUNK CODE
         bannerList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fopen_source.png?alt=media&token=d95c1304-b64c-4663-bb46-a8970a4d0c8d");
         bannerList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fopen_source.png?alt=media&token=d95c1304-b64c-4663-bb46-a8970a4d0c8d");
         bannerList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fopen_source.png?alt=media&token=d95c1304-b64c-4663-bb46-a8970a4d0c8d");
         bannerList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fopen_source.png?alt=media&token=d95c1304-b64c-4663-bb46-a8970a4d0c8d");
         bannerList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fopen_source.png?alt=media&token=d95c1304-b64c-4663-bb46-a8970a4d0c8d");
         bannerList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fopen_source.png?alt=media&token=d95c1304-b64c-4663-bb46-a8970a4d0c8d");
         bannerList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fopen_source.png?alt=media&token=d95c1304-b64c-4663-bb46-a8970a4d0c8d");
         bannerList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fopen_source.png?alt=media&token=d95c1304-b64c-4663-bb46-a8970a4d0c8d");
         bannerList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fopen_source.png?alt=media&token=d95c1304-b64c-4663-bb46-a8970a4d0c8d");
         bannerList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fopen_source.png?alt=media&token=d95c1304-b64c-4663-bb46-a8970a4d0c8d");
         bannerList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fopen_source.png?alt=media&token=d95c1304-b64c-4663-bb46-a8970a4d0c8d");
         bannerList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fopen_source.png?alt=media&token=d95c1304-b64c-4663-bb46-a8970a4d0c8d");
         bannerList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fopen_source.png?alt=media&token=d95c1304-b64c-4663-bb46-a8970a4d0c8d");
         bannerList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fopen_source.png?alt=media&token=d95c1304-b64c-4663-bb46-a8970a4d0c8d");
         bannerList.add("https://firebasestorage.googleapis.com/v0/b/hackathon-a446b.appspot.com/o/course_banner%2Fopen_source.png?alt=media&token=d95c1304-b64c-4663-bb46-a8970a4d0c8d");
         //JUNK CODE END"

         booksAdapter=new booksRvAdapter(bannerList);


         //setting up adapter and Gridlayout
         GridLayoutManager manager=new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
         manager.setOrientation(RecyclerView.VERTICAL);
         booksRV.setLayoutManager(manager);
         booksRV.setAdapter(booksAdapter);
         booksAdapter.notifyDataSetChanged();





        
    }
}
