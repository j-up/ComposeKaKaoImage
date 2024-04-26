package com.image.jm.screens.bookmark

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
sealed class BookmarkState {
    @Immutable
    object OnClear : BookmarkState()

    @Immutable
    data class OnBookmarkListLoad(val list: List<String>) : BookmarkState()
}