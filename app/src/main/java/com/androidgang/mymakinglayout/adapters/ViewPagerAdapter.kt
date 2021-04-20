package com.androidgang.mymakinglayout.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.models.DetailsCell
import kotlinx.android.synthetic.main.detail_cell.view.*

class ViewPagerAdapter(private val imgList: List<DetailsCell>): RecyclerView.Adapter<ViewPagerAdapter.DetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        return DetailsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.detail_cell, parent, false))
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val itemDetail = imgList[position]
        holder.bind(itemDetail)
    }

    override fun getItemCount(): Int = imgList.size

    inner class DetailsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val imgProduct: ImageView = itemView.iv_image_product

        fun bind(item: DetailsCell) {
            imgProduct.setImageResource(item.image)
        }
    }
}