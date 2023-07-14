package com.example.myapplication.android.common.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.myapplication.android.ui.theme.kti_dark_primary
import com.example.myapplication.android.ui.theme.kti_text_icons
import com.example.myapplication.navigation.Navigator
import org.koin.androidx.compose.get

@Composable
fun KTIDestinationTopBar(
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
    navigator: Navigator = get(),
    hasBackButton: Boolean = true,
) {
    TopAppBar(
        title = {
            KTITextNew(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                color = kti_text_icons,
                textAlign = TextAlign.Center,
            )
        },
        actions = actions,
        backgroundColor = Color.Black,
        contentColor = kti_text_icons,
        navigationIcon = {
            if (hasBackButton) {
                IconButton(onClick = { navigator.navigateBack() }) {
                    Icon(Icons.Filled.KeyboardArrowLeft, "Back Icon")
                }
            }
        },
    )
}