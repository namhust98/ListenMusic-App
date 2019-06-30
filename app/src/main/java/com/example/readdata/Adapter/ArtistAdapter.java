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
import com.example.readdata.Model.Artist;
import com.example.readdata.Model.Category;
import com.example.readdata.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {

    Context context;
    List<Artist> artists;

    public ArtistAdapter(Context context, List<Artist> artists){
        this.context = context;
        this.artists = artists;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_artist, parent, false);

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
        Artist artist = artists.get(position);
        Picasso.get()
                .load(artist.getUrlimage())
                .into(holder.imgArtist);
        holder.nameArtist.setText(artist.getName());
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgArtist;
        TextView nameArtist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgArtist = itemView.findViewById(R.id.image_artist);
            nameArtist = itemView.findViewById(R.id.name_artist);
        }
    }
}
