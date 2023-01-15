package com.example.myapplication.network.sources

import com.example.myapplication.network.entities.BPReplyEntity
import com.example.myapplication.network.entities.OpetReplyEntity
import retrofit2.Call
import retrofit2.http.GET

interface OpetApiInterface {
    @GET("/api/fuelprices/prices?ProvinceCode=934&IncludeAllProducts=false")
    fun getPrices(): Call<List<OpetReplyEntity>>
}