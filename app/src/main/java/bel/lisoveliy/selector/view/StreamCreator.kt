package bel.lisoveliy.selector.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

object StreamCreator {
    @Composable
    fun Render(){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TextField(
                value = "",
                onValueChange = {},
                label = {Text("URL")},
                placeholder = {Text("URL")})
        }
    }
}