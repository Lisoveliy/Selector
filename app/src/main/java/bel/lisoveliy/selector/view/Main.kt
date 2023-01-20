package bel.lisoveliy.selector.view

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import bel.lisoveliy.selector.viewmodels.MainVM

object Main {
    private lateinit var remstream: MainVM.StreamButton

    @Composable
    fun Render(navController: NavHostController) {
        ButtonsRenderer()
        ButtonCreateRenderer(navController)
        if (MainVM.showDeleteDialog) {
            AlertDialog(
                onDismissRequest = {
                    MainVM.showDeleteDialog = false
                }, title = {
                    Text(text = "Delete stream?")
                }, text = {
                    Text(text = "Are you sure to delete this stream?")
                }, buttons = {
                    Row(
                        modifier = Modifier.padding(all = 8.dp)
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            modifier = Modifier.padding(8.dp, 0.dp),
                            onClick = {
                                remstream.onClickedRemoved(remstream); MainVM.showDeleteDialog =
                                false
                            }
                        ) {
                            Text("Yes")
                        }
                        Button(
                            modifier = Modifier.padding(8.dp, 0.dp),
                            onClick = { MainVM.showDeleteDialog = false }
                        ) {
                            Text("No")
                        }
                    }
                })
        }
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
                    Text(
                        text = "URL: ${it.url}",
                        modifier = Modifier
                            .height(20.dp)
                            .horizontalScroll(state = ScrollState(0))
                    )
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        Text(text = it.type.name)
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            Icons.Default.Edit,
                            "Edit",
                            modifier = Modifier
                                .clickable(onClick = { it.onClickedEdited(it) })
                        )
                        Icon(
                            Icons.Default.Delete,
                            "Delete",
                            modifier = Modifier
                                .clickable(onClick = {
                                    remstream = it; MainVM.showDeleteDialog = true
                                })
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun ButtonCreateRenderer(navController: NavHostController) {
        Button(
            onClick = { navController.navigate("streamCreator") },
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

