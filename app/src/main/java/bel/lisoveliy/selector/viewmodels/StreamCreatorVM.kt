package bel.lisoveliy.selector.viewmodels

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import bel.lisoveliy.selector.MainActivity
import bel.lisoveliy.selector.logic.Data
import bel.lisoveliy.selector.logic.SType
import bel.lisoveliy.selector.logic.Stream

object StreamCreatorVM {
    var URLField = Field(placeholder = "Enter URL for stream", label = "URL")
    var TitleField = Field(placeholder = "Enter title of stream", label = "Title")

    class Field(value: String = "",
                label : String = "",
                placeholder : String = "",
                isError : Boolean = false,
                errorText : String = "error"
    ){
        var value by mutableStateOf(value)
        var label by mutableStateOf(label)
        var placeholder by mutableStateOf(placeholder)
        var isError by mutableStateOf(isError)
        var errorText by mutableStateOf(errorText)

        fun onValueChange(value: String){
            this.value = value
        }
        fun setError(state: Boolean, value: String){
            isError = state
            errorText = value
        }
        @JvmName("setError1")
        fun setError(state: Boolean){
            isError = state
        }
    }

    object RadioSelector{
        var SelectedRadio by mutableStateOf(SType.M3U8)
        fun onSelected(type: SType){
            SelectedRadio = type
        }
    }
    object SaveButton{
        //@Composable
        fun onPressed(){
            var stream = Stream(
                title = TitleField.value,
                url = URLField.value,
                type = RadioSelector.SelectedRadio,
                id = System.currentTimeMillis()
            )
            Data.AddToStreams(stream)
            MainVM.streamButtons = MainVM.ConvertToStreamButton(Data.Streams)
        }
    }
}