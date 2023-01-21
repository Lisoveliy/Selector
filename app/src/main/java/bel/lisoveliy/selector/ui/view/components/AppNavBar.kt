package bel.lisoveliy.selector.ui.view.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import bel.lisoveliy.selector.viewmodels.NavVM

@Composable
fun AppNavBar(vm: NavVM) {
    BottomNavigation {
        listOf(NavVM.NavOption.MAIN, NavVM.NavOption.STREAMS_LIST).forEach { screen ->
            BottomNavigationItem(
                selected = vm.currentOption == screen,
                onClick = { vm.navigateTo(screen) },
                label = { Text(screen.displayName) },
                icon = { Icon(screen.Icon, screen.displayName) },
                alwaysShowLabel = false,
            )
        }
    }
}