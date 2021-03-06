package com.androidgang.mymakinglayout.adapters

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.models.FavoritesResponse
import com.androidgang.mymakinglayout.models.PhonesResponse
import com.bumptech.glide.Glide
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.best_seller_cell.view.*

class BestSellerAdapter(
    private val context: Context
) : RecyclerView.Adapter<BestSellerAdapter.BestSellerViewHolder>() {

    private val bestSellerList: ArrayList<PhonesResponse> = arrayListOf()

    val behaviorSubject: BehaviorSubject<PhonesResponse> = BehaviorSubject.create()
    val behaviorFavorite: BehaviorSubject<PhonesResponse> = BehaviorSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.best_seller_cell, parent, false)
        return BestSellerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
        val itemSeller = bestSellerList[position]
        holder.bind(itemSeller)
        holder.bindImage(itemSeller)
        holder.bindIsFavoriteState(itemSeller)
        holder.itemView.setOnClickListener {
            behaviorSubject.onNext(itemSeller)
        }
        holder.itemView.iv_like_product.setOnClickListener {
            behaviorFavorite.onNext(itemSeller)
        }
    }

    override fun getItemCount(): Int = bestSellerList.size

    inner class BestSellerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val ivImageProduct: ImageView = view.img_product
        private val tvPrice: TextView = view.tv_price
        private val tvOldPrice: TextView = view.tv_old_price
        private val tvFullTitle: TextView = view.tv_description

        fun bind(item: PhonesResponse) {
            tvPrice.text = item.price
            tvOldPrice.apply {
                text = item.oldPrice
                paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
            tvFullTitle.text = item.fullTitle
        }

        fun bindIsFavoriteState(item: PhonesResponse) {
            if (item.isFavorite) {
                itemView.iv_like_product.setImageResource(R.drawable.ic_like_full)
            } else {
                itemView.iv_like_product.setImageResource(R.drawable.ic_like_empty)
            }
        }

        fun bindImage(item: PhonesResponse) {
            Glide.with(context)
                .load(item.image)
                .into(ivImageProduct)
        }
    }

    fun setList(list: List<PhonesResponse>) {
        bestSellerList.clear()
        bestSellerList.addAll(list)
        notifyDataSetChanged()
    }
}