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

import com.ersubhadip.hackathon.Classes.VideoAdapter;
import com.ersubhadip.hackathon.R;

import java.util.ArrayList;
import java.util.List;


public class videoFragment extends Fragment {

    private List<String> videoList = new ArrayList<>();
    private List<String> VideoUrlList = new ArrayList<>();
    private RecyclerView VideoRv;
    private VideoAdapter videoAdapter;






    public videoFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        //initialisation
        VideoRv=view.findViewById(R.id.VideoTabRecyclerView);

        //end

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        videoList.clear(); //to avoid duplicate items

        //todo:fetch data from db
        //junk code
        videoList.add("Introduction to Open Source");
        videoList.add("Introduction to Open Source");
        videoList.add("Introduction to Open Source");
        videoList.add("Introduction to Open Source");
        videoList.add("Introduction to Open Source");
        videoList.add("Introduction to Open Source");
        videoList.add("Introduction to Open Source");
        videoList.add("Introduction to Open Source");
        videoList.add("Introduction to Open Source");
        //end of junk code

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