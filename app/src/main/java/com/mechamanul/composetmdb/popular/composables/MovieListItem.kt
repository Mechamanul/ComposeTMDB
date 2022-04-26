package com.mechamanul.composetmdb.popular.composables

import android.view.Surface
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mechamanul.composetmdb.domain.models.Movie

@Composable
fun MovieListItem(movie: Movie) {

    Card {
        Row {
            MoviePoster(pathToPoster = movie.poster)
            MovieInfo(name = movie.name, year = movie.year, crew = movie.crew)
        }
    }
}

@Composable
fun MoviePoster(pathToPoster: String) {

}

@Composable
fun MovieInfo(name: String, year: Int, crew: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "$name (${year})")
        Text(text = crew)
    }
}

@Preview
@Composable
fun PreviewMovieInfo(){
    MovieInfo(name = "Shrek", year = 2000, crew = "Shrek,Fiona")
}