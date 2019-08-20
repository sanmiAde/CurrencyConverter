package com.sanmiaderibigbe.currencyconverter.api


import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

import com.sanmiaderibigbe.currencyconverter.api.model.FixerRating

interface FixerIOService  {


    @GET("latest")
    fun getCurrentConversionRates(@Query("access_key") apiKey : String, @Query("symbols") symbols : String) : Single<Response<FixerRating>>

    companion object{
        val apiKey = "c8bb284a7174ab46210e6514aab3dd80"

        const val BASE_CURRENCY = "USD"
    }
}