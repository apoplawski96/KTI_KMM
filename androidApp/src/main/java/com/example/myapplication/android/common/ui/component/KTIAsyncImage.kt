package com.apoplawski96.killtheinterview.common.ui.component

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun KTIAsyncImage(
    url: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    SubcomposeAsyncImage(
        model = rememberImageRequest(data = url),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier,
    )
}

@Composable
private fun rememberImageRequest(
    data: Any?,
): ImageRequest {
    val context = LocalContext.current
    return remember {
        ImageRequest(context, data = data)
    }
}

private fun ImageRequest(
    context: Context,
    data: Any?,
) = ImageRequest.Builder(context).apply {
    data(data)
    crossfade(700)
}.build()
