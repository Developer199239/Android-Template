package com.surroundapps.stringtojson

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import org.apache.commons.io.IOUtils
import java.io.IOException

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        val rawString = resources.openRawResource(R.raw.data)
        var strData = ""

        try {
            strData = IOUtils.toString(rawString)
            IOUtils.closeQuietly(rawString)

            val example = Gson().fromJson(strData, Example1::class.java)
            Log.d("tag", "tag")

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
