package bel.lisoveliy.selector.services

import bel.lisoveliy.selector.models.Stream
import bel.lisoveliy.selector.repos.Repo
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class StreamsService(
    private val repo: Repo
) {
    private val http = HttpClient(CIO)

    private val _streams = MutableStateFlow<List<Stream>>(listOf())
    val streams = _streams.asStateFlow()

    suspend fun addStream(stream: Stream.Create): AddStreamResponse {
        //Checks
        _streams.value = _streams.value + stream.create(UUID.randomUUID())
        return  AddStreamResponse.OK
    }

    suspend fun check() {}
}

enum class AddStreamResponse {
    OK,
    STREAM_WITH_TITLE_ALREADY_EXISTS,

}