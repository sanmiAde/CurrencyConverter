package com.sanmiaderibigbe.currencyconverter.ui.home


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanmiaderibigbe.currencyconverter.api.Resource
import com.sanmiaderibigbe.currencyconverter.api.model.FixerRating
import com.sanmiaderibigbe.currencyconverter.api.model.FixerRatingsReponse
import com.sanmiaderibigbe.currencyconverter.repo.CurrencyRateRepository
import com.sanmiaderibigbe.currencyconverter.useCases.CurrencyConverterUsecases
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
import retrofit2.Response

class HomeViewModel(
    private val currencyRateRepository: CurrencyRateRepository,
    private val currencyConverterUsecases: CurrencyConverterUsecases
) : ViewModel() {

    private val currencyExchangeRateLiveData = MutableLiveData<Resource<Double>>()

    private val disposable = CompositeDisposable()

    private val HomeViewModelTag = HomeViewModel::class.java.simpleName

    fun getRatings(currencyToConvertTo: String, currencyToConvertFrom: String) {

        disposable.add(currencyRateRepository.getCurrencyConversionRate(currencyToConvertTo).zipWith(
            currencyRateRepository.getCurrencyConversionRate(currencyToConvertFrom),
            BiFunction { currencyToConvertToRating: Response<FixerRating>, currencyToConvertFromRating: Response<FixerRating> ->
                FixerRatingsReponse(
                    currencyToConvertToRating,
                    currencyToConvertFromRating
                )

            }).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
            onSuccess = {
                computeConversionRate(it)

            },
            onError = {
                updateErrorReponse(it)
            }
        ))


    }

    private fun computeConversionRate(reponse: FixerRatingsReponse) {
        val conversionRate = currencyConverterUsecases.computeConversionRate(
            reponse.getCurrencyToConvertFromRating(),
            reponse.getCurrencyToConvertToRating()
        )
        currencyExchangeRateLiveData.value = Resource.success(
            conversionRate
        )
    }


    private fun updateErrorReponse(throwable: Throwable) {
        currencyExchangeRateLiveData.value = Resource.error(
            throwable.localizedMessage, Double.MIN_VALUE
        )
    }

    fun getRatingsLiveData(): MutableLiveData<Resource<Double>> {
        return currencyExchangeRateLiveData
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}