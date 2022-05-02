package com.ncasatti.meliproductssearcher.data.network

import com.ncasatti.meliproductssearcher.data.model.SearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApiClient {
    @GET("sites/MLA/search")
    suspend fun getProductsByName(@Query("q") query: String): Response<SearchModel>
}