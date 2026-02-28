package template.tab.leads

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.koin.compose.koinInject
import template.data.Media
import template.data.NetApi

object LeadsTab : Tab {
    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(index = 3u, title = "Leads", icon = null)
        }

    @Composable
    override fun Content() {
        val api: NetApi = koinInject()
        var mediaList by remember { mutableStateOf<List<Media>>(emptyList()) }

        LaunchedEffect(Unit) {
            try {
                mediaList = api.getCollection()
            } catch (e: Exception) {
                println(e.message)
            }
        }
        Box(
            modifier = Modifier.fillMaxSize()
                .background(color = MaterialTheme.colorScheme.tertiaryContainer),
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(mediaList) { i ->
                    when (i) {
                        is Media.Photo -> {
                            Text(i.src.small)
                        }

                        is Media.Video -> {
                            Text(i.firstVideoLink ?: "No video link")
                        }
                    }
                }
            }
        }
    }
}