package com.surroundapps.imagefitwithwidth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.widget.LinearLayout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    int maxWidth = 500;
    int maxHeight = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        maxWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        maxHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        imageView = findViewById(R.id.imageView);

        String url = "http://103.78.248.70:5443/vdo.sensor.buzz/1cdfbaabc20ea425f56a41f26d3064a32045c513/s1N7zWdGm4RP9m2C4WecU48b1dYTXITRT3Nr13tr/16476879631209342977.jpeg";

//        Glide
//                .with(this)
//                .load(url)
//                .apply(new RequestOptions().override(200, 100))
//                .into(imageView);

//        RequestOptions myOptions = new RequestOptions()
//                .override(500, 100);
//
//        Glide.with(this)
//                .asBitmap()
//                .apply(myOptions)
//                .load(url)
//                .into(imageView);


//        RequestOptions myOptions = new RequestOptions()
//                .centerCrop();
//
//        Glide.with(this)
//                .asBitmap()
//                .apply(myOptions)
//                .load(url)
//                .into(imageView);



    int hi = (int) (maxHeight * 0.25);

        imageView.getLayoutParams().width = maxWidth;
        imageView.getLayoutParams().height = hi;
        imageView.invalidate();


        RequestOptions myOptions = new RequestOptions()
                .fitCenter() // or centerCrop
                .override(maxWidth, hi);

        Glide.with(this)
                .asBitmap()
                .apply(myOptions)
                .load(url)
                .into(imageView);

    }



}
