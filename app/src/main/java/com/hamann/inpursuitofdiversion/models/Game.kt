package com.hamann.inpursuitofdiversion.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Game(
    @PrimaryKey
    @SerializedName("guid")
    val guid: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("deck")
    val shortDescription: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("original_release_date")
    val releaseDate: Date?
) {
}

@Entity
data class Image(
    val gameId: Int
)