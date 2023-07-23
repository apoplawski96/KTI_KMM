package com.example.myapplication.android.common.ui.component.bottomsheet.content

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButtonColors
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun KTIRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: RadioButtonColors = RadioButtonDefaults.colors(
        selectedColor = Color.Black,
        unselectedColor = Color.Gray,
    ),
) {
    val dotRadius by animateDpAsState(
        targetValue = if (selected) KTIRadioButtonDotSize / 2 else 0.dp,
        animationSpec = tween(durationMillis = KTIRadioAnimationDuration)
    )
    val radioColor by colors.radioColor(enabled, selected)
    val selectableModifier =
        if (onClick != null) {
            Modifier.selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.RadioButton,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = false,
                    radius = KTIRadioButtonRippleRadius
                )
            )
        } else {
            Modifier
        }
    Canvas(
        modifier
            .then(selectableModifier)
            .wrapContentSize(Alignment.Center)
            .padding(KTIRadioButtonPadding)
            .requiredSize(KTIRadioButtonSize)
    ) {
        drawRadio(radioColor, dotRadius)
    }
}

private fun DrawScope.drawRadio(color: Color, dotRadius: Dp) {
    val strokeWidth = KTIRadioStrokeWidth.toPx()
    drawCircle(color, KTIRadioRadius.toPx() - strokeWidth / 2, style = Stroke(strokeWidth))
    if (dotRadius > 0.dp) {
        drawCircle(color, dotRadius.toPx() - strokeWidth / 2, style = Fill)
    }
}

private const val KTIRadioAnimationDuration = 100

private val KTIRadioButtonRippleRadius = 20.dp
private val KTIRadioButtonPadding = 2.dp
private val KTIRadioButtonSize = 20.dp
private val KTIRadioRadius = KTIRadioButtonSize / 2
private val KTIRadioButtonDotSize = 10.dp
private val KTIRadioStrokeWidth = 1.dp