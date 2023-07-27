package com.example.myapplication.android.ui.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.android.common.ui.component.KTIButton
import com.example.myapplication.android.common.ui.component.KTITextButton
import com.example.myapplication.android.common.ui.component.KTITextNew
import com.example.myapplication.android.common.ui.component.KTIVerticalSpacer
import com.example.myapplication.android.ui.theme.KTITheme
import org.koin.androidx.compose.get

@Composable
fun WelcomeScreen(viewModel: WelcomeScreenViewModel = get()) {

    WelcomeScreenContent(
        navigateToHomeScreen = { viewModel.navigateToHomeScreen() },
        navigateToLoginScreen = { }
    )
}

@Composable
private fun WelcomeScreenContent(
    navigateToHomeScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KTITheme.colors.backgroundSurface)
            .padding(32.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        KTITextNew(
            text = "Kill your upcoming tech interview",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        KTIVerticalSpacer(height = 16.dp)
        KTIButton(
            label = "Create account",
            labelColor = KTITheme.colors.textMain,
            backgroundColor = KTITheme.colors.secondary,
            onClick = navigateToLoginScreen
        )
        KTITextButton(
            onClick = navigateToHomeScreen,
            label = "Continue without account",
            labelColor = KTITheme.colors.textMain,
            size = 12.sp
        )
    }
}

@Preview
@Composable
private fun Preview() {
    WelcomeScreenContent(navigateToHomeScreen = { }, navigateToLoginScreen = { })
}