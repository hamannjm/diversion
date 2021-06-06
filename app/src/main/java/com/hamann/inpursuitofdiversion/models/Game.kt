package com.hamann.inpursuitofdiversion.models

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(
    tableName = "games"
)
data class Game(
    @PrimaryKey
    @SerializedName("guid")
    val guid: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("deck")
    val shortDescription: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("original_release_date")
    val releaseDate: Date?,

    @Embedded
    @SerializedName("image")
    val image: Image
) {
    @ColumnInfo(
        index = true
    )
    var associatedSearchString: String? = null

    @Entity(
        tableName = "image"
    )
    data class Image(
        @SerializedName("thumb_url")
        val thumbUrl: String,
        @SerializedName("original_url")
        val originalUrl: String
    )
}

