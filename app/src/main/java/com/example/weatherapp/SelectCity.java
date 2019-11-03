package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SelectCity extends AppCompatActivity implements View.OnClickListener {

    private ImageView backBtn;
    private ListView cityListLv;
    private List<City> mCityList;
    private MyApplication mApplication;
    private ArrayList<String> mArrayList;

    private static String updateCode;
    private String updateCityCode="-1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);

        backBtn = (ImageView)findViewById(R.id.title_selectCity_back);
        backBtn.setOnClickListener(this);

        mApplication = (MyApplication)getApplication();
        mCityList = mApplication.getCitylist();
        mArrayList = new ArrayList<String>();
        for(int i =0 ;i<mCityList.size();i++){

            String cityName = mCityList.get(i).getCity();
            mArrayList.add(cityName);

        }

        cityListLv = (ListView)findViewById(R.id.selectcity_lv);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_1,mArrayList);
        cityListLv.setAdapter(adapter);



        //Listview项的点击事件
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int updateCityCode = Integer.parseInt(mCityList.get(position).getNumber());
                //获取code
                updateCode =  Integer.toString(updateCityCode);
                Log.d("onItemClick: ", Integer.toString(updateCityCode));
            }
        };

        cityListLv.setOnItemClickListener(itemClickListener);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.title_selectCity_back:
               // finish();
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("citycode",updateCode);
                startActivity(intent);

                break;
            default:
                break;
        }


    }



}
