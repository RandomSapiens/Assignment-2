package com.example.assignment2

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale


import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment2.data.TrendingProjects
import com.example.assignment2.data.TrendingProjectsViewModel
import kotlinx.coroutines.flow.firstOrNull

@Composable
fun ProjectsRowView(
    navController: NavController? = null,
    mainViewModel: TrendingProjectsViewModel? = null
) {
    Log.d("Uncer10nity","Project row view recomposed")
    var projects: List<TrendingProjects> by remember {
        mutableStateOf(emptyList())
    }



    LaunchedEffect(key1 = Unit) {
        Log.d("Uncer10nity","I am Running again")
        val randomItems=mainViewModel?.readFiveShuffleData?.firstOrNull()?: emptyList<TrendingProjects>()
        projects=randomItems
    }


    for(project in projects)Log.d("Uncer10nity","${project.houseName}")

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
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            items(projects) { trendingProject ->
//                ProjectsCard(trendingProject = trendingProject){
//                    trendingProject.isFavourite=!trendingProject.isFavourite
//                    mainViewModel?.updateFavourite(trendingProject.houseId)
//                }
                ProjectsCard(trendingProject = trendingProject,mainViewModel)
            }
        }
    }

}

@Composable
fun ProjectsCard(trendingProject: TrendingProjects,mainViewModel: TrendingProjectsViewModel?) {

    var isFav by remember { mutableStateOf(trendingProject.isFavourite) }

    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(12.dp)
            .width(300.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.home),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.4f),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = trendingProject.houseName,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,

                )

                IconButton(onClick = {
                    isFav=!isFav
                    mainViewModel?.updateFavourite(trendingProject.houseId)
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                        contentDescription ="",
                        colorFilter = if (isFav) ColorFilter.tint(color = Color.Red) else ColorFilter.tint(color = Color.Gray),
                    )
                }
            }
            Text(
                text = trendingProject.houseOwner,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraLight,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis

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
//        ProjectsList(null,trendingProjects = initialData())
        ProjectsRowView()
    }
}