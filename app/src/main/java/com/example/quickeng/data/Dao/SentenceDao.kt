package com.example.quickeng.data.Dao

import androidx.room.*
import com.example.quickeng.data.entity.SentenceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SentenceDao {
    @Query("SELECT * FROM sentences ORDER BY id DESC")
    fun getAllSentences(): Flow<List<SentenceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSentences(sentences: List<SentenceEntity>)

    @Query("DELETE FROM sentences WHERE id = :id")
    suspend fun deleteSentence(id: Int)
}