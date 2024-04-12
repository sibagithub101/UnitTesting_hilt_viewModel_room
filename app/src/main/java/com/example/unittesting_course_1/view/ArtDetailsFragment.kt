package com.example.unittesting_course_1.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.unittesting_course_1.R
import com.example.unittesting_course_1.databinding.FragmentArtDetailsBinding
import javax.inject.Inject

class ArtDetailsFragment @Inject constructor(val glide: RequestManager) :Fragment(R.layout.fragment_art_details) {
    private var fragmentBinding:FragmentArtDetailsBinding?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentArtDetailsBinding.bind(view)
        fragmentBinding = binding

        binding.imageView.setOnClickListener {
            findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment())
        }

        /**
         * Below is is onBack
         */

        val callBack = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }

    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }
}