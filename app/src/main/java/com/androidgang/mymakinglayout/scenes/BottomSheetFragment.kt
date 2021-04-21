package com.androidgang.mymakinglayout.scenes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment: BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickCloseFilter()
        onClickDoneFilter()
    }

    private fun onClickDoneFilter() {
        binding.tvDoneFilter.setOnClickListener {
            findNavController().navigate(R.id.action_bottomSheetFragment_to_homeStoreFragment)
        }
    }

    private fun onClickCloseFilter() {
        binding.ivCloseFilter.setOnClickListener {
            findNavController().navigate(R.id.action_bottomSheetFragment_to_homeStoreFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}