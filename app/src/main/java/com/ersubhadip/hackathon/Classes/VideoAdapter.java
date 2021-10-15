package com.ersubhadip.hackathon.Classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ersubhadip.hackathon.R;
import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{

    List<String> urlList=new ArrayList<>();
    List<String> TitleList=new ArrayList<>();

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
        }

        private void setData(String title,int icon){
            t.setText(title);
            //todo:Glide
            img.setImageDrawable(itemView.getResources().getDrawable(icon));


        }
    }
}
