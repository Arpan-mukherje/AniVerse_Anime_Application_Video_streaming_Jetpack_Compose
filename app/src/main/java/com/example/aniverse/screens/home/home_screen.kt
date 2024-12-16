package com.example.aniverse.screens.home

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.CardDefaults.shape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.aniverse.R
import com.example.aniverse.model.TopAnimeResponseModel
import com.example.aniverse.ui.theme.BackGroundColor
import com.example.aniverse.ui.theme.GradientColor2
import com.example.aniverse.viewmodel.AnimeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ResourceAsColor", "SuspiciousIndentation")

@Composable
fun HomeScreen(navController: NavController, viewModel: AnimeViewModel = hiltViewModel()) {
    val topAnime by viewModel.topAnime.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchTopAnime()
    }
    Scaffold(
        containerColor = BackGroundColor,
        topBar = {
            TopAppBar(

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = GradientColor2,
                    titleContentColor = Color.White

                ),
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            tint = Color.White,
                            contentDescription = "Localized description"
                        )
                    }
                },
                title = {
                    Text(
                        "Aniverse",
                        modifier = Modifier,
                        style = TextStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 26.sp,
                        )
                    )
                },

                )
        },
        content = { contentPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                when {
                    errorMessage != null -> {
                        Text(
                            text = "Error: $errorMessage",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    topAnime.isEmpty() &&!isLoading-> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No Anime available. Try again",
                                color = Color.Gray,
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    else -> {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize()
                        ) {
                            items(topAnime.size) { index ->
                                val anime = topAnime[index]


                                    GridBox(anime,{
                                        // Handle the click here
                                      //  val encodedUrl = Uri.encode(anime.trailer.embed_url)
                                        navController.navigate("all-episode/${anime.mal_id}/${anime.trailer.youtube_id}/$index")
                                    })

                                if (index == topAnime.size - 2 && !isLoading) {
                                    viewModel.fetchTopAnime()
                                }
                            }
                        }

                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    )
}


@Composable
fun GridBox(singledata: TopAnimeResponseModel.Data,onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.5f).clickable { onClick() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
    ) {
        // Box for the image
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(BackGroundColor)
            // .padding(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(singledata.images.jpg.image_url)
                    .crossfade(true)
                    .build(),
                contentDescription = "Network Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))

            )
        }
        Text(
            text = singledata.title,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.W600,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun preview1() {
//    HomeScreen(
//        navController = TODO(),
//        viewModel = TODO()
//    )
//
//}