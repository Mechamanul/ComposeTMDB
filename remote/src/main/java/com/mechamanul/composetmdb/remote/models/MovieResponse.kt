package com.mechamanul.composetmdb.remote.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("items") val items: List<MovieResponseDetail>? = null,
    @SerializedName("error_message") val errorMessage: String
)

