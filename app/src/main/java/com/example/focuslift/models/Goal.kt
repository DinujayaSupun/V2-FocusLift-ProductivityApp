package com.example.focuslift.models

import java.util.Date

data class Goal(
    val id: String = System.currentTimeMillis().toString(),
    val title: String,
    val description: String = "",
    val target: Int = 100,
    val currentProgress: Int = 0,
    val deadline: Date? = null,
    val status: GoalStatus = GoalStatus.IN_PROGRESS,
    val createdAt: Date = Date()
)

enum class GoalStatus {
    NOT_STARTED,
    IN_PROGRESS,
    COMPLETED,
    OVERDUE
}
