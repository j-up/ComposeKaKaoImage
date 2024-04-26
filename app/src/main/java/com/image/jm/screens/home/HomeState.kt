package com.image.jm.screens.home

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.image.jm.model.KakaoImage

@Stable
sealed class HomeState {
    @Immutable
    object OnClear : HomeState()

    @Immutable
    object OnFail : HomeState()

    @Immutable
    data class OnImageListLoad(val query: String, val list: List<KakaoImage>) : HomeState()
}