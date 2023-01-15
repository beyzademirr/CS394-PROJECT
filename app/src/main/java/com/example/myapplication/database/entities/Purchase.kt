package com.example.myapplication.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat

@Entity(tableName = "Purchases")
data class Purchase (
    @ColumnInfo(name = "description")
    var description:String,
    @ColumnInfo(name = "unitPrice")
    var unitPrice:Float,
    @ColumnInfo(name = "amount")
    var amount:Float,
    @ColumnInfo(name = "date")
    var date: Long,
    @ColumnInfo(name = "totalPrice")
    var totalPrice:Float
        ){
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null

    @Transient
    var dateString: String = SimpleDateFormat("HH:mm dd/MM/yy").format(date)

    @Transient
    var price : String = totalPrice.toString()

}