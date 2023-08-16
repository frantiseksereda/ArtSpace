package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

val image1 = Artwork()
val image2 = Artwork()
val image3 = Artwork()
val imagesToShow = mutableListOf<Artwork>(image1, image2, image3)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ShowArt()
                }
            }
        }
    }
}

class Artwork {
    var imageId: Int = R.drawable.house1
    var imageDescription: String = "Something"
    var title: String = "Some title"
    var artist: String = "Some artist"
    var year: Int = 0
}

@Composable
fun ShowArt() {
    image1.imageId = R.drawable.house1
    image1.imageDescription = stringResource(id = R.string.image1Description)
    image1.artist = stringResource(id = R.string.image1Artist)
    image1.title = stringResource(id = R.string.image1Description)
    image1.year = integerResource(id = R.integer.image1Year)

    image2.imageId = R.drawable.house2
    image2.imageDescription = stringResource(id = R.string.image2Description)
    image2.artist = stringResource(id = R.string.image2Artist)
    image2.title = stringResource(id = R.string.image2Description)
    image2.year = integerResource(id = R.integer.image2Year)

    image3.imageId = R.drawable.house3
    image3.imageDescription = stringResource(id = R.string.image3Description)
    image3.artist = stringResource(id = R.string.image3Artist)
    image3.title = stringResource(id = R.string.image3Description)
    image3.year = integerResource(id = R.integer.image3Year)

    var currentImage by remember {mutableStateOf(0)}

    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxHeight(0.75F)

        ) {
            Image(
                painter = painterResource(id = imagesToShow[currentImage].imageId),
                contentDescription = imagesToShow[currentImage].imageDescription,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        ) {
            Row() {
                Text(
                    text = imagesToShow[currentImage].title,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
            Row() {
                Text(
                    text = "${imagesToShow[currentImage].artist} (${imagesToShow[currentImage].year})",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Button(
                    onClick = { if (currentImage == 0) {
                        currentImage = 2
                    } else currentImage -= 1 },
                    modifier = Modifier.fillMaxWidth(0.5F)
                ) {
                    Text(text = "Previous")
                }
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Button(
                    onClick = {
                        if (currentImage == 2) {
                            currentImage = 0
                        } else currentImage += 1
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Next")
                }
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ShowArt()
    }
}