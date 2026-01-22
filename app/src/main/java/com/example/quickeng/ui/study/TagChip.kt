package com.example.quickeng.ui.study

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quickeng.ui.theme.PrimaryColor
import com.example.quickeng.ui.theme.QuickEngTypography
import com.example.quickeng.ui.theme.SubColor1
import com.example.quickeng.ui.theme.SubColor2
import com.example.quickeng.ui.theme.SubColor3
import com.example.quickeng.ui.theme.White

@Composable
fun TagChip(
    text: String,
    type: TagType
) {
    val baseColor = when (type) {
        TagType.BLUE -> SubColor1
        TagType.PURPLE -> SubColor3
        TagType.GREEN -> SubColor2
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(baseColor.copy(alpha = 0.19f))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = QuickEngTypography.bodySmall,
            color = baseColor
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, name = "TagChip - BLUE")
@Composable
private fun TagChipPreview_Blue() {
    Column(
        modifier = Modifier
            .background(White)
            .padding(20.dp)
    ) {
        TagChip(text = "NYC Slang", type = TagType.BLUE)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, name = "TagChip - PURPLE")
@Composable
private fun TagChipPreview_Purple() {
    Column(
        modifier = Modifier
            .background(White)
            .padding(20.dp)
    ) {
        TagChip(text = "Ordering Coffee", type = TagType.PURPLE)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, name = "TagChip - GREEN")
@Composable
private fun TagChipPreview_Green() {
    Column(
        modifier = Modifier
            .background(White)
            .padding(20.dp)
    ) {
        TagChip(text = "At the Airport", type = TagType.GREEN)
    }
}

/**
 * 한 화면에서 3개를 같이 보고 싶으면 이 프리뷰도 추가로 쓰면 됨
 */
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, name = "TagChip - All")
@Composable
private fun TagChipPreview_All() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TagChip(text = "NYC Slang", type = TagType.BLUE)
        TagChip(text = "Ordering Coffee", type = TagType.PURPLE)
        TagChip(text = "At the Airport", type = TagType.GREEN)
    }
}
