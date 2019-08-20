package com.sanmiaderibigbe.currencyconverter.di

import androidx.lifecycle.ViewModel
import com.sanmiaderibigbe.currencyconverter.api.FixerIOService
import com.sanmiaderibigbe.currencyconverter.api.RetrofitInstance
import com.sanmiaderibigbe.currencyconverter.repo.CurrencyRateRepository

import com.sanmiaderibigbe.currencyconverter.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule = module {

        single { CurrencyRateRepository() }
        viewModel { HomeViewModel(get()) }

}