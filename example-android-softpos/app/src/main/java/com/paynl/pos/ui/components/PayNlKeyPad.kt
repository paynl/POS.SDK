package com.paynl.pos.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paynl.pos.R
import java.nio.file.WatchEvent

@Composable
fun PayNlPinPad(isNumpadEnabled: Boolean, isSpecialButtonEnabled: Boolean, onNumber: (Int) -> Unit, onSubmit: (Int) -> Unit) {
    val pinPadColor = ButtonColors(
        containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.onBackground,
        disabledContainerColor =
            MaterialTheme.colorScheme.tertiary.copy(alpha = 0.12f),
        disabledContentColor =
            MaterialTheme.colorScheme.onBackground.copy(alpha = 0.38f),
    )

    val gridPadding = 16.dp
    Column(
        modifier = Modifier.padding(bottom = 30.dp).fillMaxSize()
    ) {
        Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
            PinPadButton(enabled = isNumpadEnabled, onClick = onNumber, index = 1, pinPadColor = pinPadColor, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.size(gridPadding))
            PinPadButton(enabled = isNumpadEnabled, onClick = onNumber, index = 2, pinPadColor = pinPadColor, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.size(gridPadding))
            PinPadButton(enabled = isNumpadEnabled, onClick = onNumber, index = 3, pinPadColor = pinPadColor, modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.size(gridPadding))
        Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
            PinPadButton(enabled = isNumpadEnabled, onClick = onNumber, index = 4, pinPadColor = pinPadColor, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.size(gridPadding))
            PinPadButton(enabled = isNumpadEnabled, onClick = onNumber, index = 5, pinPadColor = pinPadColor, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.size(gridPadding))
            PinPadButton(enabled = isNumpadEnabled, onClick = onNumber, index = 6, pinPadColor = pinPadColor, modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.size(gridPadding))
        Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
            PinPadButton(enabled = isNumpadEnabled, onClick = onNumber, index = 7, pinPadColor = pinPadColor, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.size(gridPadding))
            PinPadButton(enabled = isNumpadEnabled, onClick = onNumber, index = 8, pinPadColor = pinPadColor, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.size(gridPadding))
            PinPadButton(enabled = isNumpadEnabled, onClick = onNumber, index = 9, pinPadColor = pinPadColor, modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.size(gridPadding))
        Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
            PinPadButton(enabled = isSpecialButtonEnabled, onClick = onNumber, index = 11, pinPadColor = pinPadColor, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.size(gridPadding))
            PinPadButton(enabled = isNumpadEnabled, onClick = onNumber, index = 0, pinPadColor = pinPadColor, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.size(gridPadding))
            PinPadButton(enabled = isSpecialButtonEnabled, onClick = onSubmit, index = 12, pinPadColor = pinPadColor, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun PinPadButton(enabled: Boolean, onClick: (Int) -> Unit, index: Int, pinPadColor: ButtonColors, modifier: Modifier = Modifier) {
    Button(
        enabled = enabled,
        shape = MaterialTheme.shapes.small,
        colors = pinPadColor,
        onClick = { onClick(index) },
        modifier = modifier.fillMaxHeight().border(width = 2.dp, color = MaterialTheme.colorScheme.secondary, shape = MaterialTheme.shapes.small),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        if (index == 11) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.delete_left),
                contentDescription = "",
                colorFilter = if (enabled) ColorFilter.tint(Color(0xFF1D1D1D)) else ColorFilter.tint(Color(0x801D1D1D)),
                modifier = Modifier.size(32.dp)
            )
        } else if (index == 12) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.arrow_right),
                contentDescription = "",
                colorFilter = if (enabled) ColorFilter.tint(Color(0xFF1D1D1D)) else ColorFilter.tint(Color(0x801D1D1D)),
                modifier = Modifier.size(32.dp)
            )
        } else {
            Text(
                index.toString(),
                fontSize = 32.sp,
                color = if (enabled) Color(0xFF1D1D1D) else Color(0x801D1D1D),
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}