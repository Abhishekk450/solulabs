package com.example.solulab.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.solulab.repository.CoinListRepository
import com.example.solulab.viewmodel.CoinViewModel


class CoinViewModelFactory (private val repository: CoinListRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CoinViewModel::class.java)) {
            CoinViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}