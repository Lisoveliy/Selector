package bel.lisoveliy.selector.viewmodels

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController

class NavVM(context: Context) {
    val navController = NavHostController(context)

    var currentOption by mutableStateOf(NavOption.MAIN)
        private set

    fun navigateTo(option: NavOption) {
        navController.navigate(option.path)
        currentOption = option
    }

    enum class NavOption(val path: String, val Icon: ImageVector) {
        MAIN("/", Icons.Default.Home),
        STREAM_CREATE("/stream/create", Icons.Default.Add),
        STREAM_EDIT("/stream/edit", Icons.Default.Edit)
    }

    companion object {
        val LocalProvider = compositionLocalOf<NavVM> { error("not found NavVM") }
        @Composable
        fun navigateTo(option: NavOption) {
            val vm = LocalProvider.current
            vm.navigateTo(option)
        }
    }
}