package bel.lisoveliy.selector.models

import java.util.UUID

data class Stream(
    val id: UUID,
    val title: String,
    val url: String,
    val type: StreamType = StreamType.UNKNOWN
) {
    data class Create(
        val title: String,
        val url: String,
        val type: StreamType = StreamType.UNKNOWN
    ) {
        fun create(id: UUID) = Stream(
            id,
            title,
            url,
            type
        )
    }
}
