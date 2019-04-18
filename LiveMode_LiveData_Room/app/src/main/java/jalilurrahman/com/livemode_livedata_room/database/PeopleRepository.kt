package jalilurrahman.com.livemode_livedata_room.database

import android.app.Application
import jalilurrahman.com.livemode_livedata_room.database.db.PeopleDao
import jalilurrahman.com.livemode_livedata_room.database.db.PeopleDatabase
import jalilurrahman.com.livemode_livedata_room.database.model.People

class PeopleRepository(application: Application) {
    private var peopleDao: PeopleDao

    init {
        val peopleDatabase = PeopleDatabase.getInstance(application)
        peopleDao = peopleDatabase.peopleDao()
    }

    fun getAllPeople():List<People> {
        return peopleDao.getAll()
    }

    fun insertPeople(people:People) {
        peopleDao.insert(people)
    }

    fun findPeople(id:Int):People {
        return peopleDao.find(id)
    }


}