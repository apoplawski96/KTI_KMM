package com.example.myapplication.android.common.ui.component.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.android.R
import com.example.myapplication.android.common.ui.component.KTIBackButton
import com.example.myapplication.android.common.ui.component.KTIHorizontalSpacer
import com.example.myapplication.android.common.ui.component.KTIIcon
import com.example.myapplication.android.common.ui.component.KTIIconButton
import com.example.myapplication.android.common.ui.component.KTITextNew

@Composable
fun KTITopBarNew(title: String? = null) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        LeftSection(title = title)
        TopBarIconsSection()
    }
}

@Composable
private fun LeftSection(title: String? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        KTIBackButton()
        KTIHorizontalSpacer(width = 8.dp)
        title?.let { KTITextNew(text = title, fontSize = 18.sp, fontWeight = FontWeight.W500) }
    }
}

@Composable
private fun TopBarIconsSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {
        KTIIconButton(onClick = { }) {
            KTIIcon(drawableRes = R.drawable.ic_bookmarks)
        }
        KTIIconButton(onClick = { }) {
            KTIIcon(drawableRes = R.drawable.ic_user)
        }
        KTIIconButton(onClick = { }) {
            KTIIcon(drawableRes = R.drawable.ic_menu)
        }
    }
}
