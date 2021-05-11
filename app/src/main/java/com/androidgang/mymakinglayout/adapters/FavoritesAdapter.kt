package com.androidgang.mymakinglayout.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.models.FavoritesResponse
import com.bumptech.glide.Glide
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.favorite_cell.view.*

class FavoritesAdapter(private val context: Context) : RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

    private val favoriteList: ArrayList<FavoritesResponse> = arrayListOf()
    val behaviorFavorite: BehaviorSubject<FavoritesResponse> = BehaviorSubject.create()
    //Добавить слушатель(BehaviorSubject) на кнопку "добавить в корзину"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_cell, parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = favoriteList[position]
        holder.bind(item)
        holder.bindImage(item)
        holder.itemView.iv_favorite_phone_btn.setOnClickListener {
            behaviorFavorite.onNext(item)
        }
    }

    override fun getItemCount(): Int = favoriteList.size

    inner class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleProduct = view.findViewById<TextView>(R.id.tv_favorite_phone_title)
        private val ratingBar = view.findViewById<RatingBar>(R.id.rb_favorite_phone)
        private val priceProduct = view.findViewById<TextView>(R.id.iv_favorite_add_to_cart_btn)
        private val imageProduct = view.findViewById<ImageView>(R.id.iv_favorite_phone_image)

        fun bind(item: FavoritesResponse) {
            titleProduct.text = item.fullTitle
            ratingBar.rating = item.rating.toFloat()
            priceProduct.text = item.price
        }

        fun bindImage(item: FavoritesResponse) {
            Glide.with(context)
                .load(item.image)
                .into(imageProduct)
        }
    }

    fun setList(list: List<FavoritesResponse>) {
        favoriteList.clear()
        favoriteList.addAll(list)
        notifyDataSetChanged()
    }
}