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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SelectCity extends AppCompatActivity implements View.OnClickListener {

    private ImageView backBtn;
    private ListView cityListLv;
    private EditText searchEt;
    private ImageView searchBtn;

    private List<City> mCityList;
    private MyApplication mApplication;
    private ArrayList<String> mArrayList;
    ArrayAdapter<String> adapter;

    private static String updateCode;
    private String updateCityCode="-1";
    private String updateCode1="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);

        backBtn = (ImageView)findViewById(R.id.title_selectCity_back);
        backBtn.setOnClickListener(this);

        searchEt = (EditText)findViewById(R.id.selectcity_search);
        searchBtn= (ImageView)findViewById(R.id.selectcity_search_button);
        searchBtn.setOnClickListener(this);



        mApplication = (MyApplication)getApplication();
        mCityList = mApplication.getCitylist();
        mArrayList = new ArrayList<String>();
        for(int i =0 ;i<mCityList.size();i++){

            String No_ = Integer.toString(i+1);
            String number = mCityList.get(i).getNumber();
            String provinceName = mCityList.get(i).getProvince();

            String cityName = mCityList.get(i).getCity();
            mArrayList.add("NO_"+No_+":"+number+"-"+provinceName+"-"+cityName);

        }

        cityListLv = (ListView)findViewById(R.id.selectcity_lv);
        adapter = new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_1,mArrayList);
        adapter.notifyDataSetChanged();
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

            //搜索按键的处理
            case R.id.selectcity_search_button:
                String citycode = searchEt.getText().toString();
                Log.d( "Search ",citycode);


                ArrayList<String> mSearchList = new ArrayList<String>();
                String No_;
                String number;
                String provinceName;
                String cityName;
                for(int i =0;i<mCityList.size();i++)
                {
                    No_= Integer.toString(i+1);
                    number = mCityList.get(i).getNumber();
                    provinceName = mCityList.get(i).getProvince();
                    cityName = mCityList.get(i).getCity();

                    if(cityName.equals(citycode)){
                        citycode = number;
                        mSearchList.add("NO_"+No_+":"+number+"-"+provinceName+"-"+cityName);
                        Log.d("changed adapter data","NO."+No_+":"+number+"-"+provinceName+"-"+cityName);
                    }
                        adapter  = new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_1,mArrayList);
                        cityListLv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                }

                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("citycode",citycode);
                startActivity(intent);
                break;
            case R.id.title_selectCity_back:
                Intent intent1 = new Intent(this,MainActivity.class);
                intent1.putExtra("citycode02",updateCode);
                startActivity(intent1);
                finish();
                break;

            default:
                break;
        }


    }



}
