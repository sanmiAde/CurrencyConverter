package com.sanmiaderibigbe.currencyconverter.repo

import com.sanmiaderibigbe.currencyconverter.api.FixerIOService
import com.sanmiaderibigbe.currencyconverter.api.RetrofitInstance
import com.sanmiaderibigbe.currencyconverter.api.model.FixerRating

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class CurrencyRateRepository {

   private val retrofit = RetrofitInstance.initRetrofiInstance()


    /***
     * Gets currrent currency conversation
     */
    fun getCurrencyConversionRate(currency : String): Single<Response<FixerRating>> {


        return retrofit.getCurrentConversionRates(FixerIOService.apiKey, currency).subscribeOn(Schedulers.io())

    }
}