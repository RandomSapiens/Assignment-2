package com.example.assignment2.data
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TrendingProjectsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTrendingProjects(trendingProjects:TrendingProjects)

    @Query("SELECT * FROM trending_projects_table ORDER BY house_id ASC")
    fun readAllData():Flow<List<TrendingProjects>>

    @Query("SELECT * FROM trending_projects_table ORDER BY RANDOM() LIMIT 5")
    fun readFiveShuffleData():Flow<List<TrendingProjects>>



    @Query("UPDATE trending_projects_table SET is_favourite= NOT is_favourite WHERE  house_id=:id")
    suspend fun updateFavourite(id:Long)





}