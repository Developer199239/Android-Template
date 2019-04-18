package jalilurrahman.com.livemode_livedata_room.database.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jalilurrahman.com.livemode_livedata_room.database.model.People


@Dao
interface PeopleDao {
    // select all
    @Query("SELECT * FROM People ORDER BY id DESC")
    fun getAll():List<People>

    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(people: People)

    // delete
    @Query("DELETE FROM People")
    fun deleteAll()

    //select by id
    @Query("SELECT * FROM People WHERE id = :id")
    fun find(id: Int):People

}