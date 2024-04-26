package com.image.jm.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ImageColors(
    val primary: Color,
    val primaryDark: Color,
    val accent: Color,
    val accentSecondary: Color,
    val textColorPrimary: Color,
    val textColorSecondary: Color,
    val textHighlighted: Color,
    val background: Color,
    val itemBackground: Color,
    val dialogWindowBackground: Color,
    val bottomNavBackground: Color,
    val bottomTrayBackground: Color,
    val statusBar: Color,
    val selectedTab: Color,
    val unselectedTab: Color
)

val lightImageColors: ImageColors = ImageColors(
    primary = Color(0xff00A2EA),
    primaryDark = Color(0xff303F9F),
    accent = Color(0xffFF4081),
    accentSecondary = Color(0xff2C8A45),
    textColorPrimary = Color(0xff000000),
    textColorSecondary = Color(0xff7F7F82),
    textHighlighted = Color(0xff58B5E5),
    background = Color(0xffE3E6E9),
    itemBackground = Color(0xffFFFFFF),
    dialogWindowBackground = Color(0xffE0E5E9),
    bottomNavBackground = Color(0xffFFFFFF),
    bottomTrayBackground = Color(0xffF7F7F7),
    statusBar = Color(0xffADB4B9),
    selectedTab = Color(0xff00A2EA),
    unselectedTab = Color(0xff7F7F82)
)
val darkImageColors: ImageColors = ImageColors(
    primary = Color(0xff3f51b5),
    primaryDark = Color(0xff303f9f),
    accent = Color(0xffff4081),
    accentSecondary = Color(0xff54b06d),
    textColorPrimary = Color(0xffffffff),
    textColorSecondary = Color(0xff7f7f82),
    textHighlighted = Color(0xff58b5e5),
    background = Color(0xff2b2c35),
    itemBackground = Color(0xff202026),
    dialogWindowBackground = Color(0xff393a47),
    bottomNavBackground = Color(0xff393A47),
    bottomTrayBackground = Color(0xff4C4E5D),
    statusBar = Color(0xff212027),
    selectedTab = Color(0xff3F51B5),
    unselectedTab = Color(0xff7f7f82)
)
