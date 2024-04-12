package com.example.unittesting_course_1.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArtDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt(artEntity: ArtEntity)

    @Delete
    suspend fun deleteArt(artEntity: ArtEntity)

    @Query("SELECT * FROM ArtTable")
    fun getAllData():LiveData<List<ArtEntity>>
}