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
import com.androidgang.mymakinglayout.models.PhonesResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.best_seller_cell.view.*

class BestSellerAdapter(
    private val context: Context
) : RecyclerView.Adapter<BestSellerAdapter.BestSellerViewHolder>() {

    private val bestSellerList: ArrayList<PhonesResponse> = arrayListOf()

    var onBestSellerCellClickListener: OnBestSellerCellClickListener? = null

    interface OnBestSellerCellClickListener {
        fun onCellClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.best_seller_cell, parent, false)
        return BestSellerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
        val itemSeller = bestSellerList[position]
        holder.bind(itemSeller)
        holder.itemView.setOnClickListener {
            onBestSellerCellClickListener?.onCellClick(position)
        }
    }

    override fun getItemCount(): Int = bestSellerList.size

    inner class BestSellerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val ivImageProduct: ImageView = view.img_product
        private val tvPrice: TextView = view.tv_price
        private val tvOldPrice: TextView = view.tv_old_price
        private val tvFullTitle: TextView = view.tv_description

        fun bind(item: PhonesResponse) {
            Glide.with(context)
                .load(item.image)
                .into(ivImageProduct)
            tvPrice.text = item.price
            tvOldPrice.apply {
                text = item.oldPrice
                paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
            tvFullTitle.text = item.fullTitle
            if (item.isFavorite) {
                itemView.iv_like_product.visibility = View.GONE
                itemView.iv_like_product_checked.visibility = View.VISIBLE
            } else {
                itemView.iv_like_product.visibility = View.VISIBLE
                itemView.iv_like_product_checked.visibility = View.GONE
            }
        }
    }

    fun setList(list: List<PhonesResponse>) {
        bestSellerList.clear()
        bestSellerList.addAll(list)
        notifyDataSetChanged()
    }
}