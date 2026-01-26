package com.example.quickeng.ui.analyze

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickeng.data.VideoRepository
import com.example.quickeng.data.remote.AnalyzeResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import com.google.gson.Gson


// 서버 에러 응답(JSON)을 파싱하기 위한 모델
data class ErrorResponse(
    val code: String,
    val message: String
)

//분석 화면(HomeScreen 등)에서 관찰할 UI 상태
sealed interface AnalyzeUiState {
    data object Idle : AnalyzeUiState
    data object Loading : AnalyzeUiState
    data class Success(val data: AnalyzeResponse) : AnalyzeUiState
    data class Error(val message: String) : AnalyzeUiState
}

// HomeScreen에서 URL 입력 -> analyze() 호출
// Repository를 통해 서버 통신 수행
// 결과를 uiState(StateFlow)로 노출하여 UI가 상태에 따라 반응하도록 함
class AnalyzeViewModel(
    private val repo: VideoRepository = VideoRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<AnalyzeUiState>(AnalyzeUiState.Idle)
    val uiState: StateFlow<AnalyzeUiState> = _uiState

    // 영상 URL 분석 요청
    fun analyze(videoUrl: String, targetLang: String = "ko") {
        viewModelScope.launch {
            _uiState.value = AnalyzeUiState.Loading
            runCatching { repo.analyze(videoUrl, targetLang) }
                .onSuccess { _uiState.value = AnalyzeUiState.Success(it) }
                .onFailure { e ->
                    // 에러 발생 시 서버에서 내려온 에러메시지 출력
                    val errorMessage = parseErrorMsg(e)
                    _uiState.value = AnalyzeUiState.Error(errorMessage)
                }
        }
    }

    // 중복 토스트/중복 네비게이션 방지
    fun resetState() {
        _uiState.value = AnalyzeUiState.Idle // 초기 상태(Idle)로 되돌리기
    }

    // 에러 메시지 추출기
    private fun parseErrorMsg(e: Throwable): String {
        return if (e is HttpException) {
            try {
                val errorJson = e.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorJson, ErrorResponse::class.java)
                errorResponse.message
            } catch (e: Exception) {
                "서버 통신 오류가 발생했습니다. 다시 시도해주세요."
            }
        } else {
            e.message ?: "알 수 없는 오류"
        }
    }
}

