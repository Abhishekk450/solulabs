package com.example.solulab.util

import com.example.solulab.model.CoinDetails

object ValidationUtil {

    fun validateCoin(coinDetails: CoinDetails) : Boolean {
        if (coinDetails.name.isNotEmpty()) {
            return true
        }
        return false
    }
}