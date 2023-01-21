package bel.lisoveliy.selector.viewmodels

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import bel.lisoveliy.selector.services.StreamsService

class StreamCreateVM(private val service: StreamsService): ViewModel() {
    var url by mutableStateOf("")
        private set
    var title by mutableStateOf("")
        private set

    companion object {
        val LocalProvider = compositionLocalOf<StreamCreateVM> { error("not found StreamCreateVM") }
    }
}