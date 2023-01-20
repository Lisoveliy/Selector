package bel.lisoveliy.selector.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import bel.lisoveliy.selector.viewmodels.MainVM

object Main {
    @Composable
    fun Render(navController: NavHostController){
        ButtonsRenderer()
        ButtonCreateRenderer(navController)
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
                Column {
                    Text(text = "Title: ${it.title}")
                    Text(text = "URL: ${it.url}")
                    Text(text = "ID: ${it.id}")
                    Text(text = "Type: ${it.type.name}")
                    Row (
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()) {
                        Icon(
                            it.Icon,
                            it.IconDiscription,
                            modifier = Modifier
                                .clickable(onClick = { it.onClicked(it) })
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun ButtonCreateRenderer(navController : NavHostController) {
        Button(
            onClick = { navController.navigate("streamCreator")  },
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