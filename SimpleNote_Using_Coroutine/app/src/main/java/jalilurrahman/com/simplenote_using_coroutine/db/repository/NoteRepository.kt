package jalilurrahman.com.simplenote_using_coroutine.db.repository

import androidx.paging.Config
import androidx.paging.toLiveData
import jalilurrahman.com.simplenote_using_coroutine.db.bean.Note
import jalilurrahman.com.simplenote_using_coroutine.db.dao.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class NoteRepository(private val noteDao: NoteDao) {

    fun list() = noteDao.getTaskList().toLiveData(
        Config(
            pageSize = 30,
            enablePlaceholders = true
        )
    )

    suspend fun add(name: String) {
        withContext(Dispatchers.IO) {
            val note = Note(0, name)
            noteDao.add(note)
        }
    }

    suspend fun update(note: Note) {
        withContext(Dispatchers.IO) {
            note.updateDate = Date()
            noteDao.update(note)
        }
    }

    suspend fun del(id: Long) {
        withContext(Dispatchers.IO) {
            noteDao.del(id)
        }
    }
}