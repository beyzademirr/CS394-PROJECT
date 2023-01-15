package com.example.myapplication.network.sources

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class BPApiClient {

    private var retrofit: Retrofit? = null

    public fun getClient(): Retrofit? {
        retrofit = Retrofit.Builder()
            .baseUrl("https://www.bp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit as Retrofit
    }

}