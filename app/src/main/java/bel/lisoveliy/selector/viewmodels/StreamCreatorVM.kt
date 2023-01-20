package bel.lisoveliy.selector.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import bel.lisoveliy.selector.logic.Checks
import bel.lisoveliy.selector.logic.Data
import bel.lisoveliy.selector.logic.SType
import bel.lisoveliy.selector.logic.Stream

object StreamCreatorVM {
    // TODO: Make checks for creating title
    val URLField = Field(placeholder = "Enter URL for stream", label = "URL"
    ) { string: String, field: Field ->
        Thread {
            MakeChecks()
        }.start()
    }
    val TitleField = Field(placeholder = "Enter title of stream", label = "Title")
    { string: String, field: Field ->
        Thread {
            MakeChecks()
        }.start()
    }

    class Field(value: String = "",
                label : String = "",
                placeholder : String = "",
                isError : Boolean = false,
                errorText : String = "error",
                onvaluedelegate: (String, Field) -> Unit = { _: String, _: Field ->  }
    )
    {
        var value by mutableStateOf(value)
        var label by mutableStateOf(label)
        var placeholder by mutableStateOf(placeholder)
        var isError by mutableStateOf(isError)
        var errorText by mutableStateOf(errorText)
        var onValueDelegate = onvaluedelegate

        fun onValueChange(value: String){
            this.value = value
            onValueDelegate(value, this)
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
            val stream = Stream(
                title = TitleField.value,
                url = URLField.value,
                type = RadioSelector.SelectedRadio,
                id = System.currentTimeMillis()
            )
            Data.AddToStreams(stream)
            MainVM.streamButtons = MainVM.ConvertToStreamButton(Data.Streams)
        }
    }
    fun MakeChecks(): Boolean{
        var urlCheck = false
        val titleCheck: Boolean
        //URLCheck START
        when (RadioSelector.SelectedRadio) {
            SType.M3U8 -> {
                    if (!Checks.checkm3u8url(URLField.value)) {
                        URLField.setError(true, "Invalid m3u8")
                        urlCheck = false
                    } else {
                        URLField.setError(false)
                        urlCheck = true
                    }
            }
            SType.OTHER -> {
                URLField.setError(true, "Other types unchecked")
                urlCheck = true
            }
        }
        //URLCheck END
        //TitleCheck START
        if(TitleField.value == "")
        {
            TitleField.isError = true
            TitleField.errorText = "Title can't be empty"
            titleCheck = false
        }else {
            TitleField.isError = false
            titleCheck = true
        }
        //TitleCheck END
        return titleCheck && urlCheck
    }
}