package com.ppoppi.house.ui.main.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.Gray200
import com.ppoppi.house.ui.theme.Gray400
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.White

@Composable
fun MainBottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val items =
        listOf(
            BottomNavItem.Home,
            BottomNavItem.Diary,
            BottomNavItem.Map,
            BottomNavItem.Setting,
        )

    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .background(White)
                .navigationBarsPadding(),
    ) {
        HorizontalDivider(
            thickness = 1.dp,
            color = Gray100,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items.forEach { item ->
                val selected = currentRoute == item.screenRoute
                val color = if (selected) Gray400 else Gray200
                val interactionSource = remember { MutableInteractionSource() }

                Column(
                    modifier =
                        Modifier
                            .weight(1f)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                            ) {
                                navController.navigate(item.screenRoute) {
                                    navController.graph.startDestinationRoute?.let {
                                        popUpTo(it) { saveState = true }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }.padding(vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = stringResource(item.title),
                        modifier = Modifier.size(24.dp),
                        tint = color,
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = stringResource(item.title),
                        style = PpoPpiTheme.typography.label2,
                        color = color,
                    )
                }
            }
        }
    }
}
