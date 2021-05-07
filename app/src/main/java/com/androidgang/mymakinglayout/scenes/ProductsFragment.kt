package com.androidgang.mymakinglayout.scenes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidgang.mymakinglayout.adapters.ProductsAdapter
import com.androidgang.mymakinglayout.databinding.ProductsFragmentBinding
import com.androidgang.mymakinglayout.models.PhonesResponse
import com.androidgang.mymakinglayout.viewmodel.ProductsViewModel
import io.reactivex.disposables.CompositeDisposable

class ProductsFragment : Fragment() {

    private var _binding: ProductsFragmentBinding? = null
    private val binding get() = _binding!!
    private val cd = CompositeDisposable()

    private val viewModel by lazy {
        ViewModelProvider(this@ProductsFragment)[ProductsViewModel::class.java]
    }

    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
        toDetailsFragment()
    }

    private fun initialization() {
        initRVProducts()
        initProductAdapter()
        loadData()
        initLiveDataObservers()
    }

    private fun initRVProducts() {
        binding.rvProductList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false)
    }

    private fun toDetailsFragment() {
        val dis = productsAdapter.behaviorSubject.subscribe { item ->
            val action = viewModel.sendArgs(item)
             findNavController().navigate(action)
        }
        cd.add(dis)
    }

    private fun loadData() {
        viewModel.loadData()
    }

    private fun dataLoaded(list: List<PhonesResponse>) {
        productsAdapter.setList(list)
    }

    private fun initLiveDataObservers() {
        viewModel.getLiveDataOnError.observe(viewLifecycleOwner, {
            Log.e("TAG", "getLiveDataOnError: ${it.message}")
        })

        viewModel.getLiveDataOnSuccess.observe(viewLifecycleOwner, {
            dataLoaded(it as List<PhonesResponse>)
        })
    }

    private fun initProductAdapter() {
        productsAdapter = ProductsAdapter(requireContext())
        binding.rvProductList.adapter = productsAdapter
    }

    private fun disposeObservers() {
        cd.dispose()
        cd.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        disposeObservers()
        viewModel.disposeObservers()
    }
}