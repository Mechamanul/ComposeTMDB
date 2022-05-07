package com.mechamanul.composetmdb.domain.models

data class Movie(
    val id: String,
    val name: String,
    val year: Int,
    val crew: String,
    val poster: String
)
