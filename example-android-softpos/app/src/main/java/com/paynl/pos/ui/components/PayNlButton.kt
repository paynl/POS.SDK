package com.paynl.pos.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PayNlButtonPrimary(onClick: () -> Unit, modifier: Modifier = Modifier.fillMaxWidth(), enabled: Boolean = true, content: @Composable RowScope.() -> Unit) {
    Button(
        shape = MaterialTheme.shapes.small,
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor =
                MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
            disabledContentColor =
                MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.38f),
        ),
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 16.dp
        )
    ) {
        content()
    }
}

@Composable
fun PayNlButtonSecondary(onClick: () -> Unit, modifier: Modifier = Modifier.fillMaxWidth(), enabled: Boolean = true, content: @Composable (RowScope.() -> Unit)) {
    Button(
        shape = MaterialTheme.shapes.small,
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor =
                MaterialTheme.colorScheme.secondary.copy(alpha = 0.12f),
            disabledContentColor =
                MaterialTheme.colorScheme.primary.copy(alpha = 0.38f),
        ),
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 16.dp
        )
    ) {
        content()
    }
}