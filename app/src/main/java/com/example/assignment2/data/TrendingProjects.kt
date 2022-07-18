package com.example.assignment2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trending_projects_table")
data class TrendingProjects(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "house_id")
    var houseId:Long=0,
    @ColumnInfo(name = "house_name")
    var houseName:String,
    @ColumnInfo(name="house_owner")
    var houseOwner:String,
    @ColumnInfo(name="house_size")
    var houseSize:String,
    @ColumnInfo(name = "house_address")
    var houseAddress:String,
    @ColumnInfo(name="house_rent")
    var houseRent:String,
    @ColumnInfo(name="house_image_url")
    var houseImageUrl:String="",
    @ColumnInfo(name = "is_favourite")
    var isFavourite:Boolean=false
)


