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
import com.example.readdata.Model.BaiHat;
import com.example.readdata.Model.MixTapeOfTheWeek;
import com.example.readdata.Model.QuangCao;
import com.example.readdata.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MixTapeOfTheWeekAdapter extends ArrayAdapter<MixTapeOfTheWeek> {


    public MixTapeOfTheWeekAdapter(Context context, int resource, List<MixTapeOfTheWeek> listMixtape) {
        super(context, resource, listMixtape);
    }

    class ViewHolder{
        ImageView img;
        TextView name;
        TextView title;
        TextView docquyen;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_mixtapeoftheweek, null);
            viewHolder = new ViewHolder();
            viewHolder.img = convertView.findViewById(R.id.image_mixtapeoftheweek);
            viewHolder.name = convertView.findViewById(R.id.name_mixtapeoftheweek);
            viewHolder.title = convertView.findViewById(R.id.title_mixtapeoftheweek);
            viewHolder.docquyen = convertView.findViewById(R.id.isDocquyen_mixtapeoftheweek);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final MixTapeOfTheWeek mixtapeOfTheWeek = getItem(position);
        Picasso.get()
                .load(mixtapeOfTheWeek.getUrlimage())
                .into(viewHolder.img);
        viewHolder.name.setText(mixtapeOfTheWeek.getName());
        viewHolder.title.setText(mixtapeOfTheWeek.getTitle());
        if(mixtapeOfTheWeek.getDocquyen().equals("1")){
            viewHolder.docquyen.setVisibility(View.VISIBLE);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaiHat baihat = new BaiHat(mixtapeOfTheWeek.getUrlimage(),mixtapeOfTheWeek.getName(), mixtapeOfTheWeek.getTitle(), mixtapeOfTheWeek.getUrlmedia(), mixtapeOfTheWeek.getLuotnghe(), mixtapeOfTheWeek.getLoibaihat());
                Intent intent = new Intent(getContext(), PlayMusicActivity.class);
                intent.putExtra("bai hat", baihat);
                intent.putExtra("fragment", "MixTapeOfTheWeek");
                getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
