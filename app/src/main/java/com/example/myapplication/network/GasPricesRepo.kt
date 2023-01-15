package com.example.myapplication.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.network.entities.BPReplyEntity
import com.example.myapplication.network.entities.GasPriceEntity
import com.example.myapplication.network.entities.OpetReplyEntity
import com.example.myapplication.network.sources.BPApiClient
import com.example.myapplication.network.sources.BPApiInterface
import com.example.myapplication.network.sources.OpetApiClient
import com.example.myapplication.network.sources.OpetApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GasPricesRepo {
    private val bptag = "BP "
    private val opettag = "Opet "

    public fun getPrices(prices: MutableLiveData<MutableMap<String, Float>>) {
        val bptest = BPApiClient().getClient()?.create(BPApiInterface::class.java)
        val bpget = bptest?.getPrices()
        bpget?.enqueue(object : Callback<List<BPReplyEntity>> {
            override fun onResponse(
                call: Call<List<BPReplyEntity>>?, response: Response<List<BPReplyEntity>>?
            ) {
                if (response != null && response.isSuccessful && response.body().isNotEmpty()) {
                    if (prices.value == null) prices.value = mutableMapOf<String, Float>()

                    prices.value?.put(bptag + "Regular", response.body().get(0).regular)
                    prices.value?.put(bptag + "Diesel", response.body().get(0).regular)
                    prices.value?.put(bptag + "Premium", response.body().get(0).regular)
                    prices.value?.put(bptag + "Premium Diesel", response.body().get(0).regular)

//                    prices.value?.add(GasPriceEntity(bptag+"Regular",response.body().get(0).regular))
//                    prices.value?.add(GasPriceEntity(bptag+"Diesel",response.body().get(0).diesel))
//                    prices.value?.add(GasPriceEntity(bptag+"Premium",response.body().get(0).premium))
//                    prices.value?.add(GasPriceEntity(bptag+"Premium Diesel",response.body().get(0).premiumDiesel))
                }
            }

            override fun onFailure(call: Call<List<BPReplyEntity>>?, t: Throwable?) {
                Log.e("TAG", "onFailure: ", t)
            }
        })

        val opettest = OpetApiClient().getClient()?.create(OpetApiInterface::class.java)
        val opget = opettest?.getPrices()
        opget?.enqueue(object : Callback<List<OpetReplyEntity>> {
            override fun onResponse(
                call: Call<List<OpetReplyEntity>>?, response: Response<List<OpetReplyEntity>>?
            ) {
                if (response != null && response.isSuccessful && response.body().isNotEmpty()) {
                    if (prices.value == null) prices.value = mutableMapOf<String, Float>()

                    for (price in response.body().get(0).prices) {
                        when (price.name) {
                            "KURS" -> prices.value?.put(opettag + "Regular", price.price)
                            "MT_ULT" -> prices.value?.put(opettag + "Premium Diesel", price.price)
                            "MT_ECO" -> prices.value?.put(opettag + "Diesel", price.price)
//                            "KURS"->prices.value?.add(GasPriceEntity(opettag+"Regular",price.price))
//                            "MT_ULT"->prices.value?.add(GasPriceEntity(opettag+"Premium Diesel",price.price))
//                            "MT_ECO"->prices.value?.add(GasPriceEntity(opettag+"Diesel",price.price))
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<OpetReplyEntity>>?, t: Throwable?) {
                Log.e("TAG", "onFailure: ", t)
            }
        })
    }
}