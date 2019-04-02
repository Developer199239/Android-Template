package com.surroundapps.stringtojson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream is = getResources().openRawResource(R.raw.data);
        String strData = "";
        try {
            strData = IOUtils.toString(is);
            IOUtils.closeQuietly(is);

            Example example = new Gson().fromJson(strData,Example.class);
            Log.d("tag","tag");

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
