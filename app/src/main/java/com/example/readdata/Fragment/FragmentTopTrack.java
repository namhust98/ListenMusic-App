package com.example.readdata.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.readdata.Activity.MainActivity;
import com.example.readdata.Adapter.QuangcaoAdapter;
import com.example.readdata.Adapter.TopTrackAdapter;
import com.example.readdata.Model.BaiHat;
import com.example.readdata.Model.QuangCao;
import com.example.readdata.Model.TopTrack;
import com.example.readdata.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class FragmentTopTrack extends Fragment {

    private View view;
    public static List<BaiHat> mangbaihat;
    private String data;
    private ListView listTopTrack;
    private TopTrackAdapter topTrackAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_toptrack, container, false);
        data = MainActivity.data;
        findView();
        getData();
        return view;
    }

    private void findView(){
        listTopTrack = view.findViewById(R.id.listopTrack);
    }

    private void getData(){
        List<TopTrack> arrtoptrack = new ArrayList<>();
        mangbaihat = new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(data);

            JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
            JSONObject object = jsonArray.getJSONObject(2);
            JSONArray array = new JSONArray(object.getString("bxhEdm"));
            JSONObject object1 = array.getJSONObject(0);
            JSONArray listsong = new JSONArray(object1.getString("listSong"));

            String[] url_image = new String[5];
            String[] name = new String[5];
            String[] title = new String[5];
            String[] url_media = new String[5];
            String[] luotnghe = new String[5];
            String[] loibaihat = new String[5];
            for (int i = 0; i < 5; i++) {
                JSONObject baihat = listsong.getJSONObject(i);
                url_image[i] = baihat.getString("image");
                name[i] = baihat.getString("name");
                title[i] = baihat.getString("singer");
                url_media[i] = baihat.getString("media_url");
                luotnghe[i] = baihat.getString("listen_no");
                loibaihat[i] = baihat.getString("lyric");

                arrtoptrack.add(new TopTrack(url_image[i], name[i], title[i], url_media[i], luotnghe[i], loibaihat[i]));
                mangbaihat.add(new BaiHat(url_image[i], name[i], title[i], url_media[i], luotnghe[i], loibaihat[i]));
            }

            topTrackAdapter = new TopTrackAdapter(getActivity(),android.R.layout.simple_list_item_1, arrtoptrack);
            listTopTrack.setAdapter(topTrackAdapter);
            setListViewHeightBasedOnChildren(listTopTrack);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
