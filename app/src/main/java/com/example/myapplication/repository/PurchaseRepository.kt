package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.database.PurchaseDatabase
import com.example.myapplication.database.entities.Purchase

class PurchaseRepository (private val database: PurchaseDatabase) {
    val purchases: LiveData<List<Purchase>> = database.dao().getLivePurchases()

}