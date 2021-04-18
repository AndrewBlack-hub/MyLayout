package com.androidgang.mymakinglayout.viewmodel

import androidx.lifecycle.ViewModel
import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.models.BestSellerCell
import com.androidgang.mymakinglayout.models.CategoryCell

class HomeStoreViewModel: ViewModel() {
    val categoriesList = mutableListOf<CategoryCell>()

    val bestSellerList = mutableListOf<BestSellerCell>()

    init {
        var category = CategoryCell()
        category.category = R.string.category_phones
        category.img = R.drawable.ic_category_phones_grey
        category.isPressed = true
        categoriesList += category

        category = CategoryCell()
        category.category = R.string.category_computer
        category.img = R.drawable.ic_category_computer_grey
        category.isPressed = false
        categoriesList += category

        category = CategoryCell()
        category.category = R.string.category_health
        category.img = R.drawable.ic_category_health_grey
        category.isPressed = false
        categoriesList += category

        category = CategoryCell()
        category.category = R.string.category_books
        category.img = R.drawable.ic_category_books_grey
        category.isPressed = false
        categoriesList += category

        category = CategoryCell()
        category.category = R.string.category_phones
        category.img = R.drawable.ic_category_phones_grey
        category.isPressed = false
        categoriesList += category


        var bestSellerCell = BestSellerCell()
        bestSellerCell.img = R.drawable.samsung_galaxy_s20_ultra
        bestSellerCell.price = R.string.best_seller_price_item_1
        bestSellerCell.oldPrice = R.string.best_seller_old_price_item_1
        bestSellerCell.description = R.string.best_seller_description_item_1
        bestSellerCell.isChecked = false
        bestSellerList += bestSellerCell

        bestSellerCell = BestSellerCell()
        bestSellerCell.img = R.drawable.xiaomi_mi_10_pro
        bestSellerCell.price = R.string.best_seller_price_item_2
        bestSellerCell.oldPrice = R.string.best_seller_old_price_item_2
        bestSellerCell.description = R.string.best_seller_description_item_2
        bestSellerCell.isChecked = true
        bestSellerList += bestSellerCell

        bestSellerCell = BestSellerCell()
        bestSellerCell.img = R.drawable.samsung_note_20_ultra
        bestSellerCell.price = R.string.best_seller_price_item_3
        bestSellerCell.oldPrice = R.string.best_seller_old_price_item_3
        bestSellerCell.description = R.string.best_seller_description_item_3
        bestSellerCell.isChecked = false
        bestSellerList += bestSellerCell

        bestSellerCell = BestSellerCell()
        bestSellerCell.img = R.drawable.motorola_one_edge
        bestSellerCell.price = R.string.best_seller_price_item_4
        bestSellerCell.oldPrice = R.string.best_seller_old_price_item_4
        bestSellerCell.description = R.string.best_seller_description_item_4
        bestSellerCell.isChecked = true
        bestSellerList += bestSellerCell

    }
}