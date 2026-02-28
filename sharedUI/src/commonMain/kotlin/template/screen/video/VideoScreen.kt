package template.screen.video

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import template.component.NetVideoPlayerScreenModel
import template.component.NetworkVideoPlayer


data class VideoScreen(val url: String) : Screen {

    override val key: String = url

    @Composable
    override fun Content() {
        val videoSm = koinScreenModel<NetVideoPlayerScreenModel>()
        val navigator = LocalNavigator.currentOrThrow
        val url2 = "https://videos.pexels.com/video-files/34306594/14533629_640_360_30fps.mp4"
        val playerHost = videoSm.playerHost


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (playerHost != null && !videoSm.isLoading) {
                LaunchedEffect(url) {
                    playerHost.loadUrl(url)
                }
                NetworkVideoPlayer(playerHost)
            }
            Button(onClick = { navigator.push(VideoScreen(url2)) }) {
                Text("New video")
            }
            Button(onClick = { navigator.pop() }) {
                Text("Back")
            }
        }
    }
}