package com.surroundapps.objectboxdemo

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.util.*

/**
 * Created by Murtuza Rahman on 3/20/19.
 */
@Entity
class Note {
    @Id
    var label2: Long = 0L
    var text: String? = null
    var comment: String? = null
    var date: Date? = null
}