package com.ncasatti.meliproductssearcher.data.network

import android.util.Log
import com.ncasatti.meliproductssearcher.core.RetrofitHelper
import com.ncasatti.meliproductssearcher.core.mainLogTag
import com.ncasatti.meliproductssearcher.data.model.SearchModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchService {
    private val retrofit = RetrofitHelper.getRetrofit()
    private val logTag = "$mainLogTag SearchService | "

    suspend fun getProductsByName(query: String): SearchModel?{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(SearchApiClient::class.java).getProductsByName(query)

            Log.i("$logTag getProductsByName()", "Buscando productos con el nombre: $query")
            if (response.isSuccessful){
                Log.i("$logTag getProductsByName()", "Busqueda exitosa")
            }else{
                Log.e("$logTag getProductsByName()", "Error buscando productos")
                Log.e("$logTag getProductsByName()", response.errorBody().toString())
            }
            response.body()
        }
    }
}