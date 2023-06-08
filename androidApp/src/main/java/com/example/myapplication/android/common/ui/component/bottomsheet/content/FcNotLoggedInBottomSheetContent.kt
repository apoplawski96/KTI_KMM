package com.apoplawski96.killtheinterview.common.ui.component.bottomsheet.content

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.apoplawski96.killtheinterview.common.ui.component.bottomsheet.base.FcBottomSheetSurface
import com.apoplawski96.killtheinterview.common.ui.component.bottomsheet.base.bottomSheetTitlePropertiesFromDefault

@Composable
fun FcNotLoggedInBottomSheetContent(
    title: String,
    onLoginButtonClick: () -> Unit,
) {
    FcBottomSheetSurface(
        title = title,
        bottomSheetTitleProperties = bottomSheetTitlePropertiesFromDefault(
            textAlign = TextAlign.Center,
            shouldFillMaxWidth = true,
        )
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            onClick = onLoginButtonClick
        ) {
            Text(text = "siema")
        }
        Spacer(modifier = Modifier.height(80.dp))
    }
}
