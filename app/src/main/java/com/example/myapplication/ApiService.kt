package com.example.myapplication


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("api/v3/coins/markets?vs_currency=usd&ids=ethereum%2Cbitcoin%2Csolana&order=market_cap_desc&per_page=100&page=1&sparkline=false")
    fun fetchAllStocks(): Call<MutableList<Stock>>

    @GET("api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false")
    fun getSpecificStock(@Query("ids") name: String): Call<MutableList<Stock>>



    @GET("api/v3/coins/markets?vs_currency=usd&ids=bitcoin&order=market_cap_desc&per_page=100&page=1&sparkline=false")
    fun getSpecificStocke(): Call<MutableList<Stock>>


}