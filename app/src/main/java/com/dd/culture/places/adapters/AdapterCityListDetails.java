package com.dd.culture.places.adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dd.culture.places.R;
import com.dd.culture.places.models.ModelCityListDetails;

import java.util.List;

/**
 * Created by dds86 on 23-Nov-17.
 */

public class AdapterCityListDetails extends ArrayAdapter<ModelCityListDetails> {
    private List<ModelCityListDetails> objects;
    private Context context;

    public AdapterCityListDetails(@NonNull Context context, List<ModelCityListDetails> objects) {
        super(context, 0, objects);
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.items_city_list_details, parent, false);

        final TextView city_name = view.findViewById(R.id.city_name);
        final TextView cinema_name = view.findViewById(R.id.cinema_name);
        final TextView cinema_address = view.findViewById(R.id.cinema_address);
        final TextView cinema_phoneNumber = view.findViewById(R.id.cinema_phoneNumber);



        ModelCityListDetails product = getItem(position);

        String s1 = product.getCity_name();
        String s2 = product.getCinema_name();
        String s3 = product.getCinema_address();
        String s4 = product.getCinema_phoneNumber();

        city_name.setText(s1.replaceAll("\\\\n", "\n"));
        cinema_name.setText(s2.replaceAll("\\\\n", "\n"));
        cinema_address.setText(s3.replaceAll("\\\\n", "\n"));
        cinema_phoneNumber.setText(s4.replaceAll("\\\\n", "\n"));


        ImageView imageViewCopy = view.findViewById(R.id.ivCopyAll);
        ImageView imageViewShare = view.findViewById(R.id.ivShare);


        imageViewCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, R.string.TextCopied, Toast.LENGTH_SHORT).show();
                //эффект нажатия на кнопку
                makeAnimationOnView(R.id.ivCopyAll, Techniques.FadeOut, 150, 0, view);
                makeAnimationOnView(R.id.ivCopyAll, Techniques.FadeIn, 350, 0, view);

                // Gets a handle to the clipboard service.
                ClipboardManager clipboard2 = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);

                // Creates a new text clip to put on the clipboard
                ClipData clip = ClipData.newPlainText("simple text", city_name.getText().toString());

                // Set the clipboard's primary clip.
                clipboard2.setPrimaryClip(clip);
            }
        });

        imageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //эффект нажатия на кнопку
                makeAnimationOnView(R.id.ivShare, Techniques.FadeOut, 150, 0, view);
                makeAnimationOnView(R.id.ivShare, Techniques.FadeIn, 350, 0, view);

                String shareBody = city_name.getText().toString();
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.share_using)));
            }
        });


        return view;
    }

    void makeAnimationOnView(int resourceId, Techniques techniques, int duration, int repeat, View view) {
        YoYo.with(techniques)
                .duration(duration)
                .repeat(repeat)
                .playOn(view.findViewById(resourceId));

    }

}
