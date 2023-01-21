package bel.lisoveliy.selector.ui.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bel.lisoveliy.selector.models.Stream
import java.util.*

@Composable
fun StreamView(stream: Stream) {
    Card(elevation = 6.dp) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(stream.title)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val stream = Stream(UUID.randomUUID(), "Stream title", "http://localhost:8080/path")
    Box(modifier = Modifier.padding(12.dp)) {
        StreamView(stream = stream)
    }
}