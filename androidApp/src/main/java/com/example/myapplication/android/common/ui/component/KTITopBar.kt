package com.example.myapplication.android.common.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.example.myapplication.navigation.Navigator
import com.example.myapplication.android.common.ui.component.KTIText
import org.koin.androidx.compose.get

@Composable
fun KTIDestinationTopBar(
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
    navigator: Navigator = get(),
) {
    TopAppBar(
        title = { KTIText(text = title) },
        actions = actions,
        navigationIcon = {
            IconButton(onClick = { navigator.navigateBack() }) {
                Icon(Icons.Filled.ArrowBack, "Back Icon")
            }
        },
    )
}