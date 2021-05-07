package com.androidgang.mymakinglayout.viewmodel

import androidx.navigation.NavDirections
import com.androidgang.mymakinglayout.base.BaseViewModel
import com.androidgang.mymakinglayout.models.PhonesResponse
import com.androidgang.mymakinglayout.scenes.ProductsFragmentDirections
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

    fun sendArgs(item: PhonesResponse): NavDirections {
        return ProductsFragmentDirections.actionProductsFragmentToDetailsFragment(
            fullTitle = item.fullTitle,
            price = item.price,
            rating = item.rating,
            image = item.image,
            processor = item.processor,
            camera = item.camera,
            ram = item.ram,
            rom = item.rom
        )
    }
}