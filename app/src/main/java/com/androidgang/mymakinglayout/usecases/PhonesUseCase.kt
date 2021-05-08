package com.androidgang.mymakinglayout.usecases

import android.util.Log
import com.androidgang.mymakinglayout.models.PhonesResponse
import com.androidgang.mymakinglayout.service.ApiService
import com.androidgang.mymakinglayout.service.NetworkService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhonesUseCase {

    fun loadPhones(): Observable<List<PhonesResponse>> {
        return NetworkService.buildService(ApiService::class.java)
            .getPhones()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateItemInBestSeller(
        idToFind: String,
        isFavorite: Boolean,
    ) {
        NetworkService.buildService(ApiService::class.java)
            .updateBestSellerItem(
                idToFind = idToFind,
                isFavorite = isFavorite
            ).enqueue( object : Callback<PhonesResponse> {
                override fun onResponse(
                    call: Call<PhonesResponse>,
                    response: Response<PhonesResponse>
                ) {
                    Log.e("TAG", "update item in bestSeller success: ${response.body()}")
                }

                override fun onFailure(call: Call<PhonesResponse>, t: Throwable) {
                    Log.e("TAG", "update item in bestSeller fail: ${t.message}")
                }
            })
    }
}