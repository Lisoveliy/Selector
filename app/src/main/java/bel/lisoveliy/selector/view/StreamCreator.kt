package bel.lisoveliy.selector.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import bel.lisoveliy.selector.logic.SType
import bel.lisoveliy.selector.viewmodels.StreamCreatorVM
import bel.lisoveliy.selector.viewmodels.StreamCreatorVM.URLField
import bel.lisoveliy.selector.viewmodels.StreamCreatorVM.TitleField

object StreamCreator {
    @Composable
    fun Render(navController: NavHostController){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Column(
                horizontalAlignment = Alignment.Start) {
                RadioSelector()
                URLField()
                Spacer(modifier = Modifier.padding(10.dp))
                TitleField()
            }
            Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier.fillMaxHeight()) {
                SaveStreamButton(navController)
            }
        }
    }

    @Composable
    fun RadioSelector(){
        Text("Select stream type:", modifier = Modifier.padding(0.dp, 10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = StreamCreatorVM.RadioSelector.SelectedRadio == SType.OTHER,
                onClick = { StreamCreatorVM.RadioSelector.onSelected(SType.OTHER) })
            Text("MP4/MKV/AVI/...")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = StreamCreatorVM.RadioSelector.SelectedRadio == SType.M3U8,
                onClick = { StreamCreatorVM.RadioSelector.onSelected(SType.M3U8) })
            Text("M3U8")
        }
    }
    @Composable
    fun URLField(){
        TextField(
            modifier = Modifier.fillMaxWidth().padding(10.dp, 0.dp),
            value = URLField.value,
            onValueChange = URLField::onValueChange,
            label = { Text(URLField.label) },
            placeholder = { Text(URLField.placeholder) },
            isError = URLField.isError
        )
        if(URLField.isError) {
            Text(
                modifier = Modifier.padding(10.dp, 0.dp),
                text = URLField.errorText,
                color = MaterialTheme.colors.error
            )
        }
    }
    @Composable
    fun TitleField(){
        TextField(
            modifier = Modifier.fillMaxWidth().padding(10.dp, 0.dp),
            value = TitleField.value,
            onValueChange = TitleField::onValueChange,
            label = { Text(TitleField.label) },
            placeholder = { Text(TitleField.placeholder) },
            isError = TitleField.isError
        )
        if(TitleField.isError) {
            Text(
                modifier = Modifier.padding(10.dp, 0.dp),
                text = TitleField.errorText,
                color = MaterialTheme.colors.error
            )
        }
    }
    @Composable
    fun SaveStreamButton(navController: NavHostController){
        Button(
            onClick = {
                    StreamCreatorVM.SaveButton.onPressed()
                    navController.navigate("menu")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp)) {
            Text("Save stream")
        }
    }
}