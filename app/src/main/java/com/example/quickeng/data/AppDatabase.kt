package com.example.quickeng.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quickeng.data.Dao.SentenceDao
import com.example.quickeng.data.entity.SentenceEntity

@Database(entities = [SentenceEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sentenceDao(): SentenceDao
}