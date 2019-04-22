package jalilurrahman.com.simplenote_using_coroutine

import android.app.Application
import android.content.Context
import jalilurrahman.com.simplenote_using_coroutine.db.AppDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        lateinit var context: Context
    }

    /**
     * Provides AppDatabase
     */
    fun getAppDatabase() = AppDatabase.getInstance(this)
}