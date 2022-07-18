package com.example.assignment2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment2.data.TrendingProjects
import com.example.assignment2.data.TrendingProjectsViewModel

@Composable
fun ProjectsColumnView(mainViewModel: TrendingProjectsViewModel?=null){
    var projects: List<TrendingProjects> by remember { mutableStateOf(emptyList()) }

    LaunchedEffect(key1 = Unit){
        mainViewModel?.readAllData?.collect{
            projects=it
        }
    }

    Column {
        Text(
            text = "Trending Projects" ,
            fontSize =40.sp,
            modifier = Modifier.padding(10.dp)
        )
        LazyColumn{
            items(projects){ trendingProject->
                ProjectsCard(mainViewModel ,trendingProject)
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun PreviewTask(){
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        ProjectsColumnView()
    }
}