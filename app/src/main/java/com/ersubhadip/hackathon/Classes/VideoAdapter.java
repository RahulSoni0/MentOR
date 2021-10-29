package com.ersubhadip.hackathon.Classes;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ersubhadip.hackathon.Activity.VideoPlayerActivity;
import com.ersubhadip.hackathon.R;
import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{

    List<String> urlList;
    List<String> TitleList;

    public VideoAdapter(List<String> urlList, List<String> titleList) {
        this.urlList = urlList;
        TitleList = titleList;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_layout,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {

        holder.setData(TitleList.get(position),R.drawable.play);

    }

    @Override
    public int getItemCount() {
        return TitleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView t;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.videoIcon);
            t=itemView.findViewById(R.id.videoTitle);

            //Listeners to rv items
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent it=new Intent(itemView.getContext(), VideoPlayerActivity.class);
                    it.putExtra("Title",TitleList.get(getLayoutPosition()));
//                    it.putExtra("Link",urlList.get(getLayoutPosition()));
                    itemView.getContext().startActivity(it);
                }
            });

        }

        private void setData(String title,int icon){
            t.setText(title);
            //todo:Glide
            img.setImageDrawable(itemView.getResources().getDrawable(icon));


        }
    }
}
