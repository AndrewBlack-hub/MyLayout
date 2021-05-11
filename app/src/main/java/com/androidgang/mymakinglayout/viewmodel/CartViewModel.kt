package com.androidgang.mymakinglayout.viewmodel

import com.androidgang.mymakinglayout.base.BaseViewModel
import com.androidgang.mymakinglayout.models.CartResponse
import com.androidgang.mymakinglayout.usecases.CartUseCase

class CartViewModel : BaseViewModel() {

    private val cartUseCase = CartUseCase()

    fun loadData() {
        val dis = cartUseCase.loadItemFromCart()
            .subscribe(
                { result -> liveDataOnSuccess.value = result as List<CartResponse> },
                { throwable -> liveDataOnError.value = throwable }
            )
        cd.add(dis)
    }

    fun deleteItem(idToDel: String) {
        cartUseCase.deleteItemFromCart(idToDel)
    }

    fun updateItem(item: CartResponse, count: Int) {
        cartUseCase.updateItemInCart(
            id = item.id,
            idToFind = item._id,
            title = item.fullTitle,
            price = item.price,
            image = item.image,
            count = count
        )
    }
}