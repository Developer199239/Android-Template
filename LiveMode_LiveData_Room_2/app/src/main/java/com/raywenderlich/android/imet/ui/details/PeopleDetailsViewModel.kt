package com.raywenderlich.android.imet.ui.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.raywenderlich.android.imet.IMetApp
import com.raywenderlich.android.imet.data.model.People

/**
 * Created by Murtuza Rahman on 4/18/19.
 */
class PeopleDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val peopleRepository = getApplication<IMetApp>().getPeopleRepository()
    private val peopleId = MutableLiveData<Int>()

    fun getPeopleDetails(id: Int): LiveData<People> {
        peopleId.value = id
        val peopleDetails = Transformations.switchMap<Int, People>(peopleId) {
            peopleRepository.findPeople(id)
        }
        return peopleDetails
    }
}