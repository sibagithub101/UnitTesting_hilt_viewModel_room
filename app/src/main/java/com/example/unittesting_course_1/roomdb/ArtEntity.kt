package com.example.unittesting_course_1.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ArtTable")
data class ArtEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    var name:String,
    var artistName:String,
    var year:Int,
    var imageUrl:String
)