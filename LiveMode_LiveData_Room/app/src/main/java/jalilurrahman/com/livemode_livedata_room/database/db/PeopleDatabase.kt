package jalilurrahman.com.livemode_livedata_room.database.db

import android.app.Application
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import jalilurrahman.com.livemode_livedata_room.database.model.People


@Database(entities = [People::class], version = 1)
abstract class PeopleDatabase : RoomDatabase() {

    abstract fun peopleDao(): PeopleDao

    companion object{
        private val lock = Any()
        private const val DB_NAME = "People.db"
        private var INSTANCE: PeopleDatabase? = null

        fun getInstance(application: Application) : PeopleDatabase {
            synchronized(lock) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(application,
                        PeopleDatabase::class.java, DB_NAME
                        )
                        .allowMainThreadQueries()
                        .addCallback(object : RoomDatabase.Callback(){
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                PeopleDatabase.INSTANCE?.let {
//                                    PeopleDatabase.prePopulate(it,Peop)
                                }
                            }
                        })
                        .build()
                }
                return INSTANCE!!
            }
        }

        fun prePopulate(database:PeopleDatabase, peopleList:List<People>) {
            for(people in peopleList) {
                AsyncTask.execute {
                    database.peopleDao().insert(people)
                }
            }
        }

    }


}