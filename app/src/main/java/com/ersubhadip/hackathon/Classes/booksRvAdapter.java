package com.ersubhadip.hackathon.Classes;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.style.AlignmentSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ersubhadip.hackathon.Activity.TabedActivity;
import com.ersubhadip.hackathon.R;

import java.util.ArrayList;
import java.util.Random;

public class booksRvAdapter  extends RecyclerView.Adapter<booksRvAdapter.ViewHolder>{

    private ConstraintLayout layout;
    ArrayList<booksModel> list=new ArrayList<>();

    public booksRvAdapter(ArrayList<booksModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public booksRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.books_rv,parent,false);

        layout=view.findViewById(R.id.layoutConstraint);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull booksRvAdapter.ViewHolder holder, int position) {

        //setting colors list to fetch random colors
        ArrayList<String> colors=new ArrayList<>();
        colors.add("#48546E");
        colors.add("#662EC3");
        colors.add("#FC407D");
        colors.add("#4185F4");

        //end

        //Random Class Calling
        Random random=new Random();

        String c=colors.get(random.nextInt(colors.size()));
        //end
        holder.setData(list.get(position).getTitle(),list.get(position).getCaption(),c);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView booksImage;
        private TextView titleTv,captionTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            titleTv=itemView.findViewById(R.id.bookTitle);
            captionTv=itemView.findViewById(R.id.bookCaption);

        }

        public void setData(String title, String caption,String LayoutColors) {

            titleTv.setText(title);
            captionTv.setText(caption);


            layout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(LayoutColors)));  //setting the layout color

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent course=new Intent(itemView.getContext(), TabedActivity.class);
                    //todo:to pass req datas
                    itemView.getContext().startActivity(course);


                }
            });

        }


    }
}
