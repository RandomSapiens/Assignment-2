package com.example.assignment2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
//                ProjectsCard(trendingProject){
//                    trendingProject.isFavourite=!trendingProject.isFavourite
//                    mainViewModel?.updateFavourite(trendingProject.houseId)
//                }
                VerticalProjectCard(trendingProject = trendingProject,mainViewModel)
            }
        }

    }

}

@Composable
fun VerticalProjectCard(trendingProject: TrendingProjects,mainViewModel: TrendingProjectsViewModel?=null){
    var isFav by remember { mutableStateOf(trendingProject.isFavourite) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight().padding(8.dp),
        elevation = 8.dp,

    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.home),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically



                ){
                   Column() {
                       Text(
                           text = trendingProject.houseRent,
                           fontSize = 30.sp,
                           fontWeight = FontWeight.Bold
                       )
                       Text(
                           text = trendingProject.houseName,
                           fontSize = 25.sp,
                           fontWeight = FontWeight.Bold
                       )
                   }
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
                    text = trendingProject.houseAddress,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraLight,
                    maxLines = 1
                )

                Divider(thickness = 1.dp, modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp)
                    .fillMaxWidth())

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Column {
                        Row(horizontalArrangement = Arrangement.SpaceBetween){
                            Image(
                                painter = painterResource(id = R.drawable.img_1),
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp)
                                    .padding(1.dp)
                                    .clip(CircleShape),
                                contentDescription ="",
                            )
                            Text(
                                text = trendingProject.houseOwner,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                maxLines=1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.fillMaxWidth(.3f)
                            )
                        }

                        Text(
                            text = "Seller",
                            fontWeight = FontWeight.ExtraLight
                        )
                    }
                    
                    Button(onClick = {}) {
                        Text("View Phone", modifier = Modifier
                            .width(85.dp)
                            .height(25.dp))
                    }
                    Button(onClick = {}) {
                        Image(painter = painterResource(id = R.drawable.img), contentDescription = "",
                            modifier = Modifier
                                .width(15.dp)
                                .height(25.dp)
                        )
                    }
                    Button(onClick = {}) {
                        Image(painter = painterResource(id = R.drawable.ic_baseline_call_24), contentDescription = "",
                            modifier = Modifier
                                .width(15.dp)
                                .height(25.dp),
                            colorFilter = ColorFilter.tint(color = Color.White)
                        )
                    }

                }
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
//        VerticalProjectCard(initialData()[0])
    }

}