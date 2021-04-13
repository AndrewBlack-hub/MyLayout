package com.androidgang.mymakinglayout.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.androidgang.mymakinglayout.models.CategoryCells
import com.androidgang.mymakinglayout.R
import kotlinx.android.synthetic.main.category_cell.view.*

class MainAdapter(private val context: Context?): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var categoryList: List<CategoryCells> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_cell, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val itemCategory = categoryList[position]
        holder.bind(itemCategory)
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "${itemCategory.category} pressed!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = categoryList.size

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imgCategory: ImageView = view.iv_category_ic
        private val tvCategoryTitle: TextView = view.tv_category_title

        fun bind(item: CategoryCells) {
            tvCategoryTitle.text = item.category
            imgCategory.setImageResource(item.img)
        }
    }
}