package com.example.quickeng.ui.study

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.quickeng.R
import com.example.quickeng.ui.theme.Black
import com.example.quickeng.ui.theme.Grey1
import com.example.quickeng.ui.theme.Grey2
import com.example.quickeng.ui.theme.Grey3
import com.example.quickeng.ui.theme.QuickEngTypography
import com.example.quickeng.ui.theme.White

@Composable
fun SentenceCard(
    item: SentenceUi,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Grey3),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                TagChip(
                    text = item.tag,
                    type = item.tagType
                )

                Spacer(Modifier.weight(1f))

                Icon(
                    painter = painterResource(id = R.drawable.ic_trash),
                    contentDescription = null,
                    tint = Grey2,
                    modifier = Modifier
                        .size(18.dp)
                        .clickable { onDelete() }
                )
            }

            Spacer(Modifier.height(10.dp))

            Text(
                text = item.en,
                style = QuickEngTypography.bodyLarge,
                color = Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = item.ko,
                style = QuickEngTypography.bodySmall,
                color = Grey1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
@androidx.compose.ui.tooling.preview.Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    name = "SentenceCard Preview - Long Text"
)
@Composable
private fun SentenceCardPreview_Long() {
    SentenceCard(
        item = SentenceUi(
            id = "2",
            tag = "Ordering Coffee",
            tagType = TagType.PURPLE,
            en = "That's how you order like a local without stressing out. This is a longer sentence to test ellipsis behavior in the card layout.",
            ko = "이렇게 하면 스트레스 없이 현지인처럼 주문할 수 있어요. 길게 써서 말줄임이 잘 되는지도 확인하는 테스트 문장입니다."
        ),
        onDelete = {}
    )
}

