package com.example.myapplication.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.database.entities.Purchase

@Dao
interface PurchaseDao {
    @Query("SELECT * FROM Purchases ORDER BY id DESC")
    public fun getAllPurchases(): MutableList<Purchase>
    @Query("SELECT * FROM Purchases WHERE id =:id")
    public fun getPurchase(id: Int): Purchase
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public fun savePurchase(purchase: Purchase)
    @Query("DELETE FROM Purchases WHERE id=:id")
    public fun deletePurchase(id: Int)
    @Query("DELETE FROM Purchases")
    public fun wipeTable()
    @Query("SELECT * FROM Purchases ORDER BY id DESC")
    public fun getLivePurchases(): LiveData<List<Purchase>>

}