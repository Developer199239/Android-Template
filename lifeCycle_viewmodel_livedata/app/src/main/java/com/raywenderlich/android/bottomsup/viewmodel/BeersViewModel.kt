package com.raywenderlich.android.bottomsup.viewmodel

import androidx.lifecycle.ViewModel
import com.raywenderlich.android.bottomsup.App
import com.raywenderlich.android.bottomsup.model.BeerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeersViewModel: ViewModel() {
    private val interactor by lazy { App.component.breweryInteractor() }


    fun getBeers() {
        interactor.getBeers(1, beersCallback())
    }

    private fun beersCallback() = object : Callback<BeerResponse> {
        override fun onFailure(call: Call<BeerResponse>?, t: Throwable?) {
        }

        override fun onResponse(call: Call<BeerResponse>?, response: Response<BeerResponse>?) {
        }
    }
}