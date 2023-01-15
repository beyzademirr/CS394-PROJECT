package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.database.dao.PurchaseDao
import com.example.myapplication.database.entities.Purchase

@Database(entities = [Purchase::class], version = 1, exportSchema = false)
abstract class PurchaseDatabase : RoomDatabase(){
    companion object{
        var purchaseDatabase: PurchaseDatabase? = null

        @Synchronized
        fun getInstance(context: Context): PurchaseDatabase{
            if(purchaseDatabase == null)
                purchaseDatabase = Room.databaseBuilder(context, PurchaseDatabase::class.java, "purschases.db").build()
            return purchaseDatabase!!
        }
    }
    abstract fun dao(): PurchaseDao
}