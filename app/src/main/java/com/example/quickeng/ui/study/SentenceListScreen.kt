package com.example.quickeng.ui.study

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quickeng.ui.theme.*

//더미데이터
data class SentenceUi(
    val id: String,
    val tag: String,
    val tagType: TagType,
    val en: String,
    val ko: String
)

enum class TagType { BLUE, PURPLE, GREEN }


@Composable
fun SentenceListScreen(
    items: List<SentenceUi> = emptyList(),
    onDelete: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Log.d("SentenceListScreen", "Items: ${items.map { it.id }}")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .padding(horizontal = 20.dp)
    ) {
        SentenceListTopBar(
            title = "Sentence list",
            count = items.size
        )

        Spacer(Modifier.height(12.dp))

        if (items.isEmpty()) {
            EmptySentenceView(
                modifier = Modifier.fillMaxSize()
            )
        } else {
            SentenceList(
                items = items,
                onDelete = onDelete,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

/** 상단 타이틀 + 오른쪽 문장 수 */
@Composable
private fun SentenceListTopBar(
    title: String,
    count: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = QuickEngTypography.headlineLarge,
            color = Black
        )

        Spacer(Modifier.weight(1f))

        Text(
            text = "${count} 문장",
            style = QuickEngTypography.bodyMedium,
            color = Grey1
        )
    }
}
//학습 데이터 없을 경우
@Composable
private fun EmptySentenceView(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = "학습을 시작해보세요.",
            style = QuickEngTypography.headlineMedium,
            color = Grey2
        )
    }
}


//학습 데이터 있을 경우
@Composable
private fun SentenceList(
    items: List<SentenceUi>,
    onDelete: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(top = 4.dp, bottom = 90.dp)
    ) {
        items(
            items = items,
            key = { it.id }
        ) { item ->
            Log.d("SentenceList", "Rendering item: ${item.id} - ${item.en}")
            SentenceCard(
                item = item,
                onDelete = { onDelete(item.id) }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, name = "SentenceList - Empty")
@Composable
private fun SentenceListPreview_Empty() {
    SentenceListScreen(
        items = emptyList(),
        onDelete = {}
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, name = "SentenceList - Filled")
@Composable
private fun SentenceListPreview_Filled() {
    val mock = listOf(
        SentenceUi(
            id = "1",
            tag = "NYC Slang",
            tagType = TagType.BLUE,
            en = "“In New York, we don’t really say hello.”",
            ko = "뉴욕에서 우리는 'hello'라고 잘 안 해요."
        ),
        SentenceUi(
            id = "2",
            tag = "Ordering Coffee",
            tagType = TagType.PURPLE,
            en = "That's how you order like a local without stressing out.",
            ko = "이렇게 하면 스트레스 없이 현지인처럼 주문할 수 있어요."
        ),
        SentenceUi(
            id = "3",
            tag = "At the Airport",
            tagType = TagType.GREEN,
            en = "That's how you order like a local without stressing out.",
            ko = "이렇게 하면 스트레스 없이 현지인처럼 주문할 수 있어요."
        )
    )

    SentenceListScreen(
        items = mock,
        onDelete = {}
    )
}

