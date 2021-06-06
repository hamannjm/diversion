package com.hamann.inpursuitofdiversion.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class GameSearchPagination(
    @PrimaryKey
    val gameSearchString: String,
    val currentOffset: Int,
    val totalResults: Int,
    val resultsOnPage: Int
) {
    @Ignore
    val nextOffset: Int? = if (currentOffset + resultsOnPage >= totalResults) {
        null
    } else {
        currentOffset + resultsOnPage
    }
}