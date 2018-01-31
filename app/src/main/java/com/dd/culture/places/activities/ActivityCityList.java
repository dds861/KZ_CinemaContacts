package com.dd.culture.places.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dd.culture.places.database.DatabaseAccessCityList;
import com.dd.culture.places.models.ModelCityList;
import com.dd.culture.places.R;
import com.dd.culture.places.adapters.AdapterCityList;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class ActivityCityList extends AppCompatActivity {

    ListView listView;
    private AdView mAdView;
    AdapterCityList myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        //реклама
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //получаем доступ к базе
        DatabaseAccessCityList databaseAccess = DatabaseAccessCityList.getInstance(this);

        //открываем соединение
        databaseAccess.open();

        //инициализируем listview
        listView = findViewById(R.id.listview1);


        //из базы данных получаем список данных
        List<ModelCityList> products = databaseAccess.getList(0);
        //саздаем и инициализируем адаптер
        myAdapter = new AdapterCityList(getApplicationContext(), products);

        //присваем адаптер к listview
        listView.setAdapter(myAdapter);


        //создаем Кликер на item-ы в listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //создаем и инициализируем Intent
                Intent intent = new Intent(getApplicationContext(), ActivityCityListDetails.class);

                //добавляем дополнительный параметр, позицию которуб Кликнули
                intent.putExtra("position", position);

                //запускаем Intent, и открываем новый activity
                startActivity(intent);

            }
        });


    }


}
