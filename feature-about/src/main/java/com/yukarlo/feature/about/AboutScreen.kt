package com.yukarlo.feature.about

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.statusBarsPadding

@Composable
internal fun AccountScreen(navController: NavController) {

    BackHandler {
        navController.popBackStack()
    }

    Box(
        modifier = Modifier.statusBarsPadding(),
    ) {
        Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 12.dp)) {
            Row(modifier = Modifier.padding(bottom = 36.dp)) {
                Text(
                    text = "About this app",
                    style = MaterialTheme.typography.headlineSmall,
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
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(
                text = "This is an attempt to create an app with some of the latest tech stack and using purely of jetpack compose. Discover popular and trending anime.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(
                text = "API Source: AniList",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutPreview() {
    AccountScreen(rememberNavController())
}
