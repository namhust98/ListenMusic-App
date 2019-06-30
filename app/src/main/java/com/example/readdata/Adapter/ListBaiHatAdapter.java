package com.example.readdata.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.readdata.Activity.PlayMusicActivity;
import com.example.readdata.Fragment.FragmentBaiHat;
import com.example.readdata.Model.BaiHat;
import com.example.readdata.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListBaiHatAdapter extends ArrayAdapter<BaiHat> {

    public ListBaiHatAdapter(Context context, int resource, List<BaiHat> baiHats) {
        super(context, resource, baiHats);
    }

    class ViewHolder{
        ImageView img;
        TextView name;
        TextView title;
        TextView luotnghe;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_listbaihat, null);
            viewHolder = new ViewHolder();
            viewHolder.img = convertView.findViewById(R.id.image_listbaihat);
            viewHolder.name = convertView.findViewById(R.id.name_listbaihat);
            viewHolder.title = convertView.findViewById(R.id.title_listbaihat);
            viewHolder.luotnghe = convertView.findViewById(R.id.luotnghe_listbaihat);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final BaiHat baiHat = getItem(position);
        Picasso.get()
                .load(baiHat.getUrlimage())
                .into(viewHolder.img);
        viewHolder.name.setText(baiHat.getName());
        viewHolder.title.setText(baiHat.getTitle());
        viewHolder.luotnghe.setText(baiHat.getLuotnghe());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayMusicActivity.chuyenbaihat(position);
            }
        });
        return convertView;
    }
}
