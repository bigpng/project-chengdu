package template.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import chaintech.videoplayer.host.MediaPlayerHost
import chaintech.videoplayer.model.PlayerSpeed
import chaintech.videoplayer.model.ScreenResize
import chaintech.videoplayer.model.VideoPlayerConfig
import chaintech.videoplayer.ui.video.VideoPlayerComposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun NetworkVideoPlayer(playerHost: MediaPlayerHost) {
    val playerConfig = VideoPlayerConfig(isPauseResumeEnabled = false, isSeekBarVisible = true,
        isDurationVisible = false, seekBarBottomPadding = 0.dp, controlHideIntervalSeconds = 1,
        isFastForwardBackwardEnabled = false, isSpeedControlEnabled = false, backdropAlpha = 0.0f,
        enablePIPControl = false, isGestureVolumeControlEnabled = false, isFullScreenEnabled = false,
        isScreenLockEnabled = false, showVideoQualityOptions = false, showSubTitlesOptions = false,
        showAudioTracksOptions = false, autoPlayNextReel = false, iconsTintColor = Color.White.copy(alpha = 0.5f),
        isAutoHideControlEnabled = false, seekBarThumbColor = Color.Magenta, enableFullEdgeToEdge = false,
        topControlSize = 0.dp, seekBarActiveTrackColor = Color.White.copy(alpha = 0.5f),
        seekBarInactiveTrackColor = Color.Transparent)

    Box(modifier= Modifier.fillMaxWidth().aspectRatio(9f/16f), contentAlignment = Alignment.TopCenter) {
        VideoPlayerComposable(modifier = Modifier.matchParentSize(), playerHost = playerHost,
            playerConfig = playerConfig)
        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.75f).background(Color.Transparent).align(Alignment.TopCenter)
            .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null, onClick = {
                playerHost.toggleMuteUnmute()
            }))
    }
}

class NetVideoPlayerScreenModel : ScreenModel {
    private var currentUrl: String? = null
    var playerHost: MediaPlayerHost? by mutableStateOf(null)
        private set
    var isLoading by mutableStateOf(false)
        private set

    fun initializePlayer() {
        if (playerHost != null) return
        playerHost = MediaPlayerHost(
            mediaUrl = "",
            autoPlay = true,
            isMuted = false,
            initialSpeed = PlayerSpeed.X1,
            initialVideoFitMode = ScreenResize.FIT,
            isLooping = true,
            startTimeInSeconds = null,
            isFullScreen = false
        )
    }

    fun loadPlayer(url: String) {
        if (currentUrl == url) return
        currentUrl = url
        isLoading = true

        screenModelScope.launch {
            if (playerHost == null) {
                withContext(Dispatchers.Main) {
                    initializePlayer()
                }
            }
            playerHost?.loadUrl(url)
            isLoading = false
        }
    }

    override fun onDispose() {
//        playerHost?.resetMetadata()
//        playerHost = null
    }
}

