package com.example.quickeng.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sentences")
data class SentenceEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tag: String,
    val en: String,
    val ko: String
)