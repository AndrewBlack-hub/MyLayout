package com.androidgang.mymakinglayout.scenes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.adapters.ViewPagerAdapter
import com.androidgang.mymakinglayout.databinding.FragmentDetailsBinding
import com.androidgang.mymakinglayout.viewmodel.DetailsViewModel


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private var viewPagerAdapter: ViewPagerAdapter? = null
    private var viewPager: ViewPager2? = null

    private lateinit var parentActivity: MainActivity

    private val detailsViewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewPager = binding.vpDetailsList

        parentActivity = activity as MainActivity

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAdapter = ViewPagerAdapter(detailsViewModel.detailsImageList)
        binding.vpDetailsList.adapter = viewPagerAdapter
        settingViewPager2()
        hideBottomNavigation(parentActivity)
    }

    private fun settingViewPager2() {
        viewPager?.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r: Float = 1 - kotlin.math.abs(position)
            page.scaleY = 0.85F + r * 0.15f
        }
        viewPager?.setPageTransformer(compositePageTransformer)
        onClickBackBtn()
        onClickDoneBtn()
    }

    private fun onClickBackBtn() {
        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_homeStoreFragment)
            showBottomNavigation(parentActivity)
        }
    }

    private fun onClickDoneBtn() {
        binding.ivBagBtn.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_homeStoreFragment)
            showBottomNavigation(parentActivity)
        }
    }

    private fun hideBottomNavigation(mainActivity: MainActivity) {
        mainActivity.bottomNavigationView?.visibility = View.GONE
    }

    private fun showBottomNavigation(mainActivity: MainActivity) {
        mainActivity.bottomNavigationView?.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        showBottomNavigation(parentActivity)
    }
}