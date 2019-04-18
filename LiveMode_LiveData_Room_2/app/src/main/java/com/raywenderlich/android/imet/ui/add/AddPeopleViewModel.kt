package com.raywenderlich.android.imet.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.raywenderlich.android.imet.IMetApp
import com.raywenderlich.android.imet.data.model.People

/**
 * Created by Murtuza Rahman on 4/18/19.
 */
class AddPeopleViewModel (application: Application) : AndroidViewModel(application) {
    private val peopleRepository = getApplication<IMetApp>().getPeopleRepository()

    fun addPeople(people: People) {
        peopleRepository.insertPeople(people)
    }
}