package bel.lisoveliy.selector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import bel.lisoveliy.selector.repos.Repo
import bel.lisoveliy.selector.services.StreamsService
import bel.lisoveliy.selector.ui.theme.AppTheme
import bel.lisoveliy.selector.ui.view.screens.MainScreen
import bel.lisoveliy.selector.ui.view.screens.StreamCreateScreen
import bel.lisoveliy.selector.ui.view.screens.StreamEditScreen
import bel.lisoveliy.selector.viewmodels.NavVM
import bel.lisoveliy.selector.viewmodels.StreamCreateVM
import bel.lisoveliy.selector.viewmodels.StreamEditVM
import bel.lisoveliy.selector.viewmodels.StreamsVM


class MainActivity : ComponentActivity() {
    private val repo = Repo()

    private val streamsService = StreamsService(repo)

    private val navVM by lazy { NavVM(this) }
    private val streamsVM by lazy { StreamsVM() }
    private val streamCreateVM by lazy { StreamCreateVM(streamsService) }
    private val streamEditVM by lazy { StreamEditVM() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                CompositionLocalProvider(
                    NavVM.LocalProvider provides navVM,
                    StreamsVM.LocalProvider provides streamsVM,
                    StreamCreateVM.LocalProvider provides streamCreateVM,
                    StreamEditVM.LocalProvider provides streamEditVM
                ) {
                    Surface {
                        NavContainer(vm = navVM)
                    }
                }
            }
        }
    }
}

@Composable
fun NavContainer(vm: NavVM) {
    NavHost(
        navController = vm.navController,
        startDestination = vm.currentOption.path
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