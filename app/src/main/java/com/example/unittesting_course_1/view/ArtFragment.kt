package com.example.unittesting_course_1.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unittesting_course_1.R
import com.example.unittesting_course_1.databinding.FragmentArtsBinding
import com.example.unittesting_course_1.view.adapter.ArtRecyclerAdapter
import com.example.unittesting_course_1.viewmodel.ArtViewModel
import javax.inject.Inject

class ArtFragment @Inject constructor(val recyclerAdapter: ArtRecyclerAdapter) :
      Fragment(R.layout.fragment_arts) {

    private var fragmentBinding: FragmentArtsBinding?=null
    private lateinit var viewModel:ArtViewModel
    private val swipeCallBack = object: ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val selectedArt = recyclerAdapter.artList[viewHolder.layoutPosition]
            viewModel.deleteArt(selectedArt)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ArtViewModel::class.java]

        val binding = FragmentArtsBinding.bind(view)
        fragmentBinding = binding

        subScribeToObserver()
        binding.recyclerArt.adapter = recyclerAdapter
        binding.recyclerArt.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.recyclerArt)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        }
    }

    private fun subScribeToObserver(){
        viewModel.artList.observe(viewLifecycleOwner, Observer{
            recyclerAdapter.artList = it
        })
    }
}