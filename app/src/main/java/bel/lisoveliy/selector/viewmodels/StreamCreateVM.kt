package bel.lisoveliy.selector.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bel.lisoveliy.selector.models.Stream
import bel.lisoveliy.selector.models.StreamType
import bel.lisoveliy.selector.services.AddStreamResponse
import bel.lisoveliy.selector.services.StreamsService
import kotlinx.coroutines.launch

class StreamCreateVM(private val service: StreamsService): ViewModel() {
    var url by mutableStateOf("")
    var urlError by mutableStateOf<String?>(null)
        private set

    var title by mutableStateOf("")
    var titleError by mutableStateOf<String?>(null)

    var type by mutableStateOf(StreamType.M3U8)

    var creating by mutableStateOf(false)
        private set

    fun create() {
        if (creating) return
        viewModelScope.launch {
            creating = true

            val stream = Stream(
                title = title,
                url = url,
                type = type)

            when(service.addStream(stream)) {
                AddStreamResponse.OK -> clear()
                AddStreamResponse.STREAM_WITH_TITLE_ALREADY_EXISTS -> {
                    TODO()
                }
            }

            creating = false
        }
    }

    fun clear() {
        url = ""
        urlError = null

        title = ""
        titleError = null

        type = StreamType.M3U8
    }

    companion object {
        val LocalProvider = compositionLocalOf<StreamCreateVM> { error("not found StreamCreateVM") }
        val local: StreamCreateVM
            @Composable
            get() = LocalProvider.current
    }
}