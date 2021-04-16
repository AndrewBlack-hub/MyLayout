package com.androidgang.mymakinglayout.scenes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidgang.mymakinglayout.adapters.BestSellerAdapter
import com.androidgang.mymakinglayout.adapters.MainAdapter
import com.androidgang.mymakinglayout.databinding.FragmentHomeStoreBinding
import com.androidgang.mymakinglayout.viewmodel.MainViewModel

class HomeStoreFragment : Fragment() {

    private var _binding: FragmentHomeStoreBinding? = null
    private val binding get() = _binding!!

    private var mainAdapter: MainAdapter? = null
    private var bestSellerAdapter: BestSellerAdapter? = null

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeStoreBinding.inflate(inflater, container, false)

        binding.rvCategory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvBestSellerList.layoutManager = GridLayoutManager(context, 2)

        updateUI()
        return binding.root
    }

    private fun updateUI() {
        mainAdapter = MainAdapter().apply {
            categoryList = mainViewModel.categoriesList
        }
        bestSellerAdapter = BestSellerAdapter().apply {
            bestSellerList = mainViewModel.bestSellerList
        }
        binding.rvCategory.adapter = mainAdapter
        binding.rvBestSellerList.adapter = bestSellerAdapter

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}