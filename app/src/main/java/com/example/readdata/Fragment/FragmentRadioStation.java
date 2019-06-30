package com.example.readdata.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.readdata.Activity.MainActivity;
import com.example.readdata.Activity.PlayMusicActivity;
import com.example.readdata.Adapter.AlbumAdapter;
import com.example.readdata.Model.Album;
import com.example.readdata.Model.BaiHat;
import com.example.readdata.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentRadioStation extends Fragment {

    private View view;
    private ImageView[] img = new ImageView[4];
    private TextView[] name = new TextView[4];
    private TextView[] title = new TextView[4];
    private LinearLayout[] layout = new LinearLayout[4];
    private String data;
    private String[] url_image = new String[4];
    private String[] strname = new String[4];
    private String[] strtitle = new String[4];
    private String[] url_media = new String[4];
    public static List<BaiHat> mangbaihat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_radiostation, container, false);
        data = MainActivity.data;
        findView();
        getData();

        for (int i=0; i<4; i++){
            final int finalI = i;
            layout[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BaiHat baihat = new BaiHat(url_image[finalI], strname[finalI], strtitle[finalI], url_media[finalI], null, null);
                    Intent intent = new Intent(getContext(), PlayMusicActivity.class);
                    intent.putExtra("bai hat", baihat);
                    intent.putExtra("fragment", "RadioStation");
                    getContext().startActivity(intent);
                }
            });
        }
        return view;
    }

    private void findView(){
        img[0] = view.findViewById(R.id.image1_radiostation);
        img[1] = view.findViewById(R.id.image2_radiostation);
        img[2] = view.findViewById(R.id.image3_radiostation);
        img[3] = view.findViewById(R.id.image4_radiostation);

        name[0] = view.findViewById(R.id.name1_radiostation);
        name[1] = view.findViewById(R.id.name2_radiostation);
        name[2] = view.findViewById(R.id.name3_radiostation);
        name[3] = view.findViewById(R.id.name4_radiostation);

        title[0] = view.findViewById(R.id.title1_radiostation);
        title[1] = view.findViewById(R.id.title2_radiostation);
        title[2] = view.findViewById(R.id.title3_radiostation);
        title[3] = view.findViewById(R.id.title4_radiostation);

        layout[0] = view.findViewById(R.id.layout1_radiostation);
        layout[1] = view.findViewById(R.id.layout2_radiostation);
        layout[2] = view.findViewById(R.id.layout3_radiostation);
        layout[3] = view.findViewById(R.id.layout4_radiostation);
    }

    private void getData(){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(data);

            JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
            JSONObject object = jsonArray.getJSONObject(1);
            JSONArray array = new JSONArray(object.getString("radioStation"));
            mangbaihat = new ArrayList<>();


            for (int i = 0; i < 4; i++) {
                JSONObject album = array.getJSONObject(i);
                url_image[i] = album.getString("image");
                strname[i] = album.getString("name");
                strtitle[i] = album.getString("singer");
                url_media[i] = album.getString("media_url");

                Picasso.get()
                        .load(album.getString("image"))
                        .into(img[i]);
                name[i].setText(album.getString("name"));
                title[i].setText(album.getString("singer"));

                mangbaihat.add(new BaiHat(url_image[i], strname[i], strtitle[i], url_media[i], null, null));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
