package bel.lisoveliy.selector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import bel.lisoveliy.selector.ui.theme.AppTheme
import bel.lisoveliy.selector.ui.view.screens.MainScreen
import bel.lisoveliy.selector.ui.view.screens.StreamCreateScreen
import bel.lisoveliy.selector.ui.view.screens.StreamEditScreen
import bel.lisoveliy.selector.viewmodels.NavVM


class MainActivity : ComponentActivity() {
    private val navVM by lazy { NavVM(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                CompositionLocalProvider(
                    NavVM.LocalProvider provides navVM
                ) {
                    NavHost(
                        navController = navVM.navController,
                        startDestination = navVM.currentOption.path
                    ) {
                        composable(NavVM.NavOption.MAIN.path) {
                            MainScreen()
                        }
                        composable(NavVM.NavOption.STREAM_CREATE.path) {
                            StreamCreateScreen()
                        }
                        composable(NavVM.NavOption.STREAM_EDIT.path) {
                            StreamEditScreen()
                        }
                    }
                }
            }
        }
    }
}
