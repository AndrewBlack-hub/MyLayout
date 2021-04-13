package com.androidgang.mymakinglayout.viewmodel

import androidx.lifecycle.ViewModel
import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.models.BestSellerCell
import com.androidgang.mymakinglayout.models.CategoryCells

class MainViewModel: ViewModel() {
    val categoriesList = mutableListOf<CategoryCells>()
    var isSelectedCategory: Boolean = false

    val bestSellerList = mutableListOf<BestSellerCell>()

    init {
        var category = CategoryCells()
        category.category = "Phones"
        category.img = R.drawable.ic_category_phones_grey
        categoriesList += category

        category = CategoryCells()
        category.category = "Computer"
        category.img = R.drawable.ic_category_computer_grey
        categoriesList += category

        category = CategoryCells()
        category.category = "Health"
        category.img = R.drawable.ic_category_health_grey
        categoriesList += category

        category = CategoryCells()
        category.category = "Books"
        category.img = R.drawable.ic_category_books_grey
        categoriesList += category

        category = CategoryCells()
        category.category = "Some else"
        category.img = R.drawable.ic_category_phones_grey
        categoriesList += category


        var bestSellerCell = BestSellerCell()
        bestSellerCell.img = R.drawable.samsung_galaxy_s20_ultra
        bestSellerCell.price = "$1,047"
        bestSellerCell.oldPrice = "̶\$̶1̶,̶5̶0̶0̶"
        bestSellerCell.description = "Samsung Galaxy S20 Ultra"
        bestSellerCell.isChecked = false
        bestSellerList += bestSellerCell

        bestSellerCell = BestSellerCell()
        bestSellerCell.img = R.drawable.xiaomi_mi_10_pro
        bestSellerCell.price = "$300"
        bestSellerCell.oldPrice = " ̶\$̶4̶0̶0̶"
        bestSellerCell.description = "Xiaomi Mi 10 Pro"
        bestSellerCell.isChecked = false
        bestSellerList += bestSellerCell

        bestSellerCell = BestSellerCell()
        bestSellerCell.img = R.drawable.samsung_note_20_ultra
        bestSellerCell.price = "$1,047"
        bestSellerCell.oldPrice = "̶\$̶1̶,̶5̶0̶0̶"
        bestSellerCell.description = "Samsung Note 20 Ultra"
        bestSellerCell.isChecked = false
        bestSellerList += bestSellerCell

        bestSellerCell = BestSellerCell()
        bestSellerCell.img = R.drawable.motorola_one_edge
        bestSellerCell.price = "$300"
        bestSellerCell.oldPrice = " ̶\$̶4̶0̶0̶"
        bestSellerCell.description = "Motorola One Edge"
        bestSellerCell.isChecked = false
        bestSellerList += bestSellerCell

    }
}