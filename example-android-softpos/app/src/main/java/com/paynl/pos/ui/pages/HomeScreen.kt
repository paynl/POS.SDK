package com.paynl.pos.ui.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paynl.pos.R
import com.paynl.pos.ui.components.PayNlPinPad
import com.paynl.pos.ui.components.PayNlTextField
import com.paynl.pos.ui.viewModel.HomeViewModel
import java.nio.file.WatchEvent
import java.util.Locale

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val isKeyboardOpen by keyboardAsState()

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
                .size(width = 90.dp, height = 90.dp)
                .padding(bottom = 20.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Text(
                String.format(Locale.UK, "€ %d,", (uiState.amount / 100).toInt()),
                style = MaterialTheme.typography.titleLarge,
                fontSize = 56.sp,
                color = if (uiState.amount == 0.toLong()) Color(0x4D1D1D1D) else Color.Unspecified
            )
            Text(
                String.format(Locale.UK, "%02d", uiState.amount.mod(100)),
                style = MaterialTheme.typography.titleMedium,
                fontSize = 36.sp,
                color = if (uiState.amount == 0.toLong()) Color(0x4D1D1D1D) else Color.Unspecified
            )
        }

        PayNlTextField(
            value = uiState.description,
            onValueChange = { viewModel.onDescriptionChange(it) },
            label = { Text("Description") },
            modifier = Modifier.padding(bottom = 20.dp)
        )

        PayNlTextField(
            value = uiState.reference,
            onValueChange = { viewModel.onReferenceChange(it) },
            label = { Text("Reference") },
            modifier = Modifier.padding(bottom = 40.dp)
        )

        AnimatedVisibility(
            !isKeyboardOpen,
            modifier = Modifier.fillMaxSize(),
            enter = slideInVertically(initialOffsetY = { it / 2 }),
            exit = slideOutVertically(targetOffsetY = { it / 2 })
        ) {
            PayNlPinPad(
                isNumpadEnabled = uiState.isNumpadEnabled,
                isSpecialButtonEnabled = uiState.isSpecialButtonEnabled,
                onNumber = { viewModel.addValue(it) },
                onSubmit = { viewModel.startPayment() }
            )
        }
    }
}

@Composable
fun keyboardAsState(): State<Boolean> {
    val isImeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    return rememberUpdatedState(isImeVisible)
}