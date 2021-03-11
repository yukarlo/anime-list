package com.yukarlo.feature.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yukarlo.anime.common.android.navigation.BackHandler
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun AccountScreen(onBack: () -> Unit) {
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
    ) {
        Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 12.dp)) {
            Row(modifier = Modifier.padding(bottom = 36.dp)) {
                Text(
                    text = "About this app",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .weight(weight = 1f)
                        .wrapContentWidth(align = Alignment.Start)
                )
                Image(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Information",
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .weight(weight = 1f)
                        .wrapContentWidth(align = Alignment.End)
                )
            }
            Text(
                text = "This app is created by Karlo Yu",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(
                text = "This is an attempt to create an app with some of the latest tech stack and using purely of jetpack compose. Discover popular and trending anime.",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(
                text = "API Source: AniList",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
    }

    BackHandler(onBack = {
        onBack()
    })
}

@Preview(showBackground = true)
@Composable
fun AboutPreview() {
    AccountScreen(onBack = { })
}
