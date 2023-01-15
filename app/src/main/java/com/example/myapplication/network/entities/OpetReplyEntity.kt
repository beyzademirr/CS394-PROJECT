package com.example.myapplication.network.entities

import com.google.gson.annotations.SerializedName

data class OpetReplyEntity(
    @SerializedName("prices")
    val prices: MutableList<OpetPriceEntity>) {
    data class OpetPriceEntity(
        @SerializedName("productShortName")
        val name: String = "",
        @SerializedName("amount")
        val price: Float = 0f
    ){
    }
}
//https://api.opet.com.tr/api/fuelprices/prices?ProvinceCode=934&IncludeAllProducts=false
//{
//    "provinceCode": 934,
//    "provinceName": "İSTANBUL AVRUPA",
//    "districtCode": "934021",
//    "districtName": "ARNAVUTKÖY",
//    "prices": [
//    {
//        "id": "fbb4cca2-f943-43d9-9739-ac720100bf26",
//        "productName": "Kurşunsuz Benzin 95",
//        "productShortName": "KURS",
//        "amount": 18.9,
//        "productCode": "A100"
//    },
//    {
//        "id": "8e9718ac-4ada-4154-9be0-ac720100bf26",
//        "productName": "Motorin UltraForce",
//        "productShortName": "MT_ULT",
//        "amount": 22.12,
//        "productCode": "A121"
//    },
//    {
//        "id": "e5af93d5-ed46-42f1-a8dc-ac720100bf26",
//        "productName": "Motorin EcoForce",
//        "productShortName": "MT_ECO",
//        "amount": 22.07,
//        "productCode": "A128"
//    }
//    ]
//},