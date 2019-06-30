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

public class FragmentLoiBaiHat extends Fragment {

    private View view;
    private TextView loibaihat, error;
    private BaiHat dataPlaymusic;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_loibaihat, container, false);
        dataPlaymusic = PlayMusicActivity.dataBaiHat;
        findView();
        getData();
        return view;
    }

    private void findView(){
        loibaihat = view.findViewById(R.id.loibaihat);
        error = view.findViewById(R.id.ishaveloi);
    }

    private void getData() {
        if ((dataPlaymusic.getLoibathat() != null)&&(!dataPlaymusic.getLoibathat().equals(""))) {
            loibaihat.setText(android.text.Html.fromHtml(dataPlaymusic.getLoibathat()));
            loibaihat.setVisibility(View.VISIBLE);
            error.setVisibility(View.GONE);
        }
    }

}
