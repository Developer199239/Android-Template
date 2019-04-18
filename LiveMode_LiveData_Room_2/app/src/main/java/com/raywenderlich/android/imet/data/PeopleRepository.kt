package com.raywenderlich.android.imet.data

import android.app.Application
import com.raywenderlich.android.imet.data.model.People
import com.raywenderlich.android.imet.data.net.PeopleInfoProvider

class PeopleRepository(application: Application) {

  /**
   * Returns the list of all people in reverse order (latest on top)
   */
  fun getAllPeople(): List<People> {
    val allPeople = PeopleInfoProvider.peopleList
    return allPeople.reversed()
  }

  /**
   * Adds a new people info on peopleList
   */
  fun insertPeople(people: People) {
    PeopleInfoProvider.peopleList.add(people)
  }

  /**
   * Finds people with specific id
   */
  fun findPeople(id: Int): People? {
    for (people in PeopleInfoProvider.peopleList) {
      if (people.id == id) {
        return people
      }
    }
    return null
  }

  /**
   * Finds people with similar name
   */
  fun searchPeople(name: String): List<People> {
    var peoples = mutableListOf<People>()
    for (people in PeopleInfoProvider.peopleList) {
      if (people.name.toLowerCase().contains(name.toLowerCase())) {
        peoples.add(people)
      }
    }
    return peoples
  }

}
