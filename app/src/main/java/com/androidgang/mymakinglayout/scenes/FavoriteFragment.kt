package com.androidgang.mymakinglayout.scenes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.androidgang.mymakinglayout.adapters.FavoritesAdapter
import com.androidgang.mymakinglayout.databinding.FavoriteFragmentBinding
import com.androidgang.mymakinglayout.models.FavoritesResponse
import com.androidgang.mymakinglayout.viewmodel.FavoriteViewModel
import io.reactivex.disposables.CompositeDisposable

class FavoriteFragment : Fragment() {

    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this)[FavoriteViewModel::class.java]
    }
    private val parentActivity by lazy {
       activity as MainActivity
    }
    private lateinit var favoritesAdapter: FavoritesAdapter
    private val cd = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        hideBottomNavMenu()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
        onClickFavoriteBtn()
    }

    private fun initialization() {
        initRecyclerView()
        loadData()
        liveDataObserve()
    }

    private fun initRecyclerView() {
        favoritesAdapter = FavoritesAdapter(requireContext())
        binding.rvFavoriteList.adapter = favoritesAdapter
    }

    private fun onClickFavoriteBtn() {
        val dis = favoritesAdapter.behaviorFavorite.subscribe { item ->
            if (!item.isFavorite) {
                viewModel.insertItemInFavorite(
                    item,
                    requireView(),
                    resources = resources
                )
            } else {
                viewModel.delItemFromFavorite(item._id)
            }
        }
        cd.add(dis)
    }

    private fun showBottomNavMenu() {
        parentActivity.bottomNavigationView?.visibility = View.VISIBLE
    }

    private fun hideBottomNavMenu() {
        parentActivity.bottomNavigationView?.visibility = View.GONE
    }

    private fun liveDataObserve() {
        viewModel.getLiveDataOnError.observe(viewLifecycleOwner, {
            Log.e("TAG", "getLiveDataOnError(): ${it.message}")
        })

        viewModel.getLiveDataOnSuccess.observe(viewLifecycleOwner, {
            dataLoaded(it as List<FavoritesResponse>)
        })
    }

    private fun dataLoaded(list: List<FavoritesResponse>) {
        favoritesAdapter.setList(list)
    }

    private fun loadData() {
        viewModel.loadData()
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
    }
}