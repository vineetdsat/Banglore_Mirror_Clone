package com.example.recyclerview;

import android.content.Intent;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class rAdapter extends RecyclerView.Adapter<rAdapter.rViewHolder> {
    private List<Updates> news_lst;

    rAdapter( List<Updates> news_lst) {
        this.news_lst = news_lst;
    }


    @NonNull
    @Override
    public rViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View layoutModel = inflater.inflate(R.layout.list_item, parent, false);
        rViewHolder recycleViewHolder = new rViewHolder(layoutModel);
        return recycleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull rViewHolder holder, int position) {
        try {
            Updates updates = news_lst.get(position);
            holder.Headline.setText(updates.getHead());
            holder.Link.setText(updates.getLink());
            holder.des.setText(updates.getDescription());
            Picasso.get().load(news_lst.get(position).getImg_URL()).into(holder.Img_Url);




            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);


        }catch (Exception e){
            e.printStackTrace();
        }



    }

    @Override
    public int getItemCount() {
        return news_lst.size();
    }

    public class rViewHolder extends RecyclerView.ViewHolder{
        TextView Headline, Link, des;
        ImageView Img_Url;


        public rViewHolder(@NonNull View itemView) {
            super(itemView);

            Headline = itemView.findViewById(R.id.tv_1);
            Link = itemView.findViewById(R.id.tv_2);
            Img_Url = itemView.findViewById(R.id.coverImage);
            des = itemView.findViewById(R.id.tv_3);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), Description.class);
                    i.putExtra("des", news_lst.get(getAdapterPosition()));
                    v.getContext().startActivity(i);
                }
            });


        }
    }
}