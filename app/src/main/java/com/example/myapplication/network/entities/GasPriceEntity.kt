package com.example.myapplication.network.entities

data class GasPriceEntity(
    val name: String = "",
    val price: Float = 0f
) {
    val sPrice: String = price.toString()
}