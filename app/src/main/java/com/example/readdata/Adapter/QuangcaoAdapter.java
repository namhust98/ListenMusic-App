package com.example.readdata.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.readdata.Activity.PlayMusicActivity;
import com.example.readdata.Model.BaiHat;
import com.example.readdata.Model.QuangCao;
import com.example.readdata.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuangcaoAdapter extends PagerAdapter {

    private Context context;
    private List<QuangCao> ListQuangcao;
    private ImageView imageViewQuangCao;
    private BaiHat baihat;

    public QuangcaoAdapter(Context context, List<QuangCao> ListQuangcao){
        this.context = context;
        this.ListQuangcao = ListQuangcao;
    }
    @Override
    public int getCount() {
        return ListQuangcao.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_quangcao, null);

        imageViewQuangCao = (ImageView) view.findViewById(R.id.imageview_QuangCao);

        Picasso.get()
                .load(ListQuangcao.get(position).getUrlimage())
                .into(imageViewQuangCao);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuangCao quangcao = ListQuangcao.get(position);
                baihat = new BaiHat(quangcao.getUrlimage(),quangcao.getName(), null, quangcao.getUrlmedia(), null, null);
                Intent intent = new Intent(context, PlayMusicActivity.class);
                intent.putExtra("bai hat", baihat);
                intent.putExtra("fragment", "QuangCao");
                context.startActivity(intent);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
