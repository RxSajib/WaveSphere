package com.zenbyte.studio.presentation.viewmodel.utils

import android.os.SystemClock
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@Composable
fun Modifier.debounceClickable(
    debounceTime: Long = 1000L,
    onClick: () -> Unit
): Modifier = composed {

    var lastClickTime by remember {
        mutableLongStateOf(0L)
    }

    clickable {
        val currentTime = System.currentTimeMillis()

        if (currentTime - lastClickTime > debounceTime) {
            lastClickTime = currentTime
            onClick()
        }
    }
}

@Composable
fun rememberDebouncedClick(
    debounceTime: Long = 1000L,
    onClick: () -> Unit
): () -> Unit {

    val currentClick by rememberUpdatedState(onClick)

    var lastClickTime by remember {
        mutableLongStateOf(0L)
    }

    return remember(debounceTime) {
        {
            val now = SystemClock.elapsedRealtime()

            if (now - lastClickTime > debounceTime) {
                lastClickTime = now
                currentClick()
            }
        }
    }
}