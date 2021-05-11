package com.androidgang.mymakinglayout.models

import com.google.gson.annotations.SerializedName

data class PhonesResponse(
    @SerializedName("_id")
    var _id: String = "",

    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("price")
    var price: String = "",

    @SerializedName("image")
    var image: String = "",

    @SerializedName("fullTitle")
    var fullTitle: String = "",

    @SerializedName("oldPrice")
    var oldPrice: String = "",

    @SerializedName("isFavorite")
    var isFavorite: Boolean = false,

    @SerializedName("processor")
    var processor: String = "",

    @SerializedName("rating")
    var rating: Int = 0,

    @SerializedName("camera")
    var camera: String = "",

    @SerializedName("ram")
    var ram: String = "",

    @SerializedName("rom")
    var rom: String = ""
)