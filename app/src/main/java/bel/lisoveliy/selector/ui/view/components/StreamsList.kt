package bel.lisoveliy.selector.ui.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bel.lisoveliy.selector.models.Stream
import java.util.*

@Composable
fun StreamsList(streams: List<Stream>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(streams) {
            StreamView(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val stream = Stream(UUID.randomUUID(), "Stream title", "url")
    val list = listOf(stream, stream, stream, stream, stream)

    StreamsList(list, modifier = Modifier.padding(24.dp))
}