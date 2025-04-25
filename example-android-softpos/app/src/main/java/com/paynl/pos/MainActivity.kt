package com.paynl.pos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.paynl.pos.ui.theme.PayNlTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Needed for Keyboard open detection
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            PayNlTheme {
                PayNlNavigation(this)
            }
        }
    }
}

