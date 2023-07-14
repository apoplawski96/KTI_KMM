//package com.footballco.android.common.ui.components.topbar
//
//import androidx.annotation.DrawableRes
//import androidx.compose.foundation.layout.RowScope
//import androidx.compose.material.IconButton
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import com.footballco.android.common.ui.components.icon.FcIcon
//import com.footballco.android.common.ui.components.icon.model.iconSize
//import com.footballco.android.common.ui.utils.emptyClick
//import com.footballco.android.navigation.NavigationManager
//import com.footballco.android.navigation.destination.BottomNavDestinations
//import com.footballco.android.theme.GoalTheme
//import com.footballco.mobile.android.common.ui.R
//import org.koin.androidx.compose.get
//
//@Composable
//fun MoreAction(
//    clickingEnabled: Boolean = true,
//    navigationManager: NavigationManager = get()
//) {
//    MoreActionContent(
//        onClick = {
//            if (clickingEnabled) {
//                navigationManager.navigate(
//                    BottomNavDestinations.Common.More.destination()
//                )
//            }
//        },
//    )
//}
//
//@Composable
//fun MoreActionContent(onClick: () -> Unit) {
//    TopBarIconButton(
//        drawableResource = R.drawable.ic_more,
//        contentDescription = "More menu",
//        onClick = onClick,
//    )
//}
//
//@Composable
//fun RowScope.DefaultRightActions() {
//    SearchAction()
//}
//
//@Composable
//fun ActivityStreamAction(
//    clickingEnabled: Boolean = true,
//    navigationManager: NavigationManager = get()
//) {
//    ActivityStreamActionContent(
//        onClick = {
//            if (clickingEnabled) {
//                navigationManager.navigate(
//                    BottomNavDestinations.Common.ActivityStream.destination()
//                )
//            }
//        },
//    )
//}
//
//@Composable
//fun ActivityStreamAction(
//    onClick: () -> Unit,
//    clickingEnabled: Boolean = true,
//) {
//    ActivityStreamActionContent(
//        onClick = if (clickingEnabled) onClick else emptyClick,
//    )
//}
//
//// TODO - think about blocking usage outside of previews
//@Composable
//fun ActivityStreamActionContent(onClick: () -> Unit) {
//    val (tint: Color, iconRes: Int) = GoalTheme.colors.onPrimary to R.drawable.ic_activity_stream
//
//    TopBarIconButton(
//        drawableResource = iconRes,
//        contentDescription = "Activity",
//        onClick = onClick,
//        tint = tint
//    )
//}
//
//@Composable
//fun FollowAction(
//    clickingEnabled: Boolean = true,
//    onClick: () -> Unit = {},
//    isFollowed: Boolean,
//) {
//    FollowActionContent(
//        onClick = if (clickingEnabled) onClick else emptyClick,
//        isFollowed = isFollowed
//    )
//}
//
//@Composable
//private fun FollowActionContent(
//    onClick: () -> Unit,
//    isFollowed: Boolean,
//) {
//    TopBarIconButton(
//        drawableResource = if (isFollowed) {
//            R.drawable.ic_star_filled
//        } else {
//            R.drawable.ic_star
//        },
//        onClick = onClick,
//        contentDescription = "Follow",
//        tint = if (isFollowed) {
//            GoalTheme.colors.secondary
//        } else {
//            GoalTheme.colors.onPrimary
//        }
//    )
//}
//
//@Composable
//fun SearchAction(
//    clickingEnabled: Boolean = true,
//    navigationManager: NavigationManager = get()
//) {
//    SearchActionContent(
//        onClick = {
//            if (clickingEnabled) {
//                navigationManager.navigate(
//                    BottomNavDestinations.Common.Search.destination()
//                )
//            }
//        },
//    )
//}
//
//@Composable
//fun SearchActionContent(onClick: () -> Unit) {
//    TopBarIconButton(
//        drawableResource = R.drawable.ic_search,
//        contentDescription = "Search",
//        onClick = onClick,
//    )
//}
//
//@Composable
//fun CloseAction(
//    clickingEnabled: Boolean = true,
//    navigationManager: NavigationManager = get()
//) {
//    CloseActionContent(
//        onClick = {
//            if (clickingEnabled) {
//                navigationManager.navigateBack()
//            }
//        },
//    )
//}
//
//@Composable
//fun CloseActionContent(onClick: () -> Unit) {
//    TopBarIconButton(
//        drawableResource = R.drawable.ic_close,
//        contentDescription = "Close",
//        onClick = onClick,
//    )
//}
//
//@Composable
//fun BackAction(
//    clickingEnabled: Boolean = true,
//    navigationManager: NavigationManager = get()
//) {
//    BackActionContent(
//        onClick = {
//            if (clickingEnabled) {
//                navigationManager.navigateBack()
//            }
//        },
//    )
//}
//
//@Composable
//fun BackActionContent(onClick: () -> Unit) {
//    TopBarIconButton(
//        drawableResource = R.drawable.ic_back,
//        contentDescription = "Back",
//        onClick = onClick,
//    )
//}
//
//@Composable
//fun TopBarIconButton(
//    @DrawableRes drawableResource: Int,
//    onClick: () -> Unit,
//    contentDescription: String,
//    modifier: Modifier = Modifier,
//    tint: Color = GoalTheme.colors.onPrimary,
//) {
//    IconButton(onClick = onClick) {
//        FcIcon(
//            drawableRes = drawableResource,
//            size = iconSize(24.dp),
//            contentDescription = contentDescription,
//            tint = tint,
//            modifier = modifier
//        )
//    }
//}
