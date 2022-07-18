package com.example.assignment2.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrendingProjectsRepository @Inject constructor(private val trendingProjectsDao:TrendingProjectsDao) {
    val readAllData: Flow<List<TrendingProjects>> = trendingProjectsDao.readAllData()
    val readFiveShuffleData:Flow<List<TrendingProjects>> = trendingProjectsDao.readFiveShuffleData()


    suspend fun addTrendingProjects(trendingProjects: TrendingProjects){
        trendingProjectsDao.addTrendingProjects(trendingProjects)
    }

    suspend fun updateFavourite(id:Long){
        trendingProjectsDao.updateFavourite(id)
    }
}