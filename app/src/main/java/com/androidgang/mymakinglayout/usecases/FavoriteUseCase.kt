package com.androidgang.mymakinglayout.usecases

import android.content.res.Resources
import android.util.Log
import android.view.View
import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.models.FavoritesResponse
import com.androidgang.mymakinglayout.service.ApiService
import com.androidgang.mymakinglayout.service.NetworkService
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteUseCase {

    fun loadFavorites(): Observable<List<FavoritesResponse>> {
        return NetworkService.buildService(ApiService::class.java)
            .getFavorites()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertItemInFavorite(
        item: FavoritesResponse,
        view: View,
        resource: Resources
    ) {
        NetworkService.buildService(ApiService::class.java)
            .insertItemInFavorite(
                id = item.id,
                title = item.fullTitle,
                price = item.price,
                oldPrice = item.oldPrice,
                isFavorite = item.isFavorite,
                rating = item.rating,
                processor = item.processor,
                camera = item.camera,
                ram = item.ram,
                rom = item.rom,
                image = item.image
            ).enqueue( object : Callback<FavoritesResponse> {
                override fun onResponse(
                    call: Call<FavoritesResponse>,
                    response: Response<FavoritesResponse>
                ) {
                    if (response.isSuccessful) {
                        val successAdd = Snackbar.make(
                            view,
                            resource.getString(R.string.snackbar_favorite_success_add),
                            Snackbar.LENGTH_SHORT
                        )
                        successAdd.show()
                        Log.e("TAG", "insert item in cart: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<FavoritesResponse>, t: Throwable) {
                    val successAdd = Snackbar.make(
                        view,
                        resource.getString(R.string.snackbar_favorite_fail_add),
                        Snackbar.LENGTH_SHORT
                    )
                    successAdd.show()
                    Log.e("TAG", "insert item in cart fail: ${t.message}")
                }
            })
    }

    fun deleteItemFromFavorite(idToDel: String) {
        NetworkService.buildService(ApiService::class.java)
            .delItemFromFavorite(idToDel = idToDel)
            .enqueue( object : Callback<FavoritesResponse> {
                override fun onResponse(
                    call: Call<FavoritesResponse>,
                    response: Response<FavoritesResponse>
                ) {
                    Log.e("TAG", "delete item from favorite: ${response.body()}")
                }

                override fun onFailure(call: Call<FavoritesResponse>, t: Throwable) {
                    Log.e("TAG", "delete item fail: ${t.message}")
                }
            })
    }
}