package bel.lisoveliy.selector.services

import bel.lisoveliy.selector.models.Stream
import bel.lisoveliy.selector.repos.Repo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StreamsService(
    private val repo: Repo
) {
    private val _streams = MutableStateFlow<List<Stream>>(listOf())
    val streams = _streams.asStateFlow()

    suspend fun addStream(stream: Stream): AddStreamResponse {
        //Checks
        _streams.value = _streams.value + stream
        return  AddStreamResponse.OK
    }

    suspend fun check() {}
}

enum class AddStreamResponse {
    OK,
    STREAM_WITH_TITLE_ALREADY_EXISTS,

}