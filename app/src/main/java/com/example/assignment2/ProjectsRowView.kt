package com.example.assignment2

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale


import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment2.data.TrendingProjects
import com.example.assignment2.data.TrendingProjectsViewModel

@Composable
fun ProjectsRowView(
    navController: NavController? = null,
    mainViewModel: TrendingProjectsViewModel? = null
) {

    var projects: List<TrendingProjects> by remember {
        mutableStateOf(emptyList())
    }

//    var isFavList:List<Boolean> by

    LaunchedEffect(key1 = Unit) {
        mainViewModel?.readFiveShuffleData?.collect {
            projects = it
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Trending Projects",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = {
                        navController?.navigate("task")
                    },
                ) {
                    Text("See All")
                }

            }
            Text(
                text = "Most Trending Projects in Delhi City",
                fontSize = 20.sp,
                fontWeight = FontWeight.Light
            )
        }
        ProjectsList(mainViewModel,trendingProjects = projects)
    }

}


@Composable
fun ProjectsList(mainViewModel: TrendingProjectsViewModel?,trendingProjects: List<TrendingProjects>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        items(trendingProjects) { trendingProject ->
            ProjectsCard(mainViewModel, trendingProject = trendingProject)
        }
    }
}


@Composable
fun ProjectsCard(mainViewModel: TrendingProjectsViewModel?,trendingProject: TrendingProjects) {

    var isFav by remember { mutableStateOf(trendingProject.isFavourite) }

    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_home_24),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.4f),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = trendingProject.houseName,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,

                )
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                    contentDescription ="",
                    colorFilter = if (isFav) ColorFilter.tint(color = Color.Red) else ColorFilter.tint(color = Color.Gray) ,
                    modifier = Modifier.clickable {
                        isFav=!isFav
                        mainViewModel?.updateFavourite(trendingProject.houseId)
                    }
                )
            }
            Text(
                text = trendingProject.houseOwner,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraLight
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = trendingProject.houseSize,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraLight
                )
                Text(
                    text = ". ${trendingProject.houseAddress}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraLight,
                    maxLines = 1
                )
            }


            Text(
                text = trendingProject.houseRent,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
//        ProjectsCard(trendingProject = initialData()[0])
        ProjectsList(null,trendingProjects = initialData())
//        ProjectsRowView()
    }
}