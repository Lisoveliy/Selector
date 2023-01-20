package bel.lisoveliy.selector.view

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
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
                    .height(170.dp)
                    .padding(20.dp, 20.dp)
            ) {
                Column {
                    Text(text = it.title, fontSize = 30.sp, modifier = Modifier.height(50.dp))
                    Text(text = "URL: ${it.url}", modifier = Modifier.height(20.dp).
                    horizontalScroll(state = ScrollState(0)))
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.Bottom,
                        ) {
                                Text(text = it.type.name)
                                Spacer(modifier = Modifier.weight(1f))
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

