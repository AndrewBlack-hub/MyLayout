package com.androidgang.mymakinglayout.service

import com.androidgang.mymakinglayout.models.PhonesResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("/rest/phones")
    fun getPhones(): Observable<PhonesResponse>
}