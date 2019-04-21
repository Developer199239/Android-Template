package com.raywenderlich.android.ingredisearch

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

/**
 * Created by Murtuza Rahman on 4/21/19.
 */
class IngredisearchTestRunner : AndroidJUnitRunner() {
    @Throws(
            InstantiationException::class,
            IllegalAccessException::class,
            ClassNotFoundException::class
    )

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, className, context)
    }
}