package com.example.readdata.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.readdata.Activity.PlayMusicActivity;
import com.example.readdata.Model.BaiHat;
import com.example.readdata.Model.QuangCao;
import com.example.readdata.Model.TopTrack;
import com.example.readdata.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopTrackAdapter extends ArrayAdapter<TopTrack> {

    public TopTrackAdapter(Context context, int resource, List<TopTrack> listTopTrack) {
        super(context, resource, listTopTrack);
    }

    class ViewHolder{
        ImageView img;
        TextView name;
        TextView title;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_toptrack, null);
            viewHolder = new ViewHolder();
            viewHolder.img = convertView.findViewById(R.id.image_toptrack);
            viewHolder.name = convertView.findViewById(R.id.name_toptrack);
            viewHolder.title = convertView.findViewById(R.id.title_toptrack);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final TopTrack topTrack = getItem(position);
        Picasso.get()
                .load(topTrack.getUrlimage())
                .into(viewHolder.img);
        viewHolder.name.setText(topTrack.getName());
        viewHolder.title.setText(topTrack.getTitle());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaiHat baihat = new BaiHat(topTrack.getUrlimage(),topTrack.getName(), topTrack.getTitle(), topTrack.getUrlmedia(), topTrack.getLuotnghe(), topTrack.getLoibaihat());
                Intent intent = new Intent(getContext(), PlayMusicActivity.class);
                intent.putExtra("bai hat", baihat);
                intent.putExtra("fragment", "TopTrack");
                getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
