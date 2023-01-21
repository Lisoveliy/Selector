package bel.lisoveliy.selector

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import bel.lisoveliy.selector.repos.Repo
import bel.lisoveliy.selector.services.StreamsService
import bel.lisoveliy.selector.ui.theme.AppTheme
import bel.lisoveliy.selector.ui.view.components.AppNavBar
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

    private val navVM by lazy { NavVM() }
    private val streamsVM by lazy { StreamsVM(streamsService) }
    private val streamCreateVM by lazy { StreamCreateVM(streamsService) }
    private val streamEditVM by lazy { StreamEditVM() }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
                    Scaffold(
                        floatingActionButton = { AppFloatActionButton(navVM) },
                        bottomBar = { AppNavBar(vm = navVM) },
                        floatingActionButtonPosition = FabPosition.Center,
                        isFloatingActionButtonDocked = true
                    ) {
                        NavContainer(vm = navVM)
                    }
                }
            }
        }
    }
}

@Composable
fun NavContainer(vm: NavVM) {
    val navController = rememberNavController()
    val currentOption = vm.currentOption
    LaunchedEffect(currentOption) {
        navController.navigate(currentOption.path)
    }

    NavHost(
        navController = navController,
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
        composable("/streams") {
            Text("Streams")
        }
    }
}

@Composable
fun AppFloatActionButton(vm: NavVM) {
    val density = LocalDensity.current

    AnimatedVisibility(
        vm.currentOption == NavVM.NavOption.STREAMS_LIST,
        enter = slideInVertically { with(density) { 40.dp.roundToPx() } },
        exit = slideOutVertically { (it * 1.5).toInt() }
    ) {
        FloatingActionButton(onClick = {}) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add stream")
        }
    }
}
