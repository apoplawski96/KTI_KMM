package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.example.myapplication.compose.KTIButton
import com.example.myapplication.compose.KTITextButton
import com.example.myapplication.compose.KTITextNew
import com.example.myapplication.compose.KTIVerticalSpacer
import com.example.myapplication.theme.KTITheme

object ExampleScreen : Screen {

    @Composable
    override fun Content() {
        WelcomeScreenContent(
            navigateToLoginScreen = { },
            navigateToHomeScreen = { },
        )
    }
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
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopSection(modifier = Modifier.weight(2f))
        BottomSection(navigateToHomeScreen = navigateToHomeScreen, navigateToLoginScreen = navigateToLoginScreen, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun TopSection(modifier: Modifier = Modifier) {
    Box(modifier = modifier then Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
//        KTIIllustration(drawableRes = R.drawable.undraw_programming_re_kg9v)
    }
}

@Composable
private fun BottomSection(
    navigateToHomeScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier then Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
            onClick = navigateToLoginScreen,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        )
        KTITextButton(
            onClick = navigateToHomeScreen,
            label = "Continue without account",
            labelColor = KTITheme.colors.textMain,
            size = 12.sp
        )
    }
}