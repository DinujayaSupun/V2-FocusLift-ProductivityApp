package com.example.focuslift.models

import java.util.Date

data class FavoriteItem(
    val id: String,
    val title: String,
    val content: String,
    val type: FavoriteType,
    val timestamp: Date = Date(),
    val isFavorite: Boolean = true
)

enum class FavoriteType {
    QUOTE,
    TASK,
    TIP,
    GOAL
}
