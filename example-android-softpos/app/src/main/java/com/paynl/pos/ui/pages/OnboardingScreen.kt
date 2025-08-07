package com.paynl.pos.ui.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.paynl.pos.R
import com.paynl.pos.ui.components.PayNlButtonSecondary
import com.paynl.pos.ui.viewModel.OnboardingViewModel

@Composable
fun OnboardingScreen(viewModel: OnboardingViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.paynl),
            contentDescription = "App logo",
            modifier = Modifier
                .size(width = 75.dp, height = 75.dp)
                .padding(bottom = 40.dp)
        )

        // TODO: Add real waiver
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 15.dp))
                .background(color = Color(0xFFDEDFFF))
        ) {
            Image(
                painter = painterResource(id = R.drawable.attp_example),
                contentDescription = "Waiver",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .heightIn(max = 300.dp)
                    .aspectRatio(1f)
            )
        }

        Text(
            "Activeer uw terminal!",
            modifier = Modifier.padding(top = 40.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .weight(1f)
                    .background(Color.Gray)
            ) { }
            Text(
                "Klik voor activatie",
                modifier = Modifier.padding(horizontal = 5.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .weight(1f)
                    .background(Color.Gray)
            ) { }
        }

        PayNlButtonSecondary(onClick = { viewModel.activate() }) {
            AnimatedVisibility(
                !uiState.loading,
                enter = slideInVertically(initialOffsetY = { it / 2 }),
            ) {
                Text(
                    uiState.activationCode,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            if (uiState.loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.start()
    }
}