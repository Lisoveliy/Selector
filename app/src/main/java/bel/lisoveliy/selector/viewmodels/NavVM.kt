package bel.lisoveliy.selector.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel

class NavVM: ViewModel() {
    var currentOption by mutableStateOf(NavOption.MAIN)
        private set

    fun navigateTo(option: NavOption) {
        currentOption = option
    }

    enum class NavOption(val path: String, val Icon: ImageVector, val displayName: String) {
        MAIN("/", Icons.Default.Home, "Главная"),
        STREAM_CREATE("/stream/create", Icons.Default.Add, "Создать"),
        STREAM_EDIT("/stream/edit", Icons.Default.Edit, "Изменить"),
        STREAMS_LIST("/streams", Icons.Default.List, "Стримы")
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