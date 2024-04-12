package com.example.unittesting_course_1.remote.repo

import androidx.lifecycle.LiveData
import com.example.unittesting_course_1.remote.ApiService
import com.example.unittesting_course_1.remote.ImageApiResponseModel
import com.example.unittesting_course_1.roomdb.ArtDao
import com.example.unittesting_course_1.roomdb.ArtEntity
import com.example.unittesting_course_1.utils.Resource
import javax.inject.Inject


class ArtRepoRepository @Inject constructor(
    private val artDao: ArtDao,
    private val apiService: ApiService
) : ArtRepoInterface {
    override suspend fun insertArt(artEntity: ArtEntity) {
        artDao.insertArt(artEntity)
    }

    override suspend fun deleteArt(artEntity: ArtEntity) {
        artDao.deleteArt(artEntity)
    }

    override fun getArt(): LiveData<List<ArtEntity>> {
        return artDao.getAllData()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageApiResponseModel> {
        return try {
            val response = apiService.imageSerch(imageString)
            if (response.body() != null && response.isSuccessful) {
                response.body().let {
                    return@let Resource.success(it)
                }
            } else {
                Resource.error("Error", null)
            }
        } catch (e: Exception) {
            Resource.error("No data found", null)
        }
    }


}