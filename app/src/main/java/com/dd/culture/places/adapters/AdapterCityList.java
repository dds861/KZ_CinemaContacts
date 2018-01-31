package com.dd.culture.places.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dd.culture.places.models.ModelCityList;
import com.dd.culture.places.R;

import java.util.List;

/**
 * Created by dds86 on 23-Nov-17.
 */

public class AdapterCityList extends ArrayAdapter<ModelCityList> {
    private List<ModelCityList> objects;

    public AdapterCityList(@NonNull Context context, @NonNull List<ModelCityList> objects) {
        super(context, 0, objects);
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.items_city_list, parent, false);

        TextView textView1 = view.findViewById(R.id.text1);

        ModelCityList product = getItem(position);

        String s1 = product.getCity_name();


        textView1.setText(s1.replaceAll("\\\\n", "\n"));

        return view;
    }



}
