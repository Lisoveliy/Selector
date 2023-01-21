package bel.lisoveliy.selector.viewmodels

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel
import bel.lisoveliy.selector.services.StreamsService

class StreamsVM(private val service: StreamsService): ViewModel() {
    val  streams = service.streams

    companion object {
        val LocalProvider = compositionLocalOf<StreamsVM> { error("not found StreamsVM") }
        val local: StreamsVM
            @Composable
            get() = LocalProvider.current
    }
}