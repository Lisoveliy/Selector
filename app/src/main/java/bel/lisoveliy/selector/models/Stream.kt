package bel.lisoveliy.selector.models

data class Stream(
    val title: String,
    val url: String,
    val type: StreamType = StreamType.UNKNOWN
)
