package com.mechamanul.composetmdb.popular.composables


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.mechamanul.composetmdb.domain.models.Movie
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun MovieListItem(movie: Movie, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        color = MaterialTheme.colors.background,
        elevation = 8.dp
    ) {
        ConstraintLayout {
            val (image, header, crew) = createRefs()
            GlideImage(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    },
                imageModel = movie.poster,
            )
            Text(modifier = Modifier
                .constrainAs(header) {
                    top.linkTo(parent.top)
                    start.linkTo(
                        image.end
                    )
                    bottom.linkTo(crew.top)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(start = 4.dp), text = "${movie.name} (${movie.year})")
            Text(modifier = Modifier
                .constrainAs(crew) {
                    top.linkTo(header.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(start = 4.dp), text = movie.crew, fontSize = 12.sp)
        }
    }
}

@Preview
@Composable
fun PreviewMovieListItem() {
    MovieListItem(
        Movie(
            id = "tt123",
            name = "Shrek",
            year = 2000,
            crew = "Shrek,Fiona",
            poster = "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX128_CR0,3,128,176_AL_.jpg"
        )
    )
//    }
}