package com.surroundapps.thumimageloading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageAdapter adapter = null;
    private RecyclerView rvcallLogs;
    protected ImageLoader imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                Picasso.with(MainActivity.this).load(url).into(imageView);
            }
        };

        adapter = new ImageAdapter(
                MainActivity.this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        rvcallLogs = findViewById(R.id.recy);
        rvcallLogs.setLayoutManager(mLayoutManager);
        rvcallLogs.setItemAnimator(new DefaultItemAnimator());
        rvcallLogs.setAdapter(adapter);

        ArrayList<String> data = new ArrayList<>();
        data.add("0");
        data.add("0");
        data.add("0");
        data.add("0");

        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
