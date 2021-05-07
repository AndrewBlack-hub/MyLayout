package com.androidgang.mymakinglayout.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.models.CartResponse
import com.bumptech.glide.Glide
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.cart_cell.view.*

class CartAdapter(private val context: Context) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val cartList: ArrayList<CartResponse> = arrayListOf()

    val behaviorTrash: BehaviorSubject<CartResponse> = BehaviorSubject.create()
    val behaviorPlus: BehaviorSubject<CartResponse> = BehaviorSubject.create()
    val behaviorMinus: BehaviorSubject<CartResponse> = BehaviorSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.cart_cell, parent, false))
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartList[position]
        holder.bind(item)
        holder.bindImage(item)
        holder.itemView.iv_cart_trash_can.setOnClickListener {
            behaviorTrash.onNext(item)
        }
        holder.itemView.iv_ic_plus.setOnClickListener {
            behaviorPlus.onNext(item)
        }
        holder.itemView.iv_ic_minus.setOnClickListener {
            behaviorMinus.onNext(item)
        }
    }

    override fun getItemCount(): Int = cartList.size

    inner class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.tv_cart_title_product)
        private val price = view.findViewById<TextView>(R.id.tv_cart_price_product)
        private val image = view.findViewById<ImageView>(R.id.iv_cart_product)
        private val counter = view.findViewById<TextView>(R.id.tv_cart_counter)

        fun bind(item: CartResponse) {
            title.text = item.fullTitle
            price.text = item.price
            counter.text = item.counter.toString()
        }

        fun bindImage(item: CartResponse) {
            Glide.with(context)
                .load(item.image)
                .into(image)
        }
    }

    fun setList(list: List<CartResponse>) {
        cartList.clear()
        cartList.addAll(list)
        notifyDataSetChanged()
    }
}