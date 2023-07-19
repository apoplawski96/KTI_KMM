package com.example.myapplication.android.ui.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.android.common.ui.component.KTIButton
import com.example.myapplication.android.ui.theme.KTITheme

@Composable
fun WelcomeScreen(viewModel: WelcomeScreenViewModel) {

    WelcomeScreenContent(navigateToHomeScreen = { viewModel.navigateToHomeScreen() })
}

@Composable
private fun WelcomeScreenContent(
    navigateToHomeScreen: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        KTIButton(
            label = "Go to app",
            labelColor = KTITheme.colors.textMain,
            backgroundColor = KTITheme.colors.secondary,
            onClick = navigateToHomeScreen
        )
    }
}

@Preview
@Composable
private fun Preview() {
    WelcomeScreenContent(navigateToHomeScreen = { })
}