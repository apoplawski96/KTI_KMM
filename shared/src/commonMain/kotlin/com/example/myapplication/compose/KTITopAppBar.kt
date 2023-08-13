package com.example.myapplication.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KTITopAppBar(
    title: String? = null,
    iconsSection: @Composable () -> Unit = { TopBarIconsSection() },
    isNested: Boolean = true,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        LeftSection(title = title, isNested = isNested)
        Row(verticalAlignment = Alignment.CenterVertically) {
            iconsSection.invoke()
        }
    }
}

@Composable
private fun RowScope.LeftSection(
    isNested: Boolean,
    title: String? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isNested) KTIBackButton()
        KTIHorizontalSpacer(width = 8.dp)
        title?.let { KTITextNew(text = title, fontSize = 18.sp, fontWeight = FontWeight.W500) }
    }
}

@Composable
private fun TopBarIconsSection() {
//    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {
//        KTIIconButton(
//            onClick = {
////                showNotYetImplementedToast(context)
//            }
//        ) {
//            KTIIcon(drawableRes = R.drawable.ic_bookmarks)
//        }
//        KTIIconButton(
//            onClick = {
////                showNotYetImplementedToast(context)
//            }
//        ) {
//            KTIIcon(drawableRes = R.drawable.ic_user)
//        }
//        KTIIconButton(
//            onClick = {
////                showNotYetImplementedToast(context)
//            }
//        ) {
//            KTIIcon(drawableRes = R.drawable.ic_menu)
//        }
    }
}
