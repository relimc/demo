package com.example.CriminalIntent

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// UUID 用于生成一个唯一的标识
@Entity
data class Crime(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var title: String = "",
    var date: Date = Date(),
    var isSolved: Boolean = false,
    var suspect: String = ""
)