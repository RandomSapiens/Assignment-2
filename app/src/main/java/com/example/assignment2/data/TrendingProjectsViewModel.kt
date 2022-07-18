package com.example.assignment2.data

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingProjectsViewModel @Inject constructor(application: Application):ViewModel(){
    var readAllData: Flow<List<TrendingProjects>>
    var readFiveShuffleData:Flow<List<TrendingProjects>>

    private val repository: TrendingProjectsRepository

    init{
        val trendingProjectsDao=TrendingProjectsDatabase.getDatabase(application).trendingProjectsDao()
        repository= TrendingProjectsRepository(trendingProjectsDao)
        readAllData=repository.readAllData
        readFiveShuffleData=repository.readFiveShuffleData
    }

    fun addTrendingProjects(trendingProjects: TrendingProjects){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTrendingProjects(trendingProjects)
        }
    }

    fun updateFavourite(id:Long){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateFavourite(id)
        }
    }




}