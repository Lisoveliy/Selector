package bel.lisoveliy.selector.viewmodels

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import bel.lisoveliy.selector.logic.Data
import bel.lisoveliy.selector.logic.SType
import bel.lisoveliy.selector.logic.Stream

@SuppressLint("MutableCollectionMutableState")
object MainVM {
    var showDeleteDialog by mutableStateOf(false)
    var streamButtons by mutableStateOf(ConvertToStreamButton(Data.Streams))
    //Instance of Addable button
    object ButtonCreateStream{
        var Icon by mutableStateOf(Icons.Default.Add)
        var IconDiscription by mutableStateOf("Add stream")
    }
    fun ConvertToStreamButton(streams: MutableList<Stream>): MutableList<StreamButton>{
        val output: MutableList<StreamButton> = ArrayList()
        streams.forEach {
            output.add(StreamButton(it.title, it.url, it.type, it.id))
        }
        return output
    }
    class StreamButton(title: String, url: String, type: SType, id: Long) : Stream(title, url, type, id){
        fun onClickedRemoved(self: StreamButton)
        {
            Data.DeleteFromStreams(self)
            streamButtons = ConvertToStreamButton(Data.Streams)
        }
        fun onClickedEdited(self: StreamButton)
        {

        }
//        fun onClicked(/*self: StreamButton*/)
//        {
//
//        }
    }
}
