package com.mechamanul.composetmdb.domain.models

import java.time.LocalDate

data class Movie(val name:String,val releaseDate:LocalDate,val description:String,val poster:Int)
