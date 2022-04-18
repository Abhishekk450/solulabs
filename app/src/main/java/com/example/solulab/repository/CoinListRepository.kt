package com.example.solulab.repository

import com.example.solulab.data.RetrofitService

class CoinListRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllCoinDetails() = retrofitService.getAllCoinList()
}