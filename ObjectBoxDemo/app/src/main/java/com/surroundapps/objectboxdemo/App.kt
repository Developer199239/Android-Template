package com.surroundapps.objectboxdemo

import android.app.Application
import io.objectbox.BoxStore

/**
 * Created by Murtuza Rahman on 3/20/19.
 */
class App : Application() {

    companion object {
        const val TAG = "ObjectBoxExample"
    }

    private var boxStore: BoxStore? = null
    override fun onCreate() {
        super.onCreate()

//        boxStore = MyObjectBox.builder().androidContext(this).build()

        ObjectBox.init(this)

    }
}