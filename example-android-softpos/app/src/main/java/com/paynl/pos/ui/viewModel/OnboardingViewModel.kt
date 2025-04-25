package com.paynl.pos.ui.viewModel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.paynl.pos.Home
import com.paynl.pos.services.PaymentService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.Executors

data class OnboardUiState(var loading: Boolean = true, var activationCode: String = "")

class OnboardingViewModel(): ViewModel() {
    private val executeService = Executors.newSingleThreadExecutor()

    private val _uiState = MutableStateFlow(OnboardUiState())
    val uiState = _uiState.asStateFlow()

    var navController: NavHostController? = null

    fun start() {
        _uiState.value = OnboardUiState(loading = true, activationCode = "")

        val code = PaymentService.instance.getActivationCode() ?: ""
        _uiState.value = OnboardUiState(loading = false, activationCode = code)
    }

    fun activate() {
        val code = _uiState.value.activationCode
        if (code.isEmpty()) {
            return;
        }

        _uiState.value = OnboardUiState(loading = true, activationCode = code)

        executeService.submit {
            if (!PaymentService.instance.activate(code)) {
                Handler(Looper.getMainLooper()).post {
                    _uiState.value = OnboardUiState(loading = false, activationCode = code)
                }
                return@submit
            }

            Handler(Looper.getMainLooper()).post {
                navController?.navigate(Home)
            }
        }
    }
}