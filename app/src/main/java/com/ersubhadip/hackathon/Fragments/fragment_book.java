package com.ersubhadip.hackathon.Fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ersubhadip.hackathon.Activity.SignUpActivity;
import com.ersubhadip.hackathon.Classes.booksRvAdapter;
import com.ersubhadip.hackathon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class fragment_book extends Fragment {

    ArrayList<String> bannerList= new ArrayList<>();
    private RecyclerView booksRV;
    private booksRvAdapter booksAdapter;
    private FirebaseFirestore firebaseFirestore;
    public  static String CourseTitle, CourseDescription, InstructorName, InstructorBio, BannerUrl;
    public static ArrayList<String> ebookTitle = new ArrayList<>();
    public static ArrayList<String> videoTitle = new ArrayList<>();
    public static ArrayList<String> ebookUrl = new ArrayList<>();
    public static ArrayList<String> videoUrl = new ArrayList<>();




   
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
        firebaseFirestore=FirebaseFirestore.getInstance();
        //end
        return view;
    }
     @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        
        super.onViewCreated(view, savedInstanceState);
       //operational statements
         bannerList.clear();   //to avoid duplicate elements due to multiple clicks





         //Loading Dialog Creation
         Dialog d1=new Dialog(getContext());
         d1.setContentView(R.layout.loading_dialogs);
         d1.getWindow().setBackgroundDrawable(getContext().getDrawable(R.drawable.round_bg)); //todo:remove suppressLint function not applied for bg
         d1.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
         d1.setCancelable(false);
         //end
         d1.show();

         //fetch Starts
         firebaseFirestore.collection("courses").orderBy("orderNumber").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
             @Override
             public void onComplete(@NonNull Task<QuerySnapshot> task) {

                 if(task.isSuccessful()){
                     //to fetch banners and other keys and adding banner url to bannerList
                     for (DocumentSnapshot snap : task.getResult()){
                         BannerUrl=(String)snap.get("imageUrl");
                         bannerList.add(BannerUrl);

//                         CourseTitle=(String)snap.get("name");

//                         CourseDescription=(String)snap.get("description");
//                         InstructorName=(String)snap.get("InstructorName");
//                         InstructorBio=(String)snap.get("InstructorBio");
//                         ebookTitle=(List)snap.get("ebookTitle");
//                         videoTitle=(List)snap.get("videoTitle");
//                         ebookUrl=(List)snap.get("ebookTitle");
//                         videoUrl=(List)snap.get("ebookTitle");
                         //todo:fetch urls
                     }
                     //end
                     //setting up adapter and Gridlayout
                     booksAdapter=new booksRvAdapter(bannerList);
                     GridLayoutManager manager=new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
                     manager.setOrientation(RecyclerView.VERTICAL);
                     booksRV.setLayoutManager(manager);
                     booksRV.setAdapter(booksAdapter);
                     booksAdapter.notifyDataSetChanged();
                     d1.dismiss(); //dismissing dialog
                     //todo:not getting fully scrolled


                 }else{
                     d1.dismiss();
                     Toast.makeText(getContext(), "Something Went Wrong! "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                 }




             }
         });












        
    }
}
