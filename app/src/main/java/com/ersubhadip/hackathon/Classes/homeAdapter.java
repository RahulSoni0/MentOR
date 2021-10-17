package com.ersubhadip.hackathon.Classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ersubhadip.hackathon.R;

import java.util.ArrayList;

public class homeAdapter  extends RecyclerView.Adapter<homeAdapter.ViewHolder>{

    ArrayList<String> list = new ArrayList<>();

    public homeAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public homeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_grid_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull homeAdapter.ViewHolder holder, int position) {

        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView homeTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            homeTitle=itemView.findViewById(R.id.homeCoursetitle);
        }

        private void setData(String s){

            homeTitle.setText(s);

        }

    }
}
