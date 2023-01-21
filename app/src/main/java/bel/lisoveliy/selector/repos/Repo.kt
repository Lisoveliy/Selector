package bel.lisoveliy.selector.repos

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class Repo {
    private val http = HttpClient(CIO)

    suspend fun getStringByUrl(url: String): String? {
        return try {
            http.get(url).bodyAsText()
        } catch (e: Exception) {
            null
        }
    }
}