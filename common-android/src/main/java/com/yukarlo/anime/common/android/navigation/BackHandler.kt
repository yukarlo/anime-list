package com.yukarlo.anime.common.android.navigation

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.*

@Composable
fun BackHandler(
    enabled: Boolean = true,
    onBack: () -> Unit
) {
    val currentOnBack by rememberUpdatedState(onBack)

    val backCallback = remember {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                currentOnBack()
            }
        }
    }

    SideEffect {
        backCallback.isEnabled = enabled
    }
    val backDispatcher = LocalBackDispatcher.current
    DisposableEffect(backDispatcher) {
        backDispatcher.addCallback(backCallback)
        onDispose {
            backCallback.remove()
        }
    }
}

val LocalBackDispatcher = staticCompositionLocalOf<OnBackPressedDispatcher> {
    error("No Back Dispatcher provided")
}
