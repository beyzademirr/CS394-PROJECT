package com.example.myapplication.priceList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PriceListViewModel(application: Application) : AndroidViewModel(application) {
//    val prices = priceRepository.prices
//
//    init {
//        viewModelScope.launch {
//        priceRepository.refreshPrices()}
//    }
}

class PriceListViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PriceListViewModel::class.java)) {
            return PriceListViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}