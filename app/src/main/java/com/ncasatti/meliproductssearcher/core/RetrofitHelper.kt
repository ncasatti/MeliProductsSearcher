package com.ncasatti.meliproductssearcher.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val meliBackendUrl = "https://api.mercadolibre.com/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(meliBackendUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

}