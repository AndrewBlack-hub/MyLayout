package com.androidgang.mymakinglayout.service

import com.androidgang.mymakinglayout.models.PhonesResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("phones")
    fun getPhones(): Observable<List<PhonesResponse>>
}