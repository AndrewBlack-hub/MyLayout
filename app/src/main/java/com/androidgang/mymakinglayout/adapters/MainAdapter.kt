package com.androidgang.mymakinglayout.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidgang.mymakinglayout.models.CategoryCells
import com.androidgang.mymakinglayout.R
import kotlinx.android.synthetic.main.category_cell.view.*

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var categoryList: List<CategoryCells> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_cell, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val itemCategory = categoryList[position]
        holder.bind(itemCategory)
    }

    override fun getItemCount(): Int = categoryList.size

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imgCategory: ImageView = view.iv_category_ic_grey
        private val tvCategoryTitle: TextView = view.tv_category_title

        fun bind(item: CategoryCells) {
            tvCategoryTitle.setText(item.category)
            imgCategory.setImageResource(item.img)
            if (item.isPressed) {
                itemView.iv_category_ellipse.visibility = View.INVISIBLE
                itemView.iv_category_ellipse_selected.visibility = View.VISIBLE
                itemView.iv_category_ic_white.visibility = View.VISIBLE
                itemView.iv_category_ic_grey.visibility = View.GONE
                itemView.tv_category_title.setTextColor(itemView.resources.getColor(R.color.main_orange_color))
            } else {
                itemView.iv_category_ellipse.visibility = View.VISIBLE
                itemView.iv_category_ellipse_selected.visibility = View.GONE
                itemView.iv_category_ic_white.visibility = View.GONE
                itemView.iv_category_ic_grey.visibility = View.VISIBLE
                itemView.tv_category_title.setTextColor(itemView.resources.getColor(R.color.dark_blue))
            }
        }
    }
}