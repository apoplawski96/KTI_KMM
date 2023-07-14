package com.example.myapplication.android.common.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.android.ui.theme.kti_accent_color
import com.example.myapplication.android.ui.theme.kti_dark_primary
import com.example.myapplication.android.ui.theme.kti_primary_text
import com.example.myapplication.navigation.Navigator
import org.koin.androidx.compose.get
import kotlin.math.max

object TestTagsTopBar {
    const val TOP_BAR = "TOP_BAR"
    const val TOP_BAR_TEXT = "TOP_BAR_TEXT"
}

@Composable
fun FcRootTopBar(
    leftActionButtons: @Composable RowScope.() -> Unit = {  },
    middleContent: @Composable () -> Unit = { TopBarIcon() },
    rightActionButtons: @Composable (RowScope.() -> Unit)? = {  },
    hasBrandingLine: Boolean = false,
) {
    FcTopBarContent(leftActionButtons, middleContent, rightActionButtons, hasBrandingLine)
}

@Composable
fun FcTextTopBar(
    middleContentText: String,
    isNested: Boolean,
    actionsEnabled: Boolean = true,
    hasBrandingLine: Boolean = false,
    rightActionButtons: @Composable RowScope.() -> Unit = { },
    navigator: Navigator = get()
) {
    FcTopBarContent(
        leftActionButtons = {
            if (isNested) {
                IconButton(onClick = { navigator.navigateBack() }) {
                    Icon(Icons.Filled.KeyboardArrowLeft, "Back Icon", tint = kti_primary_text)
                }
            }
        },
        middleContent = { TopBarText(text = middleContentText) },
        rightActionButtons = rightActionButtons,
        hasBrandingLine = hasBrandingLine
    )
}

@Composable
private fun FcTopBarContent(
    leftActionButtons: @Composable RowScope.() -> Unit,
    middleContent: @Composable () -> Unit,
    rightActionButtons: @Composable (RowScope.() -> Unit)?,
    hasBrandingLine: Boolean = false,
) {
    Column(modifier = Modifier.background(kti_dark_primary)) {
        TopAppBar(
            elevation = 0.dp,
            backgroundColor = kti_dark_primary,
            contentPadding = PaddingValues(horizontal = 4.dp),
            modifier = Modifier
                .statusBarsPadding()
                .height(56.dp)
                .testTag(TestTagsTopBar.TOP_BAR)
        ) {
            val content: @Composable () -> Unit = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    leftActionButtons()
                }
                middleContent()
                if (rightActionButtons != null) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        rightActionButtons()
                    }
                }
            }

            Layout(content) { measurables, constraints ->
                val left = measurables[0].measure(constraints)
                val right =
                    if (rightActionButtons == null) null else measurables[2].measure(constraints)

                val sideItemMaxWidth = max(left.width, right?.width ?: 0)
                val sideItemSlotCount = if (rightActionButtons == null) 1 else 2
                val middleWidth = constraints.maxWidth - sideItemMaxWidth * sideItemSlotCount
                val middle = measurables[1].measure(
                    Constraints.fixed(width = middleWidth, height = constraints.maxHeight)
                )

                layout(constraints.maxWidth, constraints.maxHeight) {
                    left.place(x = 0, y = 0)
                    middle.place(x = sideItemMaxWidth, y = 0)
                    if (right != null) {
                        val rightItemOffset = sideItemMaxWidth - right.width
                        right.place(x = sideItemMaxWidth + middle.width + rightItemOffset, y = 0)
                    }
                }
            }
        }
        if (hasBrandingLine) Divider(color = kti_accent_color, thickness = 2.dp)
    }
}

@Composable
private fun TopBarIcon() {
//    FcIcon(
//        drawableRes = R.drawable.ic_goal_logo_with_text,
//        size = iconSize(width = 112.dp, height = 56.dp),
//        contentDescription = "Goal logo",
//        tint = GoalTheme.colors.onPrimary,
//        modifier = Modifier.padding(vertical = 4.dp)
//    )
}

@Composable
fun TopBarText(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        KTITextNew(
            text = text,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.testTag(TestTagsTopBar.TOP_BAR_TEXT),
            fontSize = 18.sp,
            fontWeight = FontWeight.W600,
        )
    }
}