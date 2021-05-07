package com.androidgang.mymakinglayout.service

import com.androidgang.mymakinglayout.models.CartResponse
import com.androidgang.mymakinglayout.models.PhonesResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("phones")
    fun getPhones(): Observable<List<PhonesResponse>>

    @GET("cart")
    fun getCart(): Observable<List<CartResponse>>

    @POST("cart")
    @FormUrlEncoded
    fun insertItemToCart(
        @Field("id") id: Int,
        @Field("fullTitle") title: String,
        @Field("price") price: String,
        @Field("image") image: String,
        @Field("counter") counter: Int
    ): Call<CartResponse>

    @PUT("cart/{idToFind}")
    @FormUrlEncoded
    fun updateItemInCart(
        @Path("idToFind") idToFind: String,
        @Field("id") id: Int,
        @Field("fullTitle") title: String,
        @Field("price") price: String,
        @Field("image") image: String,
        @Field("counter") counter: Int
    ): Call<CartResponse>

    @DELETE("cart/{idToFind}")
    fun deleteItemFromCart(@Path("idToFind") idToDelete: String): Call<CartResponse>
}