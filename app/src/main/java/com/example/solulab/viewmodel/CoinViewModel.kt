package com.example.solulab.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.solulab.model.CoinListResponse
import com.example.solulab.model.Data
import com.example.solulab.repository.CoinListRepository
import kotlinx.coroutines.*

class CoinViewModel constructor(private val coinListRepository: CoinListRepository): ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val coinList = MutableLiveData<CoinListResponse>()
    var job:Job? = null

    private val exceptionHandler = CoroutineExceptionHandler{_, throwable ->
           onError("Exception handled  ${throwable.localizedMessage}")
    }

    val loading = MutableLiveData<Boolean>()

    fun getAllCoinsData() {

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response =  coinListRepository.getAllCoinDetails()
            withContext(Dispatchers.Main){
                if(response.isSuccessful) {
                    coinList.postValue(response.body())
                }

            }
        }
    }

    private fun onError(message:String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}