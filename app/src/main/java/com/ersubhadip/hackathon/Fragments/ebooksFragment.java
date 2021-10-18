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

import com.ersubhadip.hackathon.Classes.ebooksAdapter;
import com.ersubhadip.hackathon.R;

import java.util.ArrayList;
import java.util.List;


public class ebooksFragment extends Fragment {

    private List<String> ebooksTitle=new ArrayList<>();
    private List<String> ebooksLink=new ArrayList<>();
    private RecyclerView ebooksRv;
    private ebooksAdapter ebooksAdapter;
    int t;



    public ebooksFragment() {
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
        View view = inflater.inflate(R.layout.fragment_ebooks, container, false);
        //initialisation

        ebooksRv=view.findViewById(R.id.ebooksRv);
        //end
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //todo:fetch from db and link setting
        //junk code
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        ebooksTitle.add("This is the E-Book which is downloadable");
        //end

        //setting Linear layout manager to rv
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        ebooksRv.setLayoutManager(manager);
        //end

        //setting Adapter
        ebooksAdapter=new ebooksAdapter(ebooksTitle,ebooksLink);
        ebooksRv.setAdapter(ebooksAdapter);
        ebooksAdapter.notifyDataSetChanged();
        //end


    }
}