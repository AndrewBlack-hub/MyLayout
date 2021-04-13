package com.androidgang.mymakinglayout.models

data class BestSellerCell(
    var price: String = "",
    var oldPrice: String = "",
    var description: String = "",
    var img: Int = 0,
    var isChecked: Boolean = false
)