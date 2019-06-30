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
import com.example.readdata.Adapter.CategoryAdapter;
import com.example.readdata.Adapter.MixTapeOfTheWeekAdapter;
import com.example.readdata.Model.Category;
import com.example.readdata.Model.MixTapeOfTheWeek;
import com.example.readdata.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentCategory extends Fragment {

    private View view;
    private RecyclerView recyclerViewCategory;
    private String data;
    private CategoryAdapter categoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_category, container, false);

        data = MainActivity.data;

        findView();
        getData();
        return view;
    }

    private void findView(){
        recyclerViewCategory = view.findViewById(R.id.listCategory);
    }

    private void getData(){
        List<Category> arrCategory = new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(data);

            JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
            JSONObject object = jsonArray.getJSONObject(5);
            JSONArray array = new JSONArray(object.getString("categoryEdm"));

            String[] url_image = new String[10];

            for (int i = 0; i < 10; i++) {
                JSONObject category = array.getJSONObject(i);
                url_image[i] = category.getString("image");

                arrCategory.add(new Category(url_image[i]));
            }

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerViewCategory.setLayoutManager(linearLayoutManager);

            categoryAdapter = new CategoryAdapter(getActivity(), arrCategory);
            recyclerViewCategory.setAdapter(categoryAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
