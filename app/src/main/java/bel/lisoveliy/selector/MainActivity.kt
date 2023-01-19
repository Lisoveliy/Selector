package bel.lisoveliy.selector

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import bel.lisoveliy.selector.logic.Data
import bel.lisoveliy.selector.ui.theme.SelectorTheme
import bel.lisoveliy.selector.viewmodels.MainVM

class MainActivity : ComponentActivity() {

    lateinit var navController : NavHostController
    @SuppressLint("SourceLockedOrientationActivity")//DEBUG FEATURE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            val context = LocalContext.current
            //Orientation lock
            (context as? Activity)?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            Data.InitStreams(context)
            //NavHost
            NavHost(navController = navController, startDestination = "menu")
            {
                composable("menu"){
                    MainRenderer()
                }
                composable("otherNav"){
                    Renderer2()
                }
            }
        }
    }
    @Composable
    fun Renderer2(){
        SelectorTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ){
                Text("Other Nav")
            }
        }
    }
    @Composable
    @Preview
    fun MainRenderer()
    {
        SelectorTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.verticalScroll(ScrollState(0))
                ){
                    ButtonsRenderer()
                    ButtonCreateRenderer()
                }
            }
        }
    }

    @Composable
    private fun ButtonsRenderer(){
        MainVM.UpdateState
        MainVM.streamButtons.forEach {
            Button(
                onClick = { navController.navigate("otherNav")},
                modifier = Modifier
                    .width(300.dp)
                    .height(150.dp)
                    .padding(20.dp, 20.dp)
            ) {
                Text(text="ID: ${it.id.toString()}")
                Icon(
                    it.Icon,
                    it.IconDiscription,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .clickable(onClick = { it.onClicked(it) }))
            }
        }
    }

    @Composable
    private fun ButtonCreateRenderer(){
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
                    modifier = Modifier.fillMaxSize())
        }
    }
}