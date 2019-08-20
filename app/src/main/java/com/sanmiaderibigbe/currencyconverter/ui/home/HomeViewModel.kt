package com.sanmiaderibigbe.currencyconverter.ui.home


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanmiaderibigbe.currencyconverter.api.model.FixerRatingsReponse
import com.sanmiaderibigbe.currencyconverter.api.Resource
import com.sanmiaderibigbe.currencyconverter.api.model.FixerRating
import com.sanmiaderibigbe.currencyconverter.repo.CurrencyRateRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
import retrofit2.Response

class HomeViewModel(private val currencyRateRepository: CurrencyRateRepository) : ViewModel() {

    private val currencyExchangeRateResourceLiveData = MutableLiveData<Resource<FixerRatingsReponse>>()

    private val disposable = CompositeDisposable()

    private val HomeViewModelTag = HomeViewModel::class.java.simpleName

    fun getRatings(currencyToConvertTo: String, currencyToConvertFrom: String) {

        disposable.add(  currencyRateRepository.getCurrencyConversionRate(currencyToConvertTo).zipWith(
            currencyRateRepository.getCurrencyConversionRate(currencyToConvertFrom),
            BiFunction { currencyToConvertToRating: Response<FixerRating>, currencyToConvertFromRating: Response<FixerRating> ->
                FixerRatingsReponse(
                    currencyToConvertToRating,
                    currencyToConvertFromRating
                )

            }).subscribeBy(
            onSuccess = {

                updateReponse(response = it)

            },
            onError = {

            }
        ))


//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeBy(
//            onError = { throwable -> updateErrorReponse(throwable)
//            Log.d(HomeViewModelTag, throwable.message)},
//            onSuccess = {
//                    response -> run{updateReponse(response)
//                    Log.d(HomeViewModelTag, response.raw().toString())}
//            }

    }

    private fun updateReponse(response: FixerRatingsReponse) {


        currencyExchangeRateResourceLiveData.value = Resource.success(response)


    }

    private fun updateErrorReponse(throwable: Throwable) {
        currencyExchangeRateResourceLiveData.value = Resource.error(throwable.localizedMessage, null
        )
    }

    fun getRatingsLiveData(): MutableLiveData<Resource<FixerRatingsReponse>> {
        return currencyExchangeRateResourceLiveData
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}