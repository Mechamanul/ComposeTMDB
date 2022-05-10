package com.mechamanul.composetmdb.remote.models

import com.google.gson.annotations.SerializedName

data class ActorRemote(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val imagePath: String,
    @SerializedName("asCharacter") val asCharacter: String
)