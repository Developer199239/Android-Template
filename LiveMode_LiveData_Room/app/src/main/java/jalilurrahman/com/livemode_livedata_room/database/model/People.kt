package jalilurrahman.com.livemode_livedata_room.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class People(
    var name:String = "",
    var metAt: String = "",
    var contact: String = "",
    var email: String = "",
    var facebook: String = "",
    var twitter: String = "",
    @PrimaryKey(autoGenerate = true) var id:Int = 0
)