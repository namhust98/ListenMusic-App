package com.example.readdata.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.readdata.ViewPagerAdapter.MainViewPagerAdapter;
import com.example.readdata.Fragment.FragmentTrangChu;
import com.example.readdata.R;
import com.example.readdata.Service.API;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    public static String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getdata();
    }

    public void init(){
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new FragmentTrangChu(), "Trang chu");
        viewPager.setAdapter(mainViewPagerAdapter);
    }
    public void findView(){
        viewPager = findViewById(R.id.myPager);
    }

    public void getdata(){

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.show();

        AsyncTask asyncTask = new AsyncTask() {
            @SuppressLint("WrongThread")
            @Override
            protected Object doInBackground(Object[] objects) {
                data = API.Request();
                return null;
            }

            protected void onPostExecute(Object o) {
                findView();
                init();
                dialog.dismiss();

            }
        }.execute();
    }
}
