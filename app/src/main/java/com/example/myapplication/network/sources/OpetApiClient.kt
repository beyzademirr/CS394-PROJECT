package com.example.myapplication.network.sources

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OpetApiClient {

    private var retrofit: Retrofit? = null

    public fun getClient(): Retrofit? {
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.opet.com.tr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit as Retrofit
    }

}