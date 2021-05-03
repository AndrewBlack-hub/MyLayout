package com.androidgang.mymakinglayout.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    //live data
    protected val liveDataOnSuccess = MutableLiveData<Any>()
    protected val liveDataOnError = MutableLiveData<Throwable>()

    //getters live data
    val getLiveDataOnSuccess = liveDataOnSuccess
    val getLiveDataOnError = liveDataOnError

    internal val cd = CompositeDisposable()

    fun disposeObservers(){
        cd.dispose()
        cd.clear()
    }

}