package com.example.unittesting_course_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.RequestManager
import com.example.unittesting_course_1.view.ArtFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_main)
    }
}