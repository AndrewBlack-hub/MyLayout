package com.androidgang.mymakinglayout.service

import com.androidgang.mymakinglayout.models.FavoritesResponse
import com.androidgang.mymakinglayout.models.PhonesResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("phones")
    fun getPhones(): Observable<List<PhonesResponse>>

    @GET("favorites")
    fun getFavorites(): Observable<List<FavoritesResponse>>

    @POST("favorites")
    @FormUrlEncoded
    fun insertItemInFavorite(
        @Field("id") id: Int,
        @Field("fullTitle") title: String,
        @Field("price") price: String,
        @Field("oldPrice") oldPrice: String,
        @Field("isFavorite") isFavorite: Boolean,
        @Field("rating") rating: Int,
        @Field("processor") processor: String,
        @Field("camera") camera: String,
        @Field("ram") ram: String,
        @Field("rom") rom: String,
        @Field("image") image: String,
    ): Call<FavoritesResponse>

    @DELETE("favorites/{idToFind}")
    fun delItemFromFavorite(
        @Path("idToFind") idToDel: String
    ): Call<FavoritesResponse>
}