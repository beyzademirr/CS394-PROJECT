package com.example.myapplication.purchaseList

import android.app.Application
import androidx.lifecycle.*
import com.example.myapplication.database.PurchaseDatabase
import com.example.myapplication.database.dao.PurchaseDao
import com.example.myapplication.database.entities.Purchase
import com.example.myapplication.repository.PurchaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PurchaseListViewModel (val purcaseDAO: PurchaseDao, application: Application, val database: PurchaseDatabase) : AndroidViewModel(application) {
    var app = application

    private val purchaseRepository = PurchaseRepository(database)
    val purchases = purchaseRepository.purchases




}

class PlantListViewModelFactory(
    private val dataSource: PurchaseDao, private val application: Application, val database: PurchaseDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PurchaseListViewModel::class.java)) {
            return PurchaseListViewModel(dataSource, application, database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}