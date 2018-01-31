package com.dd.culture.places.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.dd.culture.places.R;
import com.dd.culture.places.adapters.AdapterCityListDetails;
import com.dd.culture.places.database.DatabaseAccessCityListDetails;
import com.dd.culture.places.models.ModelCityListDetails;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class ActivityCityListDetails extends AppCompatActivity {

    ListView listView;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list_details);


        //реклама от google
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //получаем доступ к базе
        DatabaseAccessCityListDetails databaseAccess = DatabaseAccessCityListDetails.getInstance(this);

        //открываем соединение
        databaseAccess.open();

        //инициализируем listview d
        listView = findViewById(R.id.listview2);

        //получаем из предыдущего activity какую позицию выбрали
        int position = getIntent().getIntExtra("position", 0);

        //из базы данных получаем список данных по полученной позиции
        List<ModelCityListDetails> products = databaseAccess.getList(position + 1);

        //саздаем и инициализируем адаптер
        AdapterCityListDetails myAdapter = new AdapterCityListDetails(getApplicationContext(), products);

        //присваем адаптер к listview
        listView.setAdapter(myAdapter);



//
//

    }


}
