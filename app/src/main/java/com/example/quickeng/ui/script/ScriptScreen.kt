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
import android.net.Uri
import com.example.quickeng.model.ScriptDataHolder
import com.example.quickeng.model.ScriptItem
import android.util.Log



@Composable
fun ScriptScreen(
    onAddClick: (List<ScriptItem>) -> Unit
) {
    // 1. 홀더에서 데이터 꺼내기 (없으면 빈 껍데기 처리)
    val data = ScriptDataHolder.currentData ?: return

    // 2. 가져온 데이터로 상태 초기화
    val scriptList = remember { mutableStateListOf(*data.scriptLines.toTypedArray()) }
    val videoId = data.videoId

    // 선택된 개수 계산
    val selectedCount = scriptList.count { it.isSelected }
    // 추가하기 버튼 누를시 넘어가기
    val selectedItems = scriptList.filter { it.isSelected }
    val context = LocalContext.current

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
                VideoPlayer(
                    videoId = videoId,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                )

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
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(
                        start = 24.dp,
                        end = 24.dp,
                    ),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(scriptList) { item ->
                        ScriptCard(
                            item = item,
                            onClick = {
                                val index = scriptList.indexOf(item)
                                scriptList[index] =
                                    scriptList[index].copy(isSelected = !item.isSelected)
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
                    onClick = {
                        val currentSelectedItems = scriptList.filter { it.isSelected }

                        Log.d("ScriptScreen", "추가하기 클릭. 선택된 아이템 수: ${currentSelectedItems.size}")

                        if (currentSelectedItems.isNotEmpty()) {
                            onAddClick(currentSelectedItems)
                            Toast.makeText(context, "${currentSelectedItems.size}개 문장 추가됨", Toast.LENGTH_SHORT).show()
                        }
                    },
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
                        text = "${selectedCount}개 추가하기",
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
@Preview(showBackground = true, heightDp = 800)
@Composable
fun ScriptScreenPreview() {
    MaterialTheme {
        ScriptScreen(onAddClick = {})
    }
}

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

// ScriptItem을 DB 저장용 SentenceUi로 변환
fun ScriptItem.toSentenceUi(videoId: String): SentenceUi {
    return SentenceUi(
        id = "${videoId}_${id}",
        tag = tag,
        tagType = TagType.BLUE,
        en = eng,
        ko = kor
    )
}