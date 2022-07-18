package com.example.assignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.assignment2.data.TrendingProjects
import com.example.assignment2.data.TrendingProjectsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel:TrendingProjectsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val projects= initialData()
//        for(project in projects)mainViewModel.addTrendingProjects(project)
        setContent {
            MainContent(mainViewModel)
        }

    }
}



fun initialData(): MutableList<TrendingProjects> {
    val projects= mutableListOf<TrendingProjects>()

    for (i in 1..15) {
        val p=TrendingProjects(houseName = "Shanti-Niketan - $i",
            houseOwner = "mktd by Elara technology group of india",
            houseAddress ="Echelon Square Sector - 32",
            houseSize = "2,3,4 BHK",
            houseRent = "₹ 88.73L - 1.84 Cr"
        )
        projects.add(p)
    }
    return projects
}

