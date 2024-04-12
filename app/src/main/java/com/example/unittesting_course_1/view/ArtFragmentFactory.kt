package com.example.unittesting_course_1.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.example.unittesting_course_1.view.adapter.ArtRecyclerAdapter
import com.example.unittesting_course_1.view.adapter.ImageRecyclerAdapter
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
    private val artRecyclerAdapter: ArtRecyclerAdapter,
    private val imageRecyclerAdapter: ImageRecyclerAdapter,
    private val glide:RequestManager):FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            ArtFragment::class.java.name -> ArtFragment(artRecyclerAdapter)
            ImageApiFragment::class.java.name -> ImageApiFragment(imageRecyclerAdapter)
            ArtDetailsFragment::class.java.name -> ArtDetailsFragment(glide)
            else ->  return super.instantiate(classLoader, className)
        }


    }
}