package com.mechamanul.composetmdb.remote.models

import com.google.gson.annotations.SerializedName

data class PopularMovies(
    @SerializedName("items") val items: List<PopularMoviesItems>? = null,
    @SerializedName("error_message") val errorMessage: String
)

