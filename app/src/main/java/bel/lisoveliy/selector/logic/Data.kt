package bel.lisoveliy.selector.logic

import android.content.Context
import android.icu.util.TimeUnit
import android.util.Log
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import java.io.File
import java.time.Clock
import kotlin.random.Random

object Data{
    var Streams: MutableList<Stream> = ArrayList()
    lateinit var directory: String
    fun InitStreams(context: Context)
    {
        directory = context.getDir("", Context.MODE_PRIVATE).parentFile!!.path
        val file = File(directory + "/streams.json")
        if(file.exists())
            Streams = Json.decodeFromString(file.readText())
        Log.v("Data: InitStreams", "Streams inited: ${Streams.size}")
    }
    fun AddToStreams(value: Stream)
    {
        Streams.add(value)
        Log.v("Data: AddToStreams", "Stream added. Streams size: ${Streams.size}")
        SaveStreams()
    }
    fun DeleteFromStreams(element: Stream)
    {
        Streams.removeIf {
            it.id == element.id
        }
        Log.v("Data: DeleteFromStreams", "Stream removed. Streams size: ${Streams.size}")
        SaveStreams()
    }
    fun SaveStreams()
    {
        val file = File(directory + "/streams.json")
        file.writeText(Json.encodeToString(Streams))
    }
}

//Streams
@Serializable
open class Stream(val title: String, val url: String, val type: SType, val id: Long)

enum class SType{
    M3U8, OTHER
}