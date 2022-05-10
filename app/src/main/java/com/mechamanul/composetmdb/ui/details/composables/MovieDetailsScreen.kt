package com.mechamanul.composetmdb.ui.details.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mechamanul.composetmdb.domain.models.Actor
import com.mechamanul.composetmdb.domain.models.MovieDetails
import com.mechamanul.composetmdb.ui.details.MovieDetailsScreenViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieDetailsScreen(viewModel: MovieDetailsScreenViewModel = hiltViewModel()) {


    when (val uiState = viewModel.uiState.collectAsState().value) {
        is MovieDetailsScreenViewModel.DetailsScreenUiState.Empty -> Text(text = "Initial screen state")
        is MovieDetailsScreenViewModel.DetailsScreenUiState.Error -> Text(text = uiState.throwable.message.toString())
        is MovieDetailsScreenViewModel.DetailsScreenUiState.Loading -> Text(text = "Loading movie details...")
        is MovieDetailsScreenViewModel.DetailsScreenUiState.Success -> MovieDetailsScreenContent(
            movieDetails = uiState.movieDetails
        )
    }
}

@Composable
fun MovieDetailsScreenContent(movieDetails: MovieDetails) {

    Surface(modifier = Modifier.padding(4.dp)) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlideImage(
                modifier = Modifier
                    .aspectRatio(0.8f),
                imageModel = movieDetails.imagePath
            )
            Text(
                "${movieDetails.title} (${movieDetails.year})",
                style = MaterialTheme.typography.h4, textAlign = TextAlign.Center
            )
            Row {
                Text(text = "IMDB rating: ")
                RatingCircle(rating = movieDetails.rating)
            }
            Text("Runtime: ${movieDetails.runtimeStr}")
            Text("Directors: ${movieDetails.directors}")
            Text("Writers: ${movieDetails.writers}")
            Text("Company: ${movieDetails.companies}")
            LazyRow {
                items(movieDetails.actorList) { actor: Actor ->
                    ActorCard(actor)
                }

            }
        }

    }
}

@Composable
fun ActorCard(actor: Actor) {
    Log.d("Actor", actor.toString())
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        GlideImage(
            modifier = Modifier
                .aspectRatio(1f),
            imageModel = actor.imagePath
        )
        Text(actor.name)
        Text(text = actor.asCharacter, overflow = TextOverflow.Ellipsis, softWrap = true)

    }
}

@Composable
fun RatingCircle(rating: Float) {
    val circleColor = when {
        rating > 6.5 -> Color(44, 202, 144)
        rating in 3.5..6.5 -> Color(238, 230, 87)
        else -> Color(251, 96, 66)
    }
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(circleColor, shape = CircleShape)
            .layout { measurable, constraints ->
                // Measure the composable
                val placeable = measurable.measure(constraints)

                //get the current max dimension to assign width=height
                val currentHeight = placeable.height
                var heightCircle = currentHeight
                if (placeable.width > heightCircle)
                    heightCircle = placeable.width

                //assign the dimension and the center position
                layout(heightCircle, heightCircle) {
                    // Where the composable gets placed
                    placeable.placeRelative(0, (heightCircle - currentHeight) / 2)
                }
            }) {

        Text(
            text = rating.toString(),
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .padding(4.dp)
                .defaultMinSize(24.dp) //Use a min size for short text.
        )
    }
}

