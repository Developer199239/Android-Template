package com.surroundapps.objectboxdemo

import android.content.Context
import android.util.Log
import io.objectbox.BoxStore

/**
 * Created by Murtuza Rahman on 3/20/19.
 */
object ObjectBox {

    lateinit var boxStore: BoxStore
        private set

    fun init(context: Context) {
        boxStore = MyObjectBox.builder().androidContext(context.applicationContext).build()

        if (BuildConfig.DEBUG) {
            Log.d("TAG", "Using ObjectBox ${BoxStore.getVersion()} (${BoxStore.getVersionNative()})")
        }
    }
}