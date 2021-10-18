package com.ersubhadip.hackathon.Classes;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.text.style.AlignmentSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ersubhadip.hackathon.Activity.TabedActivity;
import com.ersubhadip.hackathon.R;

import java.util.ArrayList;
import java.util.Random;

public class booksRvAdapter  extends RecyclerView.Adapter<booksRvAdapter.ViewHolder>{

    public static int type;
    ArrayList<String> bannerUrlList=new ArrayList<>();

    public booksRvAdapter(ArrayList<String> bannerUrlList) {
        this.bannerUrlList = bannerUrlList;
    }

    @NonNull
    @Override
    public booksRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.books_rv,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull booksRvAdapter.ViewHolder holder, int position) {


        //end
        holder.setData(bannerUrlList.get(position));

    }

    @Override
    public int getItemCount() {
        return bannerUrlList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.courseBanner);

        }

        public void setData(String url) {


            Glide.with(itemView.getContext()).load(url).into(imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent course=new Intent(itemView.getContext(), TabedActivity.class);
                    type=getLayoutPosition();
                    itemView.getContext().startActivity(course);
                }
            });

        }


    }
}
