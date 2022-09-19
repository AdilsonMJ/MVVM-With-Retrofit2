package com.adilson.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adilson.mvvm.repositories.MainRepository
import com.adilson.mvvm.viewmodel.main.MainViewModel
import java.lang.IllegalArgumentException

class MainViewModelFactory (private val repository: MainRepository) : ViewModelProvider.Factory{
    //Codigo Padrao

    override fun <t : ViewModel> create(modelsClass: Class<t>): t {
        return if (modelsClass.isAssignableFrom(MainViewModel::class.java)){
            MainViewModel(this.repository) as t
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}