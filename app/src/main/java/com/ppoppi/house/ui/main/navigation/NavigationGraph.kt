package com.ppoppi.house.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ppoppi.house.R
import com.ppoppi.house.ui.main.diary.DiaryScreen
import com.ppoppi.house.ui.main.home.HomeScreen
import com.ppoppi.house.ui.main.map.MapScreen
import com.ppoppi.house.ui.main.setting.SettingScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    navigateToDiagnosis: () -> Unit,
    startRoute: String = HOME,
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navController, startDestination = startRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen(navigateToDiagnosis, modifier)
        }
        composable(BottomNavItem.Diary.screenRoute) {
            DiaryScreen(modifier)
        }
        composable(BottomNavItem.Map.screenRoute) {
            MapScreen(modifier)
        }
        composable(BottomNavItem.Setting.screenRoute) {
            SettingScreen(modifier)
        }
    }
}

sealed class BottomNavItem(
    val title: Int,
    val icon: Int,
    val screenRoute: String,
) {
    object Home : BottomNavItem(R.string.bottom_nav_home, R.drawable.ic_bot_nav_home, HOME)

    object Diary : BottomNavItem(R.string.bottom_nav_diary, R.drawable.ic_bot_nav_diary, DIARY)

    object Map : BottomNavItem(R.string.bottom_nav_map, R.drawable.ic_bot_nav_map, MAP)

    object Setting : BottomNavItem(R.string.bottom_nav_setting, R.drawable.ic_bot_nav_setting, SETTING)
}

const val HOME = "HOME"
const val DIARY = "DIARY"
const val MAP = "MAP"
const val SETTING = "SETTING"
