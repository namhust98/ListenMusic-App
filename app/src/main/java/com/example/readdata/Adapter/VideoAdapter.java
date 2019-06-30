package com.example.readdata.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.readdata.Activity.PlayMusicActivity;
import com.example.readdata.Activity.PlayVideoActivity;
import com.example.readdata.Interface.ItemClickListener;
import com.example.readdata.Model.Album;
import com.example.readdata.Model.BaiHat;
import com.example.readdata.Model.Category;
import com.example.readdata.Model.Video;
import com.example.readdata.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>  {

    private Context context;
    private List<Video> videos;

    public VideoAdapter(Context context, List<Video> videos){
        this.context = context;
        this.videos = videos;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_video, parent, false);

        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Video video = videos.get(position);
        Picasso.get()
                .load(video.getUrlimage())
                .into(holder.imgVideo);
        holder.nameVideo.setText(video.getName());
        holder.titleVideo.setText(video.getTitle());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Video video = new Video(videos.get(position).getUrlimage(), videos.get(position).getName(), videos.get(position).getTitle(), videos.get(position).getUrlmedia(), videos.get(position).getLuotxem());
                Intent intent = new Intent(context, PlayVideoActivity.class);
                intent.putExtra("video", video);
                intent.putExtra("fragment", "Video");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        ImageView imgVideo;
        TextView nameVideo, titleVideo;
        private ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgVideo = itemView.findViewById(R.id.image_video);
            nameVideo = itemView.findViewById(R.id.name_video);
            titleVideo = itemView.findViewById(R.id.title_video);

            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
