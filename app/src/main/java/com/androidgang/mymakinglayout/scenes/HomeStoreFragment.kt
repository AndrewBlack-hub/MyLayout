package com.androidgang.mymakinglayout.scenes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.adapters.BestSellerAdapter
import com.androidgang.mymakinglayout.adapters.MainAdapter
import com.androidgang.mymakinglayout.databinding.FragmentHomeStoreBinding
import com.androidgang.mymakinglayout.viewmodel.MainViewModel

//Класс HomeStoreFragment наследуется от класса Fragment
class HomeStoreFragment : Fragment() {

    //создаем изменяемую nullable переменную типа Биндинг
    private var _binding: FragmentHomeStoreBinding? = null
    //присваиваем через геттер
    private val binding get() = _binding!!

    //создаем nullable переменную адаптера
    private var homeStoreAdapter: HomeStoreAdapter? = null
    //создаем nullable переменную адаптера
    private var bestSellerAdapter: BestSellerAdapter? = null

    //Создаем переменную типа HomeStoreViewModel, отложенной инициализации
    private val homeStoreViewModel: HomeStoreViewModel by lazy {
        //Связываемся с ЖЦ фрагмента и возвращаем существующую вьюМодель
        ViewModelProvider(this).get(HomeStoreViewModel::class.java)
    }

    //Метод в котором мы создаем наш интерфейс (подключаем layout)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //создаем экземпляр класса привязки для использования фрагмента
        _binding = FragmentHomeStoreBinding.inflate(inflater, container, false)
        //возвращаем корневое представление
        return binding.root
    }

    //Метод в котором наш layout создан (для вызова методов у View)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Передаем аргументы в родительский метод и вызываем его
        super.onViewCreated(view, savedInstanceState)
        //настраиваем отображение Ресайклера на экране
        binding.rvCategory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        //настраиваем отображение Ресайклера на экране
        binding.rvBestSellerList.layoutManager = GridLayoutManager(context, 2)
        updateUI()
        //обрадатываем нажатие у Вью элемента
        binding.ivFilterIc.setOnClickListener {
            //переходим на другой фрагмент(вызываем bottom sheet dialog)
            findNavController().navigate(R.id.action_homeStoreFragment_to_bottomSheetFragment)
        }
    }

    //методо для обнавления UI
    private fun updateUI() {
        //инициализируем адаптер
        homeStoreAdapter = HomeStoreAdapter().apply {
            //передаем в адаптер данные из вьюМодели
            categoryList = homeStoreViewModel.categoriesList
        }
        //инициализируем адаптер
        bestSellerAdapter = BestSellerAdapter().apply {
            //передаем в адаптер данные из вьюМодели
            bestSellerList = homeStoreViewModel.bestSellerList
        }
        //присваиваем Ресайклеру адаптер
        binding.rvCategory.adapter = homeStoreAdapter
        //присваиваем Ресайклеру адаптер
        binding.rvBestSellerList.adapter = bestSellerAdapter
    }

    // Метод уничтожения фрагмента
    override fun onDestroy() {
        //вызываем метод родителя
        super.onDestroy()
        //обнуляем переменную при уничтожении фрагмента
        _binding = null
    }
}