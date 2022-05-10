package com.mechamanul.composetmdb.remote.models

import com.google.gson.annotations.SerializedName

data class MovieDetailsRemote(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("image") val imagePath: String,
    @SerializedName("year") val year: String,
    @SerializedName("releaseDate") val releaseDate: String,
    @SerializedName("runtimeStr") val runtimeStr: String,
    @SerializedName("directors") val directors: String,
    @SerializedName("writers") val writers: String,
    @SerializedName("countries") val countries: String,
    @SerializedName("companies") val companies: String,
    @SerializedName("actorList") val actorList: List<ActorRemote>,
    @SerializedName("genres") val genres: String,
    @SerializedName("imDbRating") val rating: String,
    @SerializedName("tagline") val tagLine: String?,
    @SerializedName("errorMessage") val errorMessage: String?
)
