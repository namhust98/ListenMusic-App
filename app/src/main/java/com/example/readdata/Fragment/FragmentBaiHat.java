package com.example.readdata.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.readdata.Activity.PlayMusicActivity;
import com.example.readdata.Model.BaiHat;
import com.example.readdata.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentBaiHat extends Fragment {

    private View view;
    private static ImageView img;
    private static TextView name;
    private static TextView singer;
    private static TextView titlename;
    private static TextView luotnghe;
    private static BaiHat dataPlaymusic;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_baihat, container, false);
        dataPlaymusic = PlayMusicActivity.dataBaiHat;
        findView();
        getData(dataPlaymusic);
        return view;
    }

    private void findView(){
        titlename = view.findViewById(R.id.titlename_baihat);
        name = view.findViewById(R.id.name_baihat);
        img = view.findViewById(R.id.image_baihat);
        singer = view.findViewById(R.id.singer_baihat);
        luotnghe = view.findViewById(R.id.luotnghe_baihat);
    }

    public static void getData(BaiHat baiHat){
        titlename.setText(baiHat.getName());
        name.setText(baiHat.getName());
        Picasso.get()
                .load(baiHat.getUrlimage())
                .into(img);
        singer.setText(baiHat.getTitle());
        if (baiHat.getLuotnghe() != null) {
            luotnghe.setText(baiHat.getLuotnghe() + " Lượt nghe");
        }
    }

}
