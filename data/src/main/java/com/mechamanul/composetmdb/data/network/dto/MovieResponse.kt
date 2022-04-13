package com.mechamanul.composetmdb.data.network.dto

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("items") val items: List<MovieResponseDetail>? = null,
    @SerializedName("error_message") val errorMessage: String
)

data class MovieResponseDetail(
    @SerializedName("id") val id: String,
    @SerializedName("rank") val rank: String,
    @SerializedName("title") val title: String,
    @SerializedName("full_title") val fullTitle: String,
    @SerializedName("year") val year: String,
    @SerializedName("image") val image:String,
    @SerializedName("crew") val crew: String,
    @SerializedName("imdbRating") val imdbRating: String,
    @SerializedName("imdbRatingCount") val imdbRatingCount: String
)
