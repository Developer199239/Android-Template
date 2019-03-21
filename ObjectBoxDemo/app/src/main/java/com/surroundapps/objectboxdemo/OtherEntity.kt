package com.surroundapps.objectboxdemo

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by Murtuza Rahman on 3/20/19.
 */
@Entity
class OtherEntity {
    @Id
    var id: Long = 0

    var name: String? = null
}