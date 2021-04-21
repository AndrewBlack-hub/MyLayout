package com.androidgang.mymakinglayout.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.models.BestSellerCell
import kotlinx.android.synthetic.main.best_seller_cell.view.*

//Создаем класс Адаптер и наследуем от класса RecyclerView.Adapter<ViewHolder>() обязательные для реализации методы
class BestSellerAdapter : RecyclerView.Adapter<BestSellerAdapter.BestSellerViewHolder>() {

    //Создаем пустой список(List) типа BestSellerCell
    var bestSellerList: List<BestSellerCell> = listOf()

    //Метод отвечает за создание представления(View) на дисплее, оборачивает его в Holder и возвращает результат
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerViewHolder {
        //
        //LayoutInflater.from(paren.context) контейнер, где будет храниться ячейки (RecyclerView)
        //.inflate(layout, parent, attachToRoot) layout - указываем из какого макета мы будем надувать представление(View)
        // parent - родительский элемент, контейнер (RecyclerView)
        // attachToRoot - нужно ли помещать ViewHolder в родительский контейнер.
        // false - потому, что RecyclerView и так помещает в себя ViewHolder
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.best_seller_cell, parent, false)
        //Возвращаем ViewHolder с view
        return BestSellerViewHolder(view)
    }

    //Отвечает за заполнение данного Холдера данными из конкетной позиции
    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
        //Берем конкретный объект по позиции из всего списка
        val itemSeller = bestSellerList[position]
        //Передаем конкретный объект в качестве аргумента в метод ViewHolder-a, в котором будет происходить инициализация необходимых view
        holder.bind(itemSeller)
    }

    //Возвращает колчество элементов в списке, отвечая на запрос Recycler-a
    override fun getItemCount(): Int = bestSellerList.size

    //Создаем внутренний класс ViewHolder, который принимает в качестве параметра View и наследует класс RecyclerView.ViewHolder(view)
    inner class BestSellerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        //Присваеваем переменной ссылку на конкретное Вью
        private val ivImageProduct: ImageView = view.img_product
        //Присваеваем переменной ссылку на конкретное Вью
        private val tvPrice: TextView = view.tv_price
        //Присваеваем переменной ссылку на конкретное Вью
        private val tvOldPrice: TextView = view.tv_old_price
        //Присваеваем переменной ссылку на конкретное Вью
        private val tvDescription: TextView = view.tv_description

        //Устанавливаем каждой Вью соответсвующие данные из списка bestSellerList типа BestSellerCell
        fun bind(item: BestSellerCell) {
            //Устанавливаем ImageView картинку из объекта item[position]
            ivImageProduct.setImageResource(item.img)
            //Устанавливаем TextView текст из объекта item
            tvPrice.setText(item.price)
            //Устанавливаем TextView текст и флаг(перечеркивание текста), для удобства инициализации используем функцию-расширения apply
            tvOldPrice.apply {
                //Устанавливаем текст
                setText(item.oldPrice)
                //Устанавливаем тексту флаг
                paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
            //Устанавливаем текст
            tvDescription.setText(item.description)
            //Используем логический оператов if, если значение переменной объекта true, тогда..
            if (item.isChecked) {
                //ImageView исчезает
                itemView.iv_like_product.visibility = View.GONE
                //ImageView становится видимой
                itemView.iv_like_product_checked.visibility = View.VISIBLE
                //Иначе(значение переменной объекта false)
            } else {
                //ImageView становится видимой
                itemView.iv_like_product.visibility = View.VISIBLE
                //ImageView исчезает
                itemView.iv_like_product_checked.visibility = View.GONE
            }
        }
    }
}