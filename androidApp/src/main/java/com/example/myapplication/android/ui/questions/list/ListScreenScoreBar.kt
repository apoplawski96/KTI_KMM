package com.example.myapplication.android.ui.questions.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.android.common.ui.component.KTITextNew
import com.example.myapplication.android.ui.theme.kti_accent_color
import com.example.myapplication.android.ui.theme.kti_dark_primary
import com.example.myapplication.android.ui.theme.kti_primary_text

@Composable
fun ListScreenScoreBar(score: Int, total: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(kti_dark_primary.copy(alpha = 0.8f))
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            KTITextNew(
                text = "Question answered: $score/$total",
                fontSize = 13.sp,
                fontWeight = FontWeight.W300,
                color = kti_primary_text.copy(alpha = 0.9f),
                modifier = Modifier.padding(start = 12.dp, top = 0.dp, bottom = 8.dp, end = 12.dp)
            )
        }
        Divider(color = kti_accent_color, thickness = 2.dp)
    }
}