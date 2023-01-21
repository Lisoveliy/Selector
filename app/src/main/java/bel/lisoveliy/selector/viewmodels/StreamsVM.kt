package bel.lisoveliy.selector.viewmodels

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel

class StreamsVM: ViewModel() {
    companion object {
        val LocalProvider = compositionLocalOf<StreamsVM> { error("not found StreamsVM") }
        val local: StreamsVM
            @Composable
            get() = LocalProvider.current
    }
}