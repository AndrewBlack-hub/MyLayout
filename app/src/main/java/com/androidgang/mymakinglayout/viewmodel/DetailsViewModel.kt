package com.androidgang.mymakinglayout.viewmodel

import android.content.res.Resources
import android.view.View
import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.base.BaseViewModel
import com.androidgang.mymakinglayout.models.DetailsCell
import com.androidgang.mymakinglayout.service.ApiService
import com.androidgang.mymakinglayout.service.NetworkService
import com.androidgang.mymakinglayout.usecases.CartUseCase
import io.reactivex.disposables.Disposable

class DetailsViewModel: BaseViewModel() {
    val detailsImageList = mutableListOf<DetailsCell>()

    private val cartUseCase = CartUseCase()

    fun insertItem(
        id: Int,
        title: String,
        price: String,
        image: String,
        count: Int = 1,
        view: View,
        resource: Resources
    ) {
        cartUseCase.insertItemInCart(
            id = id,
            title = title,
            price = price,
            image = image,
            counter = count,
            view = view,
            resource = resource
        )
    }

    init {
        var item = DetailsCell()
        item.image = R.drawable.viewpager2_samsung_galaxy_s20_ultra
        detailsImageList += item

        item = DetailsCell()
        item.image = R.drawable.viewpager2_galaxy_note_20_ultra
        detailsImageList += item

        item = DetailsCell()
        item.image = R.drawable.viewpager2_xiaomi_mi_10_pro
        detailsImageList += item
    }
}