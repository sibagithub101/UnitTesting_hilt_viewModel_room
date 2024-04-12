package com.example.unittesting_course_1.remote

import com.example.unittesting_course_1.utils.Util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/")
    suspend fun imageSerch(
                          @Query("q") searchQuery:String,
                           @Query("key")apiKey:String = API_KEY
                           ) : Response<ImageApiResponseModel>
}