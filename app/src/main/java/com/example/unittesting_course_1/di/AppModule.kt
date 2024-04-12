package com.example.unittesting_course_1.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.unittesting_course_1.R
import com.example.unittesting_course_1.remote.ApiService
import com.example.unittesting_course_1.remote.repo.ArtRepoInterface
import com.example.unittesting_course_1.remote.repo.ArtRepoRepository
import com.example.unittesting_course_1.roomdb.ArtDao
import com.example.unittesting_course_1.roomdb.LocalDb
import com.example.unittesting_course_1.utils.Util.BASE_API
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Module responsible for providing instances of the local database components using Hilt.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides an instance of the NewsDatabase using the application context.
     *
     * @param context The application context.
     * @return An instance of the NewsDatabase.
     */

    @Provides
    @Singleton
    fun providesUserDatabase(@ApplicationContext context: Context): LocalDb {
        return Room.databaseBuilder(context, LocalDb::class.java, "RoomDatabase")
            .fallbackToDestructiveMigration().allowMainThreadQueries()
            .build()
    }

    /**
     * Provides an instance of the NewsDao using the provided NewsDatabase instance.
     *
     * @param database The NewsDatabase instance.
     * @return An instance of the NewsDao.
     */

    @Provides
    @Singleton
    fun providesNewsDao(database: LocalDb): ArtDao {
        return database.artDao()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRepo(dao:ArtDao,apiService: ApiService) = ArtRepoRepository(dao,apiService) as ArtRepoInterface


    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(RequestOptions().placeholder(R.drawable.baseline_animation_24).error(R.drawable.baseline_error_outline_24))
}
