package com.paynl.pos.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.paynl.pos.R
import com.paynl.pos.ui.viewModel.SplashViewModel

@Composable
fun SplashScreen(viewModel: SplashViewModel) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.background(MaterialTheme.colorScheme.primary).fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.paynl),
                contentDescription = "App logo",
                modifier = Modifier
                    .size(width = 150.dp, height = 150.dp)
                    .padding(bottom = 20.dp)
            )
            CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.start()
    }

}