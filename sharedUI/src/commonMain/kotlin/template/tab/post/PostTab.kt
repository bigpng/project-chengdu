package template.tab.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import chaintech.videoplayer.ui.preview.VideoPreviewComposable

object PostTab: Tab {
    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(index = 5u, title = "Post", icon = null)
        }

    @Composable
    override fun Content() {
        Box(modifier = Modifier.fillMaxSize().background(color = Color.Yellow), contentAlignment = Alignment.Center) {
            VideoPreviewComposable(url="https://videos.pexels.com/video-files/34306594/14533629_640_360_30fps.mp4",
                frameCount = 3)

        }
    }
}