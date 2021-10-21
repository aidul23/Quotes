package com.aidul23.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aidul23.quotes.api.QuoteService
import com.aidul23.quotes.api.RetrofitHelper
import com.aidul23.quotes.repository.QuoteRepository
import com.aidul23.quotes.viewmodels.MainViewModel
import com.aidul23.quotes.viewmodels.MainViewModelFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val repository = QuoteRepository(quoteService)

        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {
            for (result in it.results) {
                Log.d(TAG, "onCreate: {${result.content}}\n")
            }
        })

    }
}