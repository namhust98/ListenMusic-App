package com.example.readdata.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.readdata.Activity.MainActivity;
import com.example.readdata.Adapter.AlbumAdapter;
import com.example.readdata.Adapter.CategoryAdapter;
import com.example.readdata.Adapter.MixTapeOfTheWeekAdapter;
import com.example.readdata.Adapter.VideoAdapter;
import com.example.readdata.Model.Album;
import com.example.readdata.Model.Category;
import com.example.readdata.Model.MixTapeOfTheWeek;
import com.example.readdata.Model.Video;
import com.example.readdata.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentallVideo extends Fragment {

    private View view;
    private RecyclerView recyclerViewVideo;
    private String data;
    private VideoAdapter videoAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video, container, false);

        data = MainActivity.data;

        findView();
        getData();
        return view;
    }

    private void findView(){
        recyclerViewVideo = view.findViewById(R.id.listVideo);
    }

    private void getData(){
        List<Video> arrVideo = new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(data);

            JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
            JSONObject object = jsonArray.getJSONObject(7);
            JSONArray array = new JSONArray(object.getString("videoHotEdm"));

            String[] url_image = new String[10];
            String[] name = new String[10];
            String[] title = new String[10];
            String[] url_media = new String[10];
            String[] luotxem = new String[10];

            for (int i = 0; i < 10; i++) {
                JSONObject video = array.getJSONObject(i);
                url_image[i] = video.getString("image");
                name[i] = video.getString("name");
                title[i] = video.getString("singer");
                url_media[i] = video.getString("media_url");
                luotxem[i] = video.getString("listen_no");

                arrVideo.add(new Video(url_image[i], name[i], title[i], url_media[i], luotxem[i]));
            }

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerViewVideo.setLayoutManager(linearLayoutManager);

            videoAdapter = new VideoAdapter(getActivity(), arrVideo);
            recyclerViewVideo.setAdapter(videoAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
