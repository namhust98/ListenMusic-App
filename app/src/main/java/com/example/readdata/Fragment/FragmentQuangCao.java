package com.example.readdata.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.readdata.Activity.MainActivity;
import com.example.readdata.Adapter.QuangcaoAdapter;
import com.example.readdata.Model.BaiHat;
import com.example.readdata.Model.QuangCao;
import com.example.readdata.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

import static java.lang.String.valueOf;

public class FragmentQuangCao extends Fragment {

    private View view;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private QuangcaoAdapter quangcaoAdapter;
    private Handler handler;
    private Runnable runnable;
    private int currentItem;
    private String data;
    public static List<BaiHat> mangbaihat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_quangcao, container, false);
        data = MainActivity.data;

        findView();
        getData();
        return view;
    }

    private void findView() {
        viewPager = view.findViewById(R.id.view_quang_cao);
        circleIndicator = view.findViewById(R.id.indicatordefault);
    }

    public void getData() {

        List<QuangCao> arrquangcao = new ArrayList<>();
        mangbaihat = new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(data);

            JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
            JSONObject object = jsonArray.getJSONObject(0);
            JSONArray array = new JSONArray(object.getString("flashhotEDM"));
            String[] url_image = new String[6];
            String[] name = new String[6];
            String[] url_media = new String[6];
            for (int i = 0; i < 6; i++) {
                JSONObject quangcao = array.getJSONObject(i);
                url_image[i] = quangcao.getString("image");
                name[i] = quangcao.getString("name");
                url_media[i] = quangcao.getString("media_url");

                arrquangcao.add(new QuangCao(url_image[i], name[i], url_media[i]));
                mangbaihat.add(new BaiHat(url_image[i], name[i], null, url_media[i], null, null));
            }

            quangcaoAdapter = new QuangcaoAdapter(getActivity(), arrquangcao);
            viewPager.setAdapter(quangcaoAdapter);
            circleIndicator.setViewPager(viewPager);

            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    currentItem = viewPager.getCurrentItem();
                    currentItem++;

                    if (currentItem >= viewPager.getAdapter().getCount()) {
                        currentItem = 0;
                    }

                    viewPager.setCurrentItem(currentItem, true);
                    handler.postDelayed(runnable, 8000);
                }
            };

            handler.postDelayed(runnable, 8000);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
