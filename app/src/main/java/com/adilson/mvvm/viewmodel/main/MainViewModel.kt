package com.adilson.mvvm.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adilson.mvvm.models.Live
import com.adilson.mvvm.repositories.MainRepository
import retrofit2.Call
import retrofit2.Response


class MainViewModel (private val repository: MainRepository): ViewModel() {

    val livelist = MutableLiveData<List<Live>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllLives(){

        val request = repository.getAllLives()
        request.enqueue(object  : retrofit2.Callback<List<Live>> {
            override fun onResponse(call: Call<List<Live>>, response: Response<List<Live>>) {
                //Quando tiver resposta
                livelist.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Live>>, t: Throwable) {
                //Quando tiver falha na chamada
                errorMessage.postValue(t.message)
            }
        }

        )

    }

}