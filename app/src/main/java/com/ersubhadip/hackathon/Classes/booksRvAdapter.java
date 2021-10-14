package com.ersubhadip.hackathon.Classes;

import android.text.style.AlignmentSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ersubhadip.hackathon.R;

import java.util.ArrayList;

public class booksRvAdapter  extends RecyclerView.Adapter<booksRvAdapter.ViewHolder>{

    ArrayList<booksModel> list=new ArrayList<>();

    public booksRvAdapter(ArrayList<booksModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public booksRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.books_rv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull booksRvAdapter.ViewHolder holder, int position) {

        holder.setData(list.get(position).getTitle(),list.get(position).getCaption(),list.get(position).getImageAddress());

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

            booksImage=itemView.findViewById(R.id.bookImg);
            titleTv=itemView.findViewById(R.id.bookTitle);
            captionTv=itemView.findViewById(R.id.bookCaption);

        }

        public void setData(String title, String caption, int imageAddress) {

            titleTv.setText(title);
            captionTv.setText(caption);
            //todo:use Glide below
            booksImage.setImageDrawable(itemView.getResources().getDrawable(imageAddress));

        }


    }
}
