package com.mechamanul.composetmdb.domain.models

import java.time.LocalDate

data class MovieDetails(
    val id: String,
    val title: String,
    val year: Int,
    val releaseDate: LocalDate,
    val runtimeStr: String?,
    val directors: String,
    val writers: String,
    val countries: String?,
    val companies: String,
    val actorList: List<Actor>,
    val genres: String,
    val rating: Float,
    val tagline: String?,
    val imagePath: String
)