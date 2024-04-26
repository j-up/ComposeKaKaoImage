package com.image.jm.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.image.jm.R

sealed class BottomNavigationScreens(
    val route: String,
    @StringRes val titleId: Int,
    @DrawableRes val iconId: Int
) {
    object Search : BottomNavigationScreens(
        "Search",
        R.string.tab_tile_search,
        R.drawable.search
    )

    object Bookmark : BottomNavigationScreens(
        "Bookmark",
        R.string.tab_tile_bookmark,
        R.drawable.bookmark_on
    )
}
