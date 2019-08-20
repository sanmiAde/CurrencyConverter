package com.sanmiaderibigbe.currencyconverter.api.model

import com.sanmiaderibigbe.currencyconverter.api.model.FixerRating
import retrofit2.Response

class FixerRatingsReponse(private val ratingOfCurrencyToConverTo: Response<FixerRating>, private val ratingOfCurrencyToConverFrom : Response<FixerRating>) {

    fun getCurrencyToConvertFromRating(): Double {
        return getCurrencyRating(ratingOfCurrencyToConverFrom)
    }


    fun getCurrencyToConvertToRating(): Double {
        return getCurrencyRating(ratingOfCurrencyToConverTo)
    }

    private fun getCurrencyRating( response: Response<FixerRating>): Double {
        return when( response.isSuccessful){
            true -> {
                response.body()?.rates?.currentRatings!!
            }
            else -> {
                Double.MIN_VALUE
            }
        }

    }
}