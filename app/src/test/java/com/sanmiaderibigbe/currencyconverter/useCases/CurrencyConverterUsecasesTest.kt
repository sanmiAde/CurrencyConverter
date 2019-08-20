package com.sanmiaderibigbe.currencyconverter.useCases

import org.junit.After
import org.junit.Assert
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class CurrencyConverterUsecasesTest {

    lateinit var currencyConverterUsecases : CurrencyConverterUsecases
    var currencyToConvertFromRate : Double = Double.MIN_VALUE
    var currencyToConvertToRate : Double = Double.MAX_VALUE

    @Before
    fun setUp() {
        currencyConverterUsecases = CurrencyConverterUsecases()
        currencyToConvertFromRate = 1.107396
        currencyToConvertToRate = 401.464266
    }

    @Test
    fun getCurrencyConversionRate_shouldComputeCurrencyConvertRate_currencyConversionRate(){
        val conversionRate = currencyConverterUsecases.computeConversionRate(currencyToConvertFromRate , currencyToConvertToRate)
        assertEquals(362.53, conversionRate, 1e-3)
    }

    @After
    fun tearDown() {
    }
}