package com.androidgang.mymakinglayout.usecases

import com.androidgang.mymakinglayout.models.PhonesResponse
import com.androidgang.mymakinglayout.service.ApiService
import com.androidgang.mymakinglayout.service.NetworkService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PhonesUseCase {

    fun loadPhones(): Observable<List<PhonesResponse>> {
        return NetworkService.buildService(ApiService::class.java)
            .getPhones()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}