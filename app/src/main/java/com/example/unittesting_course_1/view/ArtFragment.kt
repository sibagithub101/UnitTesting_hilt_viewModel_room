package com.example.unittesting_course_1.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.unittesting_course_1.R
import com.example.unittesting_course_1.databinding.FragmentArtsBinding

class ArtFragment:Fragment(R.layout.fragment_arts) {

    private var fragmentBinding: FragmentArtsBinding?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentArtsBinding.bind(view)
        fragmentBinding = binding

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        }


    }
}