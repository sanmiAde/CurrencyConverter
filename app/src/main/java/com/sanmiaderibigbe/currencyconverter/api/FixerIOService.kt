package com.sanmiaderibigbe.currencyconverter.api

import com.sanmiaderibigbe.currencyconverter.BuildConfig
import com.sanmiaderibigbe.currencyconverter.model.Ratings
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


internal interface FixerIOService  {


    @GET("latest/")
    fun getCurrentConversionRates(@Query("access_key") apiKey : String, @Query("base") baseCurrency : String, @Query("symbols") currencyToConvertTo : String) : Single<Response<Ratings>>

    companion object{
        val apiKey = "f0abdaf471e31a94ba06657beed42ed6"

        const val BASE_CURRENCY = "USD"
    }
}