package bel.lisoveliy.selector.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import bel.lisoveliy.selector.logic.Checks
import bel.lisoveliy.selector.logic.Data
import bel.lisoveliy.selector.logic.SType
import bel.lisoveliy.selector.logic.Stream

object StreamEditorVM {
    var editableElement: MainVM.StreamButton by mutableStateOf(MainVM.StreamButton("", "", SType.M3U8, 0))
    fun PrepareFields(){
        URLField = Field(value = editableElement.url, placeholder = "Enter URL for stream", label = "URL"
        ) { string: String, field: Field ->
            //TODO: Change to corutine
            Thread {
                MakeChecks()
            }.start()
        }
        TitleField = Field(value = editableElement.title, placeholder = "Enter title of stream", label = "Title")
        { string: String, field: Field ->
            //TODO: Change to corutine
            Thread {
                MakeChecks()
            }.start()
        }
    }
    var URLField = Field(value = editableElement.url, placeholder = "Enter URL for stream", label = "URL"
    ) { string: String, field: Field ->
        //TODO: Change to corutine
        Thread {
            MakeChecks()
        }.start()
    }
    var TitleField = Field(value = editableElement.title, placeholder = "Enter title of stream", label = "Title")
    { string: String, field: Field ->
        //TODO: Change to corutine
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
        var SelectedRadio by mutableStateOf(editableElement.type)
        fun onSelected(type: SType){
            SelectedRadio = type
        }
    }
    object EditButton{
        //@Composable
        fun onPressed(){
            val previousid = editableElement.id

            val stream = Stream(
                title = TitleField.value,
                url = URLField.value,
                type = RadioSelector.SelectedRadio,
                id = editableElement.id
            )
            Data.EditStream(stream)
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