package bel.lisoveliy.selector.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import bel.lisoveliy.selector.logic.Data
import bel.lisoveliy.selector.logic.SType
import bel.lisoveliy.selector.logic.Stream

object MainVM {
    var streamButtons by mutableStateOf(ConvertToStreamButton(Data.Streams))
    //Instance of Addable button
    object ButtonCreateStream{
        var Icon by mutableStateOf(Icons.Default.Add)
        var IconDiscription by mutableStateOf("Add stream")
        fun onClicked()
        {
            Data.AddToStreams()
            streamButtons = ConvertToStreamButton(Data.Streams)
        }
    }
    fun ConvertToStreamButton(streams: MutableList<Stream>): MutableList<StreamButton>{
        val output: MutableList<StreamButton> = ArrayList()
        streams.forEach {
            output.add(StreamButton(it.title, it.url, it.type, it.id))
        }
        return output
    }
    class StreamButton(title: String, url: String, type: SType, id: Long) : Stream(title, url, type, id){
        var Icon by mutableStateOf(Icons.Default.Delete)
        var IconDiscription by mutableStateOf("Add stream")
        fun onClicked(self: StreamButton)
        {
            Data.DeleteFromStreams(self)
            streamButtons = ConvertToStreamButton(Data.Streams)
        }
    }
}
