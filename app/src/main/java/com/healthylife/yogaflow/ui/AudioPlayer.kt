package com.healthylife.yogaflow.ui

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.ui.PlayerView
import com.healthylife.yogaflow.provideExoPlayer
import com.healthylife.yogaflow.ui.theme.YogaFlowTheme

@Composable
fun AudioPlayer() {
    val context = LocalContext.current
    val playerView = PlayerView(context)
    val uri =
        Uri.parse("https://cdn.freecodecamp.org/curriculum/js-music-player/scratching-the-surface.mp3")
    val mediaItem = MediaItem.fromUri(uri)
    val player = provideExoPlayer(context = context, mediaItem = mediaItem)
    playerView.player = player
    LaunchedEffect(player) {
        player.prepare()
        player.playWhenReady = false
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(modifier = Modifier.weight(1f).padding(top = 10.dp),
            factory = {playerView})
        Column(
            modifier = Modifier.fillMaxWidth().weight(2f)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 32.dp, end = 32.dp),
                onClick = {
                    player.playWhenReady = true
                }
            ) {
                Text(text = "Start")
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 32.dp, end = 32.dp),
                onClick = {
                    player.playWhenReady = false
                }
            ) {
                Text(text = "Stop")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RadioScreenPreview() {
    YogaFlowTheme {
        AudioPlayer()
    }
}