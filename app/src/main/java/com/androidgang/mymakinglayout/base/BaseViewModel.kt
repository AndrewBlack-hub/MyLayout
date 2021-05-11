package com.androidgang.mymakinglayout.base

import android.content.res.Resources
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.models.FavoritesResponse
import com.androidgang.mymakinglayout.service.ApiService
import com.androidgang.mymakinglayout.service.NetworkService
import com.androidgang.mymakinglayout.usecases.FavoriteUseCase
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseViewModel : ViewModel() {

    private val favoriteUseCase = FavoriteUseCase()

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

    fun insertItemInFavorite(item: FavoritesResponse, view: View, resources: Resources) {
        favoriteUseCase.insertItemInFavorite(item, view, resources)
    }

    fun delItemFromFavorite(idToDel: String) {
        favoriteUseCase.deleteItemFromFavorite(idToDel)
    }
}