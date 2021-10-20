package com.ersubhadip.hackathon.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ersubhadip.hackathon.Activity.TabedActivity;
import com.ersubhadip.hackathon.Classes.VideoAdapter;
import com.ersubhadip.hackathon.Classes.booksRvAdapter;
import com.ersubhadip.hackathon.Classes.ebooksAdapter;
import com.ersubhadip.hackathon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class videoFragment extends Fragment {

    private List<String> videoList = new ArrayList<>();
    private List<String> VideoUrlList = new ArrayList<>();
    private RecyclerView VideoRv;
    private VideoAdapter videoAdapter;
    private ImageView courseBanner;
    FirebaseFirestore store;
    String t;






    public videoFragment() {
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
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        //initialisation
        VideoRv=view.findViewById(R.id.VideoTabRecyclerView);
        courseBanner=view.findViewById(R.id.courseImage);
        t= TabedActivity.t;
        //end

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        videoList.clear(); //to avoid duplicate items

        //todo:link setting
        store=FirebaseFirestore.getInstance();

        store.collection("courses").orderBy("orderNumber").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for(DocumentSnapshot snapshot:task.getResult()){

                    if(t.equals((String) snapshot.get("courseId"))){

                        videoList=(ArrayList<String>)snapshot.get("videoTitle");
                        String url=(String)snapshot.get("imageUrl");
                        Glide.with(getContext()).load(url).into(courseBanner);

                        if(videoList.get(0).equals("")){

                            videoList.clear();

                        }


                        //setting LinearLayoutManager
                        LinearLayoutManager manager=new LinearLayoutManager(getContext());
                        manager.setOrientation(RecyclerView.VERTICAL);
                        VideoRv.setLayoutManager(manager);

                        //end

                        //setting the adapter
                        videoAdapter=new VideoAdapter(VideoUrlList,videoList);
                        VideoRv.setAdapter(videoAdapter);
                        videoAdapter.notifyDataSetChanged();
                        //end

                    }

                }
            }
        });

    }
}