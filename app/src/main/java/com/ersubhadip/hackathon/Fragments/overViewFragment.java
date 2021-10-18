package com.ersubhadip.hackathon.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ersubhadip.hackathon.Classes.booksRvAdapter;
import com.ersubhadip.hackathon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


public class overViewFragment extends Fragment {

    int t;
    FirebaseFirestore store;
    String url, courseName, description, instructorName, instructorBio;
    private ImageView banner;
    private TextView title,des,instructorn,instructorb;
    private ProgressBar loadingBar;


    public overViewFragment() {
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
        View view= inflater.inflate(R.layout.fragment_over_view, container, false);
        //initialisation
        banner=view.findViewById(R.id.courseImage);
        title=view.findViewById(R.id.titleCourse);
        des=view.findViewById(R.id.courseDescription);
        instructorb=view.findViewById(R.id.aboutCoach);
        instructorn=view.findViewById(R.id.nameCoach);
        loadingBar=view.findViewById(R.id.loading);
        t= booksRvAdapter.type;
        //end
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //functional statements
        store=FirebaseFirestore.getInstance();

        //todo:network handling



            loadingBar.setVisibility(View.VISIBLE);

            fetchData();

            loadingBar.setVisibility(View.GONE);




    }

    private void fetchData(){


        store.collection("courses").orderBy("orderNumber").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot snapshot:task.getResult()) {



                        if((Long)snapshot.get("orderNumber")==Long.valueOf(t+1)){

                            //fetch now from that particular snap
                            //to fetch-->banner url, course name, description, instructorName, InstructorBio
                            instructorName=(String)snapshot.get("InstructorName");
                            instructorBio=(String)snapshot.get("InstructorBio");
                            courseName=(String)snapshot.get("name");
                            url=(String)snapshot.get("imageUrl");
                            description=(String)snapshot.get("description");
                            //fetched all

                            setData();
                            break;



                        }


                    }



                }


        });



    }
    private void setData(){

        Glide.with(getContext()).load(url).into(banner);
        title.setText(courseName);
        instructorn.setText(instructorName);
        instructorb.setText(instructorBio);

        //html parsing description
        des.setText(Html.fromHtml(description));
        //end

    }
}