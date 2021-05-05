package com.androidgang.mymakinglayout.viewmodel

import com.androidgang.mymakinglayout.base.BaseViewModel
import com.androidgang.mymakinglayout.models.PhonesResponse
import com.androidgang.mymakinglayout.usecases.PhonesUseCase

class ProductsViewModel : BaseViewModel() {
    private val phonesUseCase = PhonesUseCase()

    fun loadData() {
        val dis = phonesUseCase.loadPhones()
            .subscribe(
                { result -> liveDataOnSuccess.value = result as List<PhonesResponse> },
                { throwable -> liveDataOnError.value = throwable }
            )
        cd.add(dis)
    }
}