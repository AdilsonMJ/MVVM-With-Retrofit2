package com.adilson.mvvm

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.adilson.mvvm.Adapter.LiveAdapter
import com.adilson.mvvm.databinding.ActivityMainBinding
import com.adilson.mvvm.repositories.MainRepository
import com.adilson.mvvm.rest.RetrofitService
import com.adilson.mvvm.viewmodel.MainViewModelFactory
import com.adilson.mvvm.viewmodel.main.MainViewModel

class MainActivity : AppCompatActivity() {

    private  val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var viewModel : MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val adapter = LiveAdapter{
        openLink(it.link)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,
            MainViewModelFactory(
                MainRepository(retrofitService)
            )).get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapter

    }

    override fun onStart() {
        super.onStart()

        viewModel.livelist.observe(this, Observer {
            Log.i("AAA", "onStart")
            adapter.setLivesList(it)
        })

        viewModel.errorMessage.observe(
            this,
        ) { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllLives()
    }

    fun openLink(link: String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }

}