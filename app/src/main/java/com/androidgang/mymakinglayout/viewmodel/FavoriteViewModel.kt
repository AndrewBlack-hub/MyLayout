package com.androidgang.mymakinglayout.viewmodel

import android.content.res.Resources
import android.view.View
import com.androidgang.mymakinglayout.base.BaseViewModel
import com.androidgang.mymakinglayout.models.FavoritesResponse
import com.androidgang.mymakinglayout.usecases.FavoriteUseCase

class FavoriteViewModel : BaseViewModel() {

    private val favoriteUseCase = FavoriteUseCase()

    fun loadData() {
        val dis = favoriteUseCase.loadFavorites()
            .subscribe(
                { result -> liveDataOnSuccess.value = result as List<FavoritesResponse> },
                { throwable -> liveDataOnError.value = throwable }
            )
        cd.add(dis)
    }


}