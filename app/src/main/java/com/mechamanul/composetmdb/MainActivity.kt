package com.mechamanul.composetmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mechamanul.composetmdb.popular.composables.PopularMoviesScreen
import com.mechamanul.composetmdb.ui.theme.ComposeTMDBTheme
import com.skydoves.landscapist.glide.LocalGlideRequestBuilder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalGlideRequestBuilder provides viewModel.requestBuilder) {
                ComposeTMDBTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        PopularMoviesScreen()
//                    val navController = rememberNavController()
//                    NavHost(navController = navController, startDestination = )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTMDBTheme {
        Greeting("Android")
    }
}