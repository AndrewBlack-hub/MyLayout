package com.androidgang.mymakinglayout.viewmodel

import androidx.lifecycle.ViewModel
import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.models.DetailsCell

class DetailsViewModel: ViewModel() {
    val detailsImageList = mutableListOf<DetailsCell>()

    init {
        var item = DetailsCell()
        item.image = R.drawable.viewpager2_samsung_galaxy_s20_ultra
        detailsImageList += item

        item = DetailsCell()
        item.image = R.drawable.viewpager2_galaxy_note_20_ultra
        detailsImageList += item

        item = DetailsCell()
        item.image = R.drawable.viewpager2_xiaomi_mi_10_pro
        detailsImageList += item
    }
}