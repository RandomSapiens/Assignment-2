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
        val projects= initialData()
        for(project in projects)mainViewModel.addTrendingProjects(project)
        setContent {
            MainContent(mainViewModel)
        }

    }
}



fun initialData(): MutableList<TrendingProjects> {
    val projects= mutableListOf<TrendingProjects>()
    val p1 =TrendingProjects(houseName = "Shanti-Niketan",
        houseOwner = "mktd by Elara technology group of india",
        houseAddress ="Jhanda Chawk Saguna More",
        houseSize = "2,3,4 BHK",
        houseRent = "₹ 88.73L - 1.84 Cr"
    )

    val p2 = TrendingProjects(houseName = "Baba Ki Haweli",
        houseOwner = "mktd by Elara technology group of india",
        houseAddress ="Echelon Square Sector -32",
        houseSize = "2,3,4 BHK",
        houseRent = "₹ 88.73L - 1 Cr"
    )

    for (i in 1..15) {
        if (i % 2 == 1)projects.add(p1)
        else projects.add(p2)
    }
    return projects
}

