package jalilurrahman.com.simplenote_using_coroutine.db.test

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import jalilurrahman.com.simplenote_using_coroutine.db.AppDatabase
import jalilurrahman.com.simplenote_using_coroutine.db.bean.Note
import kotlinx.coroutines.coroutineScope

class DbTestDataInit(context:Context,
                     workedParams: WorkerParameters
                     ) : CoroutineWorker(context,workedParams) {
    override suspend fun doWork(): Result = coroutineScope {
        val database = AppDatabase.getInstance(applicationContext)

        database.noteDao().add(Note(0,"sample Note"))
        Result.success()
    }

}