package com.example.myapplication.network.entities

import com.google.gson.annotations.SerializedName

data class BPReplyEntity(
    @SerializedName("DtPriceListDate")
    val date: String = "",
    @SerializedName("Benzin")
    val regular: Float = 0f,
    @SerializedName("BenzinUltimate")
    val premium: Float = 0f,
    @SerializedName("MotorinUltimate")
    val premiumDiesel: Float = 0f,
    @SerializedName("Motorin")
    val diesel: Float = 0f
) {
}

//sample data
//    DtPriceListDate	"2023-01-13T00:00:00"
//    District	"ATAŞEHİR"
//    City	"İSTANBUL (ANADOLU)"
//    Benzin	"19.70"
//    GazYagi	"21.77"
//    BenzinUltimate	"19.77"
//    MotorinUltimate	"22.12"
//    Motorin	"22.07"
//    FuelOil	"14.02"
//    KaloriferYakiti	"16.78"
//    FuelOilYuksekKukurt	"9.06"
//    LpgPrice	"10.30"