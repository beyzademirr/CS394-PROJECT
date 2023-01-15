package com.example.myapplication.network.sources

import com.example.myapplication.network.entities.BPReplyEntity
import retrofit2.Call
import retrofit2.http.GET

interface BPApiInterface {
    @GET("/bp-tr-pump-prices/api/PumpPrices?strCity=%C4%B0STANBUL%20(ANADOLU)")
    fun getPrices():Call<List<BPReplyEntity>>
}