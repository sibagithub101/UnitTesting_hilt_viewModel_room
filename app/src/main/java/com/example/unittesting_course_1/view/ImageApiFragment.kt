package com.example.unittesting_course_1.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.RequestManager
import com.example.unittesting_course_1.R
import com.example.unittesting_course_1.view.adapter.ImageRecyclerAdapter
import javax.inject.Inject

class ImageApiFragment @Inject constructor( adapter: ImageRecyclerAdapter) :Fragment(R.layout.fragment_image_api) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}