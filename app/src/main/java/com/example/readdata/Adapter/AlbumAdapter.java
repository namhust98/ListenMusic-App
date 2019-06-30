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

import com.example.readdata.Activity.WaitUpdateActivity;
import com.example.readdata.Model.Album;
import com.example.readdata.Model.Category;
import com.example.readdata.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    Context context;
    List<Album> albums;

    public AlbumAdapter(Context context, List<Album> albums){
        this.context = context;
        this.albums = albums;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_album, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WaitUpdateActivity.class);
                context.startActivity(intent);
            }
        });
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Album album = albums.get(position);
        Picasso.get()
                .load(album.getUrlimage())
                .into(holder.imgAlbum);
        holder.nameAlbum.setText(album.getName());
        holder.titleAlbum.setText(album.getTitle());
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAlbum;
        TextView nameAlbum, titleAlbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAlbum = itemView.findViewById(R.id.image_album);
            nameAlbum = itemView.findViewById(R.id.name_album);
            titleAlbum = itemView.findViewById(R.id.title_album);
        }
    }
}
