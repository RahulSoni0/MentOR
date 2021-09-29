package com.ersubhadip.hackathon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeRvAdapter extends RecyclerView.Adapter<HomeRvAdapter.ViewHolder> {

    private List<HomeRvModel> list;

    public HomeRvAdapter(List<HomeRvModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HomeRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //main layout ko inflate karwa do baar baar
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homr_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRvAdapter.ViewHolder holder, int position) {

        //holder mein data bhar do is method se
        holder.setData(list.get(position).getTitle(),list.get(position).getCaption(),list.get(position).getImageAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView temp_image;
        private TextView temp_title,temp_caption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            temp_image = itemView.findViewById(R.id.home_rv_itemIcon);
            temp_caption = itemView.findViewById(R.id.home_rv_itemCaption);
            temp_title = itemView.findViewById(R.id.home_rv_itemTitle);
        }

        public void setData(String title, String caption, int imageAddress) {
            //ye dalal hai jo keval information convey karega aur kuch nahi.
            temp_title.setText(title);
            temp_caption.setText(caption);
            //below line ko update kar lena andtoid 22 ke hisaab se (depreceated hai)
            temp_image.setImageDrawable(itemView.getResources().getDrawable(imageAddress));//imageAddress ke corresponding koi image dhoondo aur usko
            //temp_image naamak ImageView mein set kar do
        }
    }
}
