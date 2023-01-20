package bel.lisoveliy.selector

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import bel.lisoveliy.selector.logic.Data
import bel.lisoveliy.selector.logic.SType
import bel.lisoveliy.selector.logic.Stream
import bel.lisoveliy.selector.ui.theme.SelectorTheme
import bel.lisoveliy.selector.view.Main
import bel.lisoveliy.selector.view.StreamCreator
import bel.lisoveliy.selector.view.StreamEditor

class MainActivity : ComponentActivity() {
    private lateinit var navController : NavHostController
    @SuppressLint("SourceLockedOrientationActivity")//DEBUG FEATURE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            val context = LocalContext.current
            //Orientation lock
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            Data.InitStreams(context)
            //NavHost
            NavHost(navController = navController, startDestination = "menu")
            {
                composable("menu"){
                    MainRenderer()
                }
                composable("streamCreator"){
                    StreamCreatorRenderer()
                }
                composable("streamEditor"){
                    StreamEditorRenderer()
                }
            }
        }
    }
    @Composable
    fun StreamEditorRenderer() {
        SelectorTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                StreamEditor.Render(navController)
            }
        }
    }
    @Composable
    fun StreamCreatorRenderer() {
        SelectorTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                StreamCreator.Render(navController)
            }
        }
    }

    @Composable
    fun MainRenderer() {
        SelectorTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.verticalScroll(ScrollState(0))
                ) {
                    Main.Render(navController)
                }
            }
        }
    }
    @Preview
    @Composable
    fun MainPreview(){
        SelectorTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                Data.Streams.add(Stream("Hello world", "https://url", SType.OTHER, System.currentTimeMillis()))
                Data.Streams.add(Stream("Hello world2", "https://url2", SType.M3U8, System.currentTimeMillis()))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.verticalScroll(ScrollState(0))
                ) {
                    navController = rememberNavController()
                    Main.Render(navController)
                }
            }
        }
    }
}