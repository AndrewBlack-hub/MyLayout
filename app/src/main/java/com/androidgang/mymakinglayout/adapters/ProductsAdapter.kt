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
import com.androidgang.mymakinglayout.models.PhonesResponse
import com.bumptech.glide.Glide
import io.reactivex.subjects.BehaviorSubject

class ProductsAdapter(private val context: Context) : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    private val productsList: ArrayList<PhonesResponse> = arrayListOf()

    val behaviorSubject: BehaviorSubject<PhonesResponse> = BehaviorSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.product_cell, parent, false))
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val item = productsList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            behaviorSubject.onNext(item)
        }
    }

    override fun getItemCount(): Int = productsList.size

    inner class ProductsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleProduct = view.findViewById<TextView>(R.id.tv_phone_title)
        private val ratingBar = view.findViewById<RatingBar>(R.id.rb_phone)
        private val priceProduct = view.findViewById<TextView>(R.id.iv_add_to_cart_btn)
        private val imageProduct = view.findViewById<ImageView>(R.id.iv_phone_image)

        fun bind(item: PhonesResponse) {
            titleProduct.text = item.fullTitle
            ratingBar.rating = item.rating.toFloat()
            priceProduct.text = item.price
            Glide.with(context)
                .load(item.image)
                .into(imageProduct)
        }
    }

    fun setList(list: List<PhonesResponse>) {
        productsList.clear()
        productsList.addAll(list)
        notifyDataSetChanged()
    }
}