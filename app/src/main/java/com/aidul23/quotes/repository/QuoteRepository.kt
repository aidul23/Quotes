package com.aidul23.quotes.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aidul23.quotes.api.QuoteService
import com.aidul23.quotes.models.Quotes

class QuoteRepository(private val quoteService: QuoteService) {

    private val quotesLiveData = MutableLiveData<Quotes>()

    val quotes : LiveData<Quotes>
    get() = quotesLiveData

    suspend fun getQuotes(page: Int) {
        val result = quoteService.getQuotes(page)
        if (result?.body() != null) {
            quotesLiveData.postValue(result.body())
        }
    }
}