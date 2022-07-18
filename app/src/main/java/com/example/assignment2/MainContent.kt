package com.example.assignment2

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment2.data.TrendingProjectsViewModel


@Composable
fun MainContent(mainViewModel: TrendingProjectsViewModel?=null){
    val navController= rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(navController =navController ,
            startDestination ="home")
        {

            composable(route = "home") {
                ProjectsRowView(navController,mainViewModel)
            }
            composable(route = "task") {
                ProjectsColumnView(mainViewModel)
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewMainContent(){
    MainContent(null)
}

