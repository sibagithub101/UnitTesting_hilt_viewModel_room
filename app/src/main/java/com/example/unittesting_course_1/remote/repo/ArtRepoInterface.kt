package com.example.unittesting_course_1.remote.repo

import androidx.lifecycle.LiveData
import com.example.unittesting_course_1.remote.ImageApiResponseModel
import com.example.unittesting_course_1.roomdb.ArtEntity
import com.example.unittesting_course_1.utils.Resource

interface ArtRepoInterface {
    suspend fun insertArt(artEntity: ArtEntity)
    suspend fun deleteArt(artEntity: ArtEntity)
     fun getArt():LiveData<List<ArtEntity>>
     suspend fun searchImage(imageString:String): Resource<ImageApiResponseModel>



}