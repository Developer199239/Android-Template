package jalilurrahman.com.simplenote_using_coroutine.db.dao

import androidx.paging.DataSource
import androidx.room.*
import jalilurrahman.com.simplenote_using_coroutine.db.bean.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note order by updateDate desc")
    fun getTaskList(): DataSource.Factory<Int, Note>

    @Insert
    fun add(note: Note)

    @Insert
    fun add(notes: List<Note>)

    @Update
    fun update(note: Note)

    @Delete
    fun del(note: Note)

    @Query("DELETE FROM Note WHERE id = (:id)")
    fun del(id: Long)
}