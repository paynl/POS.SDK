package com.paynl.pos.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paynl.pos.sdk.shared.models.offline.OfflineQueueModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PayNlOfflineSheet(offlineQueue: OfflineQueueModel, onDismiss: () -> Unit, triggerFullOfflineProcessing: () -> Unit, triggerSingleOfflineProcessing: (String) -> Unit, triggerRefresh: () -> Unit) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 20.dp).fillMaxHeight(0.5f)
        ) {
            Text(
                "Offline queue size: " + offlineQueue.size,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            offlineQueue.queue.map { item -> Button(
                modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth(),
                onClick = { triggerSingleOfflineProcessing(item.id) }
            ) {
                Text("Single offline process - id: " + item.id)
            } }

            Row(modifier = Modifier.weight(1f)) { }

            Button(
                modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth(),
                enabled = offlineQueue.size > 0,
                onClick = { triggerFullOfflineProcessing() }
            ) {
                Text("Trigger full offline processing")
            }
            Button(
                modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth(),
                onClick = { triggerFullOfflineProcessing() }
            ) {
                Text("Refresh offline queue")
            }
        }
    }
}