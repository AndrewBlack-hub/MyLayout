package com.androidgang.mymakinglayout.usecases

import android.content.res.Resources
import android.util.Log
import android.view.View
import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.models.CartResponse
import com.androidgang.mymakinglayout.service.ApiService
import com.androidgang.mymakinglayout.service.NetworkService
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartUseCase {

    fun loadItemFromCart(): Observable<List<CartResponse>> {
        return NetworkService.buildService(ApiService::class.java)
            .getCart()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertItemInCart(
        id: Int,
        title: String,
        price: String,
        image: String,
        counter: Int = 1,
        view: View,
        resource: Resources
    ) {
        NetworkService.buildService(ApiService::class.java)
            .insertItemToCart(
                id = id,
                title = title,
                price = price,
                image = image,
                counter = counter
            ).enqueue(object : Callback<CartResponse> {
                override fun onResponse(
                    call: Call<CartResponse>,
                    response: Response<CartResponse>
                ) {
                    if (response.isSuccessful) {
                        val successAdd = Snackbar.make(
                            view,
                            resource.getString(R.string.snackbar_success_add),
                            Snackbar.LENGTH_SHORT
                        )
                        successAdd.show()
                        Log.e("TAG", "insert item in cart: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                    val successAdd = Snackbar.make(
                        view,
                        resource.getString(R.string.snackbar_failure_add),
                        Snackbar.LENGTH_SHORT
                    )
                    successAdd.show()
                    Log.e("TAG", "insert item in cart fail: ${t.message}")
                }
            })
    }

    fun updateItemInCart(
        id: Int,
        idToFind: String,
        title: String,
        price: String,
        image: String,
        count: Int = 1
    ) {
        val newCount = if (count < 1) {
            1
        } else {
            count
        }

        NetworkService.buildService(ApiService::class.java)
            .updateItemInCart(
                id = id,
                idToFind = idToFind,
                title = title,
                price = price,
                image = image,
                counter = newCount
            ).enqueue( object : Callback<CartResponse> {
                override fun onResponse(
                    call: Call<CartResponse>,
                    response: Response<CartResponse>
                ) {
                    Log.e("TAG", "update item in cart: ${response.body()}")
                }

                override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                    Log.e("TAG", "update item fail: ${t.message}")
                }
            })
    }

    fun deleteItemFromCart(idToDelete: String) {
        NetworkService.buildService(ApiService::class.java)
            .deleteItemFromCart(idToDelete = idToDelete)
            .enqueue( object : Callback<CartResponse> {
                override fun onResponse(
                    call: Call<CartResponse>,
                    response: Response<CartResponse>
                ) {
                    Log.e("TAG", "delete item from cart: ${response.body()}")
                }

                override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                    Log.e("TAG", "delete item fail: ${t.message}")
                }
            })
    }
}