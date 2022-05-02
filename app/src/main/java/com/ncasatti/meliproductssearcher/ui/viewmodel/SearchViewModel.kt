package com.ncasatti.meliproductssearcher.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncasatti.meliproductssearcher.core.mainLogTag
import com.ncasatti.meliproductssearcher.data.model.SearchModel
import com.ncasatti.meliproductssearcher.data.network.SearchService
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {

    private val logTag = "$mainLogTag SearchViewModel() | "

    private val searchService = SearchService()
    val searchMutableData = MutableLiveData<SearchModel>()
    val isLoading = MutableLiveData<Boolean>()
    val actionBarTittle = MutableLiveData<String>()
    val position = MutableLiveData<Int>()
    val isEmpty = MutableLiveData<Boolean>(true)

    fun searchProductByName(query: String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = searchService.getProductsByName(query)
            if (result != null){
                Log.i("$logTag searchProductByName()",
                    "Se obtuvieron ${result.results.size} resultados para la busqueda: $query")
                searchMutableData.postValue(result)
                if (result.results.isEmpty()){
                    Log.i("$logTag searchProductByName()",
                        "No se obtuvieron resultados para la busqueda: $query")
                    isEmpty.postValue(true)
                }else{
                    isEmpty.postValue(false)
                }
            }
            isLoading.postValue(false)
        }
    }
}