package com.image.jm.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.image.jm.navigation.BottomNavigationScreens
import com.image.jm.screens.bookmark.BookmarkScreen
import com.image.jm.screens.bookmark.BookmarkState
import com.image.jm.screens.bookmark.BookmarkViewModel
import com.image.jm.screens.home.HomeScreen
import com.image.jm.screens.home.HomeState
import com.image.jm.screens.home.HomeViewModel
import com.image.jm.theme.ResourceObject.LocalColors

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Search,
        BottomNavigationScreens.Bookmark
    )

    Scaffold(
        bottomBar = {
            MainBottomNavigation(navController, bottomNavigationItems)
        }
    ) { innerPadding ->
        MainScreenNavigation(navController, innerPadding)
    }
}

@Composable
private fun MainBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavigationScreens>,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        backgroundColor = LocalColors.current.bottomNavBackground
    ) {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomTab(modifier, screen, currentRoute, navController)
        }
    }
}

@Composable
private fun MainScreenNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.Search.route
    ) {
        composable(BottomNavigationScreens.Search.route) {
            InitHomeScreen(paddingValues)
        }

        composable(BottomNavigationScreens.Bookmark.route) {
            InitBookmarkScreen(paddingValues)
        }
    }
}


@Composable
private fun InitHomeScreen(
    paddingValues: PaddingValues,
) {
    val viewModel: HomeViewModel = hiltViewModel()

    HomeScreen(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(LocalColors.current.background),
        state = viewModel.homeStateFlow.collectAsStateWithLifecycle(HomeState.OnClear),
        onValueChange = { input ->
            viewModel.setQuery(input)
        },
        bookmarkClickListener = {
            viewModel.updateBookmark(it.thumbnail, it.isBookmark)
        }
    )
}

@Composable
private fun InitBookmarkScreen(
    paddingValues: PaddingValues,
) {
    val viewModel: BookmarkViewModel = hiltViewModel()

    BookmarkScreen(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(LocalColors.current.background),
        state = viewModel.bookmarkStateFlow.collectAsStateWithLifecycle(BookmarkState.OnClear),
        onBookmarkClickListener = {
            viewModel.removeBookmark(it)
        }
    )
}

@Composable
private fun RowScope.BottomTab(
    modifier: Modifier,
    screen: BottomNavigationScreens,
    currentRoute: String?,
    navController: NavHostController,
    iconSize: Dp = 24.dp
) {
    val onClick: () -> Unit = {
        if (currentRoute != screen.route) {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    BottomNavigationItem(
        icon = {
            IconButton(
                modifier = modifier.size(iconSize),
                onClick = onClick
            ) {
                Icon(
                    painterResource(screen.iconId),
                    stringResource(id = screen.titleId),
                    tint = getTabColour(currentRoute == screen.route),
                    modifier = modifier,
                )
            }
        },
        label = {
            Text(
                text = stringResource(id = screen.titleId),
                color = getTabColour(currentRoute == screen.route)
            )
        },
        selected = currentRoute == screen.route,
        onClick = onClick
    )
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
private fun getTabColour(selected: Boolean) =
    if (selected) LocalColors.current.selectedTab
    else LocalColors.current.unselectedTab
