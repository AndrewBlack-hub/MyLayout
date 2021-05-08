package com.androidgang.mymakinglayout.viewmodel

import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.base.BaseViewModel
import com.androidgang.mymakinglayout.models.CategoryCell
import com.androidgang.mymakinglayout.models.PhonesResponse
import com.androidgang.mymakinglayout.usecases.PhonesUseCase

class HomeStoreViewModel: BaseViewModel() {

    private val phonesUseCase = PhonesUseCase()

    fun loadData() {
        val dis = phonesUseCase.loadPhones()
            .subscribe(
                { result -> liveDataOnSuccess.value = result as List<PhonesResponse> },
                { throwable -> liveDataOnError.value = throwable }
            )
        cd.add(dis)
    }

    fun updateBestSellerItem(
        idToFind: String,
        isFavorite: Boolean
    ) {
        phonesUseCase.updateItemInBestSeller(
            idToFind = idToFind,
            isFavorite = isFavorite
        )
    }

    val categoriesList = mutableListOf<CategoryCell>()

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
    }
}