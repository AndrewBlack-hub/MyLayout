package com.androidgang.mymakinglayout.scenes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.adapters.BestSellerAdapter
import com.androidgang.mymakinglayout.adapters.HomeStoreAdapter
import com.androidgang.mymakinglayout.databinding.FragmentHomeStoreBinding
import com.androidgang.mymakinglayout.models.PhonesResponse
import com.androidgang.mymakinglayout.viewmodel.HomeStoreViewModel

class HomeStoreFragment : Fragment() {

    private var _binding: FragmentHomeStoreBinding? = null
    private val binding get() = _binding!!

    private var homeStoreAdapter: HomeStoreAdapter? = null
    private var bestSellerAdapter: BestSellerAdapter? = null

    private val homeStoreViewModel: HomeStoreViewModel by lazy {
        ViewModelProvider(this).get(HomeStoreViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeStoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
    }

    private fun initialization() {
        initRVCategory()
        initRVBestSeller()
        initCategoryAdapter()
        loadData()
        liveDataObservers()
        initBestSellerAdapter()
        openFilterFragment()
        implClickListenerAdapter()

    }

    private fun initRVCategory() {
        binding.rvCategory.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL, false
        )
    }

    private fun initRVBestSeller() {
        binding.rvBestSellerList.layoutManager = GridLayoutManager(context, 2)
    }

    private fun initCategoryAdapter() {
        homeStoreAdapter = HomeStoreAdapter().apply {
            categoryList = homeStoreViewModel.categoriesList
        }
        binding.rvCategory.adapter = homeStoreAdapter
    }

    private fun initBestSellerAdapter() {
        bestSellerAdapter = BestSellerAdapter(requireContext())
        binding.rvBestSellerList.adapter = bestSellerAdapter
    }

    private fun liveDataObservers() {
        homeStoreViewModel.getLiveDataOnError.observe(viewLifecycleOwner, Observer {
            Log.e("TAG", "getLiveDataOnError: ${it.message}")
        })

        homeStoreViewModel.getLiveDataOnSuccess.observe(viewLifecycleOwner, Observer {
            dataLoaded(it as List<PhonesResponse>)
            Log.e("TAG", "data loaded success!")
        })
    }

    private fun dataLoaded(list: List<PhonesResponse>) {
        bestSellerAdapter?.setList(list)
    }

    private fun loadData() {
        homeStoreViewModel.loadData() //Загружаем данные с сервера в liveData
        Log.e("TAG", "данные с сервера")
    }

    private fun implClickListenerAdapter() {
        bestSellerAdapter?.onBestSellerCellClickListener = object : BestSellerAdapter
        .OnBestSellerCellClickListener {
            override fun onCellClick(item: PhonesResponse) {
                val action = HomeStoreFragmentDirections.actionHomeStoreFragmentToDetailsFragment(
                    fullTitle = item.fullTitle,
                    price = item.price,
                    rating = item.rating,
                    image = item.image,
                    processor = item.processor,
                    camera = item.camera,
                    ram = item.ram,
                    rom = item.rom
                )
                findNavController().navigate(action)
            }
        }
    }

    private fun openFilterFragment() {
        binding.ivFilterIc.setOnClickListener {
            findNavController().navigate(R.id.action_homeStoreFragment_to_bottomSheetFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        homeStoreViewModel.disposeObservers()
    }
}