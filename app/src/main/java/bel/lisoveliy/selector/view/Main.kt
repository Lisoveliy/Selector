package bel.lisoveliy.selector.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bel.lisoveliy.selector.viewmodels.MainVM

object Main {

    @Composable
    fun Render(){
        Main.ButtonsRenderer()
        Main.ButtonCreateRenderer()
    }
    @Composable
    private fun ButtonsRenderer() {
        MainVM.streamButtons.forEach {
            Button(
                onClick = {},
                modifier = Modifier
                    .width(300.dp)
                    .height(150.dp)
                    .padding(20.dp, 20.dp)
            ) {
                Text(text = "ID: ${it.id.toString()}")
                Icon(
                    it.Icon,
                    it.IconDiscription,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .clickable(onClick = { it.onClicked(it) })
                )
            }
        }
    }

    @Composable
    private fun ButtonCreateRenderer() {
        Button(
            onClick = MainVM.ButtonCreateStream::onClicked,
            modifier = Modifier
                .width(300.dp)
                .height(150.dp)
                .padding(20.dp, 20.dp)
        ) {
            Icon(
                MainVM.ButtonCreateStream.Icon,
                MainVM.ButtonCreateStream.IconDiscription,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}