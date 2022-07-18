package com.example.assignment2.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TrendingProjectsRepository @Inject constructor(private val trendingProjectsDao:TrendingProjectsDao) {
    val readAllData: Flow<List<TrendingProjects>> = trendingProjectsDao.readAllData()
    val readFiveShuffleData:Flow<List<TrendingProjects>> = readAllData.map{
        it.shuffled().take(5)
    }


    suspend fun addTrendingProjects(trendingProjects: TrendingProjects){
        trendingProjectsDao.addTrendingProjects(trendingProjects)
    }

    suspend fun updateFavourite(id:Long){
        trendingProjectsDao.updateFavourite(id)
    }
}