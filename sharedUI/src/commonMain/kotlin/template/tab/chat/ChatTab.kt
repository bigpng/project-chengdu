package template.tab.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.yield
import template.component.NetVideoPlayerScreenModel

object ChatTab : Tab {
    override val options: TabOptions
        @Composable get() = remember {
            TabOptions(index = 0u, title = "Contacts", icon = null)
        }

    @Composable
    override fun Content() {
        val sm = koinScreenModel<NetVideoPlayerScreenModel>()
        val url = "https://videos.pexels.com/video-files/34149284/14478852_960_540_30fps.mp4"
        LaunchedEffect(Unit) {
            coroutineScope {
                yield()
            }
            sm.loadPlayer(url.trim())
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            when {
//                sm.isLoading || sm.playerHost == null -> {
//                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                        Text("Loading player...", style = MaterialTheme.typography.bodySmall)
//                    }
//                }
//                else -> {
//
//                    NetworkVideoPlayer(sm.playerHost!!)
//                }
//            }


        }
    }
}