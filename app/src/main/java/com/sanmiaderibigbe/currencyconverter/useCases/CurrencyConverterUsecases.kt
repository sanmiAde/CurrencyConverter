package com.sanmiaderibigbe.currencyconverter.useCases

class CurrencyConverterUsecases {
    fun computeConversionRate(currencyToConvertFrom: Double, currencyToConvertTo: Double): Double {
        return  currencyToConvertTo / currencyToConvertFrom
    }


}