package com.example.quickeng.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.quickeng.data.AppDatabase
import com.example.quickeng.data.entity.SentenceEntity
import com.example.quickeng.ui.study.SentenceUi
import com.example.quickeng.ui.study.TagType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudyVM(application: Application) : AndroidViewModel(application) {
    // 1. DB 초기화
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "quickeng-database"
    ).build()

    private val sentenceDao = db.sentenceDao()

    // 2. DB 데이터를 실시간 Flow로 읽어와서 UI 모델로 변환
    val sentences: StateFlow<List<SentenceUi>> = sentenceDao.getAllSentences()
        .map { entities ->
            entities.map { it.toUiModel() }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // 3. 문장 추가 로직
    fun addSentences(newSentences: List<SentenceUi>) {
        viewModelScope.launch {
            sentenceDao.insertSentences(newSentences.map { it.toEntity() })
        }
    }

    // 4. 문장 삭제 로직 (id가 String인 경우 Int로 변환)
    fun deleteSentence(id: String) {
        viewModelScope.launch {
            sentenceDao.deleteSentence(id.toInt())
        }
    }
}

// 확장 함수: 변환 로직
fun SentenceEntity.toUiModel() = SentenceUi(id.toString(), tag, TagType.BLUE, en, ko)
fun SentenceUi.toEntity() = SentenceEntity(tag = tag, en = en, ko = ko)