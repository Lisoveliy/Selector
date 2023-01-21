package bel.lisoveliy.selector.repos

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class Repo {
    private val http = HttpClient(CIO)

    suspend fun check3u8Url(url: String): Boolean {
        return try {
            val res = http.get(url)
            res.bodyAsText().contains("#EXTM3U")
        } catch (e: Exception) {
            false
        }
    }
}