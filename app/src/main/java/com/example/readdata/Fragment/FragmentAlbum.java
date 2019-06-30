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
import com.example.readdata.Model.Album;
import com.example.readdata.Model.Category;
import com.example.readdata.Model.MixTapeOfTheWeek;
import com.example.readdata.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentAlbum extends Fragment {

    private View view;
    private RecyclerView recyclerViewAlbum;
    private String data;
    private AlbumAdapter albumAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album, container, false);

        data = MainActivity.data;

        findView();
        getData();
        return view;
    }

    private void findView(){
        recyclerViewAlbum = view.findViewById(R.id.listAlbum);
    }

    private void getData(){
        List<Album> arrAlbum = new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(data);

            JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
            JSONObject object = jsonArray.getJSONObject(6);
            JSONArray array = new JSONArray(object.getString("albumHotEdm"));

            String[] url_image = new String[10];
            String[] name = new String[10];
            String[] title = new String[10];

            for (int i = 0; i < 10; i++) {
                JSONObject album = array.getJSONObject(i);
                url_image[i] = album.getString("image");
                name[i] = album.getString("name");
                title[i] = album.getString("singer");

                arrAlbum.add(new Album(url_image[i], name[i], title[i]));
            }

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerViewAlbum.setLayoutManager(linearLayoutManager);

            albumAdapter = new AlbumAdapter(getActivity(), arrAlbum);
            recyclerViewAlbum.setAdapter(albumAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
