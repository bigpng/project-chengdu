package template.tab.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import chaintech.videoplayer.ui.preview.VideoPreviewComposable
import template.screen.home.HomeScreen
import template.screen.video.VideoScreen

object ProfileTab : Tab {
    override val options: TabOptions
        @Composable get() = remember {
            TabOptions(index = 6u, title = "Profile", icon = null)
        }

    @Composable
    override fun Content() {
        val nav = LocalNavigator.currentOrThrow.parent
        val url1 = "https://videos.pexels.com/video-files/34185153/14490821_640_360_50fps.mp4"
        val url1a = "https://kou.my/asset/video/junjie.mp4"
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Box(contentAlignment = Alignment.Center) {
                VideoPreviewComposable(
                    url = url1a,
                    frameCount = 2,
                    contentScale = ContentScale.Crop
                )
                Column() {
                    Button(onClick = { nav?.push(VideoScreen(url1a)) }) {
                        Text("Load video")
                    }
                    Button(onClick = { nav?.push(HomeScreen()) }) { Text("Go to HomeScreen") }
                }
            }
        }
    }
}