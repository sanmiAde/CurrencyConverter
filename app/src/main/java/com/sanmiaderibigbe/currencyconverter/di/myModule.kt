package com.sanmiaderibigbe.currencyconverter.di

import com.sanmiaderibigbe.currencyconverter.repo.CurrencyRateRepository
import com.sanmiaderibigbe.currencyconverter.ui.home.HomeViewModel
import com.sanmiaderibigbe.currencyconverter.useCases.CurrencyConverterUsecases
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule = module {

        single { CurrencyRateRepository() }
        single { CurrencyConverterUsecases() }
        viewModel { HomeViewModel(get(), get()) }

}