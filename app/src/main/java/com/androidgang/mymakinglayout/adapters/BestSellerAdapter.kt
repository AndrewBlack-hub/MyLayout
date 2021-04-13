package com.androidgang.mymakinglayout.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.models.BestSellerCell
import kotlinx.android.synthetic.main.best_seller_cell.view.*

class BestSellerAdapter : RecyclerView.Adapter<BestSellerAdapter.BestSellerViewHolder>() {

    var bestSellerList: List<BestSellerCell> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.best_seller_cell, parent, false)
        return BestSellerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
        val itemSeller = bestSellerList[position]
        holder.bind(itemSeller)
    }

    override fun getItemCount(): Int = bestSellerList.size

    inner class BestSellerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val ivImageProduct: ImageView = view.img_product
        private val tvPrice: TextView = view.tv_price
        private val tvOldPrice: TextView = view.tv_old_price
        private val tvDescription: TextView = view.tv_description

        fun bind(item: BestSellerCell) {
            ivImageProduct.setImageResource(item.img)
            tvPrice.text = item.price
            tvOldPrice.text = item.oldPrice
            tvDescription.text = item.description
        }
    }
}