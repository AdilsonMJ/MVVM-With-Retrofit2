package com.adilson.mvvm.repositories

import com.adilson.mvvm.rest.RetrofitService

//Nome Main because reference the MainActivity
class MainRepository (private val retrofitService: RetrofitService){

    fun getAllLives() = retrofitService.getAllLives()

}