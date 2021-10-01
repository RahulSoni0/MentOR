package com.ersubhadip.hackathon.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import com.ersubhadip.hackathon.Classes.booksModel;
import com.ersubhadip.hackathon.Classes.booksRvAdapter;
import com.ersubhadip.hackathon.R;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class fragment_book extends Fragment {

    private ArrayList<booksModel> list= new ArrayList<>();
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
         list.clear();   //to avoid duplicate elements due to multiple clicks



         //todo:Fetch data from firebase
         //JUNK CODE
         list.add(new booksModel("Arihant","DC Pandey",R.drawable.ic_launcher_background));
         list.add(new booksModel("Cengage","BM Sharma",R.drawable.ic_launcher_background));
         list.add(new booksModel("Dhanpat Rai","Premchand",R.drawable.ic_launcher_background));
         list.add(new booksModel("Concise","S chand",R.drawable.ic_launcher_background));
         list.add(new booksModel("Polity","Laxmikant",R.drawable.ic_launcher_background));
         list.add(new booksModel("Canberra","Vibhu Pandey",R.drawable.ic_launcher_background));
         list.add(new booksModel("Cengage","BM Sharma",R.drawable.ic_launcher_background));
         list.add(new booksModel("Dhanpat Rai","Premchand",R.drawable.ic_launcher_background));
         list.add(new booksModel("Dhanpat Rai","Premchand",R.drawable.ic_launcher_background));
         list.add(new booksModel("Dhanpat Rai","Premchand",R.drawable.ic_launcher_background));
         list.add(new booksModel("Dhanpat Rai","Premchand",R.drawable.ic_launcher_background));
         list.add(new booksModel("Dhanpat Rai","Premchand",R.drawable.ic_launcher_background));
         list.add(new booksModel("Dhanpat Rai","Premchand",R.drawable.ic_launcher_background));
         list.add(new booksModel("Dhanpat Rai","Premchand",R.drawable.ic_launcher_background));
         list.add(new booksModel("Dhanpat Rai","Premchand",R.drawable.ic_launcher_background));
         list.add(new booksModel("Dhanpat Rai","Premchand",R.drawable.ic_launcher_background));
         list.add(new booksModel("Dhanpat Rai","Premchand",R.drawable.ic_launcher_background));
         list.add(new booksModel("Dhanpat Rai","Premchand",R.drawable.ic_launcher_background));
         list.add(new booksModel("Dhanpat Rai","Premchand",R.drawable.ic_launcher_background));
         list.add(new booksModel("Dhanpat Rai","Premchand",R.drawable.ic_launcher_background));
         list.add(new booksModel("Cengage","BM Sharma",R.drawable.ic_launcher_background));
         list.add(new booksModel("Dhanpat Rai","Premchand",R.drawable.ic_launcher_background));
         //JUNK CODE ENDS

         booksAdapter=new booksRvAdapter(list);


         //setting up adapter and Gridlayout
         GridLayoutManager manager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
         manager.setOrientation(RecyclerView.VERTICAL);
         booksRV.setLayoutManager(manager);
         booksRV.setAdapter(booksAdapter);
         booksAdapter.notifyDataSetChanged();





        
    }
}
