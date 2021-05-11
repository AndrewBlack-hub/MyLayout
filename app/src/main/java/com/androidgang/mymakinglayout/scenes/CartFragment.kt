package com.androidgang.mymakinglayout.scenes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.adapters.CartAdapter
import com.androidgang.mymakinglayout.databinding.CartFragmentBinding
import com.androidgang.mymakinglayout.models.CartResponse
import com.androidgang.mymakinglayout.viewmodel.CartViewModel
import io.reactivex.disposables.CompositeDisposable

class CartFragment : Fragment() {

    private var _binding: CartFragmentBinding? = null
    private val binding get() = _binding!!

    private val cd = CompositeDisposable()
    private val parentActivity by lazy {
        activity as MainActivity
    }

    private val cartViewModel by lazy {
        ViewModelProvider(this)[CartViewModel::class.java]
    }

    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CartFragmentBinding.inflate(inflater, container, false)
        hideBottomNavMenu()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
        onClickCheckoutBtn()
        onClickBackBtn()
        onClickTrashIcon()
        onClickPlusIcon()
        onClickMinusIcon()
    }

    private fun onClickTrashIcon() {
        val dis = cartAdapter.behaviorTrash.subscribe { item ->
            cartViewModel.deleteItem(item._id)
            loadData()
            cartViewModel.getLiveDataOnSuccess.observe(viewLifecycleOwner, {
                dataLoaded(it as List<CartResponse>)
            })
        }
        cd.add(dis)
    }

    private fun onClickPlusIcon() {
        val dis = cartAdapter.behaviorPlus.subscribe { item ->
            val count = item.counter + 1
            cartViewModel.updateItem(item, count)
            loadData()
            cartViewModel.getLiveDataOnSuccess.observe(viewLifecycleOwner, {
                dataLoaded(it as List<CartResponse>)
            })
        }
        cd.add(dis)
    }

    private fun onClickMinusIcon() {
        val dis = cartAdapter.behaviorMinus.subscribe { item ->
            val count = item.counter - 1
            cartViewModel.updateItem(item, count)
            loadData()
            cartViewModel.getLiveDataOnSuccess.observe(viewLifecycleOwner, {
                dataLoaded(it as List<CartResponse>)
            })
        }
        cd.add(dis)
    }

    private fun initialization() {
        initRecyclerView()
        loadData()
        liveDataObserve()
    }

    private fun initRecyclerView() {
        cartAdapter = CartAdapter(requireContext())
        binding.rvCartList.adapter = cartAdapter
    }

    private fun liveDataObserve() {
        cartViewModel.getLiveDataOnError.observe(viewLifecycleOwner, {
            Log.e("TAG", "getLiveDataOnError() ${it.message}")
        })

        cartViewModel.getLiveDataOnSuccess.observe(viewLifecycleOwner, {
            dataLoaded(it as List<CartResponse>)
        })
    }

    private fun dataLoaded(list: List<CartResponse>) {
        cartAdapter.setList(list)
    }

    private fun loadData() {
        cartViewModel.loadData()
        Log.e("TAG", "getCart() on CartFragment()")
    }

    private fun hideBottomNavMenu() {
        parentActivity.bottomNavigationView?.visibility = View.GONE
    }

    private fun showBottomNavMenu() {
        parentActivity.bottomNavigationView?.visibility = View.VISIBLE
    }

    private fun onClickBackBtn() {
        binding.ivCartBtnBack.setOnClickListener {
            findNavController().navigate(R.id.homeStoreFragment)
            showBottomNavMenu()
        }
    }

    private fun onClickCheckoutBtn() {
        binding.tvBtnCheckout.setOnClickListener {
            findNavController().navigate(R.id.homeStoreFragment)
            showBottomNavMenu()
        }
    }

    private fun disposeObservers() {
        cd.dispose()
        cd.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        showBottomNavMenu()
        disposeObservers()
        cartViewModel.disposeObservers()
    }
}