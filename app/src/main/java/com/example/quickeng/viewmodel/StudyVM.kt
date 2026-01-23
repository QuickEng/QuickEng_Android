package com.example.quickeng.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.quickeng.ui.study.SentenceUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class StudyVM : ViewModel() {
    init {
        // ViewModel이 생성될 때마다 고유 해시코드를 출력합니다.
        Log.d("StudyVM", "ViewModel 생성됨: ${this.hashCode()}")
    }

    private val _sentences = MutableStateFlow<List<SentenceUi>>(emptyList())
    val sentences: StateFlow<List<SentenceUi>> = _sentences

    fun addSentences(newItems: List<SentenceUi>) {
        Log.d("StudyVM", "addSentences 호출됨! 아이템 개수: ${newItems.size}")
        _sentences.update { old ->
            val merged = (old + newItems).distinctBy { it.id }
            Log.d("StudyVM", "합쳐진 리스트 크기: ${merged.size}")
            merged
        }
    }
    init {
        Log.d("StudyVM", "ViewModel Initialized: ${this.hashCode()}")
    }

    // 문장 삭제 함수
    fun deleteSentence(id: String) {
        _sentences.update { old -> old.filterNot { it.id == id } }
    }
}
