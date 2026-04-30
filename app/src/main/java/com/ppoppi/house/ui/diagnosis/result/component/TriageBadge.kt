package com.ppoppi.house.ui.diagnosis.result.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.domain.Triage
import com.ppoppi.house.domain.Triage.Companion.toColor
import com.ppoppi.house.ui.theme.PpoPpiTheme

@Composable
fun TriageBadge(
    triage: Triage,
    modifier: Modifier = Modifier,
) {
    val (backgroundColor, borderColor, textColor) = triage.toColor()

    Row(
        modifier =
            modifier
                .clip(RoundedCornerShape(100.dp))
                .background(backgroundColor)
                .border(1.dp, borderColor, RoundedCornerShape(100.dp))
                .padding(vertical = 10.dp, horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier =
                Modifier
                    .size(10.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(borderColor),
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text =
                triage.name
                    .lowercase()
                    .replaceFirstChar { it.uppercase() },
            style = PpoPpiTheme.typography.body3,
            color = textColor,
        )
    }
}

@Preview(showBackground = true, name = "응급도가 URGENT일 때")
@Composable
private fun TriageBadgePreview_Urgent() {
    PpoPpiTheme {
        TriageBadge(triage = Triage.URGENT)
    }
}

@Preview(showBackground = true, name = "응급도가 SOON일 때")
@Composable
private fun TriageBadgePreview_Soon() {
    PpoPpiTheme {
        TriageBadge(triage = Triage.SOON)
    }
}

@Preview(showBackground = true, name = "응급도가 Monitor일 때")
@Composable
private fun TriageBadgePreview_Monitor() {
    PpoPpiTheme {
        TriageBadge(triage = Triage.MONITOR)
    }
}

@Preview(showBackground = true, name = "응급도가 Normal일 때")
@Composable
private fun TriageBadgePreview_Normal() {
    PpoPpiTheme {
        TriageBadge(triage = Triage.NORMAL)
    }
}
