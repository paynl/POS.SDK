package com.paynl.pos.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PayNlTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    label: @Composable (() -> Unit)? = null
) {
    @Suppress("NAME_SHADOWING")
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        enabled = true,
        singleLine = singleLine,
        interactionSource = interactionSource,
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextFieldDefaults.DecorationBox(
            value = value,
            innerTextField = it,
            singleLine = singleLine,
            enabled = true,
            label = label,
            visualTransformation = VisualTransformation.None,
            colors = OutlinedTextFieldDefaults.colors(),
            interactionSource = interactionSource,
            container = {
                OutlinedTextFieldDefaults.run {
                    Container(
                                enabled = true,
                                interactionSource = interactionSource,
                                isError = false,
                                colors = colors(),
                                shape = MaterialTheme.shapes.small,
                                focusedBorderThickness = 2.dp,
                                unfocusedBorderThickness = 2.dp
                            )
                }
            }
        )
    }

//    OutlinedTextField(
//        value = value,
//        onValueChange = onValueChange,
//        label = label,
//        singleLine = singleLine,
//        colors = OutlinedTextFieldDefaults.colors(
//            focusedBorderColor = Color.Transparent,
//            unfocusedBorderColor = Color.Transparent
//        ),
//        modifier = modifier
//            .border(
//                width = 3.dp,
//                color = if (hasFocus) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
//                shape = MaterialTheme.shapes.small,
//            )
//            .onFocusChanged { focusState -> hasFocus = focusState.hasFocus },
//    )
}