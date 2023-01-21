package bel.lisoveliy.selector.viewmodels

import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel

class StreamEditVM: ViewModel() {

    companion object {
        val LocalProvider = compositionLocalOf<StreamEditVM> { error("not found StreamEditVM") }
    }
}