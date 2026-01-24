package com.example.quickeng.ui.script
import android.content.ContentValues.TAG
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.quickeng.ui.theme.Grey1
import com.example.quickeng.ui.theme.QuickEngTypography
import com.example.quickeng.ui.study.SentenceUi
import com.example.quickeng.ui.study.TagType
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import android.util.Log
import com.example.quickeng.viewmodel.StudyVM


// 1. 데이터 모델
data class ScriptItem(
    val id: Long,
    val tag: String,
    val eng: String,
    val kor: String,
    var isSelected: Boolean = false
)

// 2. 메인 화면 Composable
@Composable
fun ScriptScreen(videoId: Int, // 이제 ID를 받습니다
                 studyVM: StudyVM,
                 onAddClick: (List<ScriptItem>) -> Unit) {

    val videoData = remember { studyVM.getVideoData(videoId) }
    // 추가하기 버튼 누를시 넘어가기
    val selectedItems = remember { mutableStateListOf<ScriptItem>() }
    // URL에서 유튜브 ID 추출
    val youtubeId = videoData?.videoUrl?.split("v=")?.lastOrNull() ?: ""

    Scaffold { innerPadding ->
        // 전체를 감싸는 Box
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // 영상 + 리스트 (화면 전체 채움)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                // A. 영상 영역
                VideoPlayer(videoId = youtubeId,
                    modifier =
                        Modifier.fillMaxWidth().aspectRatio(16/9f))

                // B. 헤더
                Text(
                    text = "Script",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        start = 24.dp, end = 24.dp, top = 20.dp, bottom = 16.dp
                    )
                )

                // C. 리스트
                LazyColumn {
                    items(videoData?.learningContent ?: emptyList()) { content ->
                            // 1. content 데이터를 ScriptItem으로 변환
                            val currentItem = ScriptItem(
                                id = content.expression.hashCode().toLong(),
                                tag = content.contextTag,
                                eng = content.expression,
                                kor = content.meaningKr
                            )

                        val isSelected = selectedItems.any { it.eng == currentItem.eng }

                            ScriptCard(
                                item = currentItem.copy(isSelected = isSelected),
                                onClick = {
                                    val existing = selectedItems.find { it.eng == currentItem.eng }
                                    if (existing != null) {
                                        selectedItems.remove(existing)
                                    } else {
                                        selectedItems.add(currentItem)
                                    }
                                }
                            )
                    }
                }
            }

            // 하단 흰색 배경 + 버튼
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                // 1. 흰색 배경 (리스트 가림용)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .align(Alignment.BottomCenter)
                        .background(Color.White)
                )

                // 2. 버튼
                Button(
                    onClick = { onAddClick(selectedItems.toList()) },
                    enabled = selectedItems.isNotEmpty(),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, bottom = 10.dp)
                        .height(54.dp),
                    shape = RoundedCornerShape(100.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF64B5F6)),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                ) {
                    Text(
                        text = "${selectedItems.size}개 추가하기",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// 3. 개별 카드
@Composable
fun ScriptCard(item: ScriptItem, onClick: () -> Unit) {
    val borderColor = if (item.isSelected) Color(0xFF85C3F6) else Color(0xFF616161)
    val bgColor = if (item.isSelected) Color(0x3685C3F6) else Color(0xFFFFFFFF)
    val borderWidth = if (item.isSelected) 1.5.dp else 0.5.dp

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        modifier = Modifier
            .fillMaxWidth()
            .border(borderWidth, borderColor, RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) { // 카드 전체 내부 패딩
            Surface(
                color = Color(0xFFCFDFF5),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(
                    text = item.tag,
                    color = Color(0xFF0056C7),
                    style = QuickEngTypography.bodySmall,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }

            // 영어 문장
            Text(
                text = item.eng,
                style = QuickEngTypography.bodyLarge,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = 4.dp),
            )

            // 한글 해석
            Text(
                text = item.kor,
                style = QuickEngTypography.bodySmall,
                color = Grey1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// 4. 미리보기
//@Preview(showBackground = true, heightDp = 800)
//@Composable
//fun ScriptScreenPreview() {
//    MaterialTheme {
//        ScriptScreen(onAddClick = {})
//    }
//}

//유튜브 영상 플레이어
@Composable
fun VideoPlayer(
    videoId: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            YouTubePlayerView(ctx).apply {
                lifecycleOwner.lifecycle.addObserver(this)
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        // 영상 로드 (자동 재생 원하면 loadVideo, 클릭해야 재생은 cueVideo)
                        youTubePlayer.cueVideo(videoId, 0f)
                    }
                })
            }
        },
        onRelease = { view ->
            lifecycleOwner.lifecycle.removeObserver(view)
            view.release()
        }
    )
}

fun ScriptItem.toSentenceUi(): SentenceUi {
    return SentenceUi(
        id = id.toString(),
        tag = tag,
        tagType = TagType.BLUE,
        en = eng,
        ko = kor
    )
}

@Preview(showBackground = true, heightDp = 800)
@Composable
fun ScriptScreenPreview() {
    // 미리보기용 가짜 데이터 리스트
    val mockItems = listOf(
        ScriptItem(1, "Daily", "What's good?", "안녕? 무슨 좋은 일 있어?", isSelected = true),
        ScriptItem(2, "Greeting", "Long time no see.", "오랜만이야.", isSelected = false),
        ScriptItem(3, "Restaurant", "I'd like to order a pepperoni pizza.", "페퍼로니 피자 한 판 주문할게요.", isSelected = false)
    )

    MaterialTheme {
        // 실제 ScriptScreen 대신, UI 구조만 보여주는 방식으로 미리보기 구성
        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
            Column {
                // 영상 영역 (회색 박스로 대체)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16/9f)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("YouTube Player Area")
                }

                Text(
                    text = "Script",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(24.dp, 20.dp, 24.dp, 16.dp)
                )

                // 리스트 미리보기
                Column(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    mockItems.forEach { item ->
                        ScriptCard(item = item, onClick = {})
                    }
                }
            }

            // 하단 버튼 미리보기
            Button(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(24.dp, bottom = 34.dp)
                    .height(54.dp),
                shape = RoundedCornerShape(100.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF64B5F6))
            ) {
                Text("1개 추가하기", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}