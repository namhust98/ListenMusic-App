package com.example.readdata.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.readdata.Activity.PlayMusicActivity;
import com.example.readdata.Adapter.ListBaiHatAdapter;
import com.example.readdata.Model.BaiHat;
import com.example.readdata.Model.TopTrack;
import com.example.readdata.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class FragmentListBaiHat extends Fragment {

    private View view;
    private ListView listBaiHat;
    private TextView txtError;
    private List<BaiHat> mangbaihat;
    private ListBaiHatAdapter listBaiHatAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_listbaihat, container, false);
        findView();
        getData();
        return view;
    }

    private void findView(){
        listBaiHat = view.findViewById(R.id.listlistbaihat);
        txtError = view.findViewById(R.id.ishavelist);
    }

    private void getData() {
        if (PlayMusicActivity.mangbaihat.size() > 0) {

            mangbaihat = PlayMusicActivity.mangbaihat;
            listBaiHatAdapter = new ListBaiHatAdapter(getActivity(), android.R.layout.simple_list_item_1, mangbaihat);
            listBaiHat.setAdapter(listBaiHatAdapter);
            listBaiHat.setVisibility(View.VISIBLE);
            txtError.setVisibility(View.GONE);
            setListViewHeightBasedOnChildren(listBaiHat);
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
