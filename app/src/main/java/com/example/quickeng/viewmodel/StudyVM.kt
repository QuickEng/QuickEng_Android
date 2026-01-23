package com.example.quickeng.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.quickeng.ui.study.SentenceUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class StudyVM : ViewModel() {
    private val _sentences = MutableStateFlow<List<SentenceUi>>(emptyList())
    val sentences: StateFlow<List<SentenceUi>> = _sentences

    // 문장 추가 함수
    fun addSentences(newItems: List<SentenceUi>) {
        Log.d("StudyVM", "addSentences: ${newItems.size} items to add")
        _sentences.update { old ->
            val merged = (old + newItems).distinctBy { it.id }
            Log.d("StudyVM", "addSentences: New items added. Merged list size: ${merged.size}")
            merged
        }
    }

    // 문장 삭제 함수
    fun deleteSentence(id: String) {
        _sentences.update { old -> old.filterNot { it.id == id } }
    }
}
