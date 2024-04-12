package com.example.unittesting_course_1.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArtEntity::class], version = 1, exportSchema = false)
abstract class LocalDb:RoomDatabase() {
    abstract fun artDao():ArtDao
}