package com.paynl.pos.ui.viewModel

import androidx.lifecycle.ViewModel
import com.paynl.pos.services.PaymentService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeState(amount = 0, description = "", reference = "", isNumpadEnabled = true, isSpecialButtonEnabled = false))
    val uiState = _uiState.asStateFlow()

    fun addValue(value: Int) {
        var amountStr = _uiState.value.amount.toString()

        if (value == 11 && amountStr.length > 0) {
            amountStr = amountStr.substring(0, amountStr.length - 1)
        } else if (value != 12 && amountStr.length <= 5) {
            amountStr += value.toString()
        }

        if (amountStr.isEmpty()) {
            amountStr = "0"
        }

        _uiState.value = HomeState(
            amount = amountStr.toLong(),
            description = _uiState.value.description,
            reference = _uiState.value.reference,
            isNumpadEnabled = amountStr.length <= 5,
            isSpecialButtonEnabled = amountStr.toLong() != 0L
        )
    }

    fun startPayment() {
        val amount = _uiState.value.amount
        val description = _uiState.value.description
        val reference = _uiState.value.reference
        if (amount == 0L) {
            return
        }

        PaymentService.instance.startPayment(amount, description, reference)

        _uiState.value = HomeState(
            amount = 0,
            description = "",
            reference = "",
            isNumpadEnabled = true,
            isSpecialButtonEnabled = false,
        )
    }

    fun onDescriptionChange(value: String) {
        _uiState.value = HomeState(
            amount = _uiState.value.amount,
            description = value,
            reference = _uiState.value.reference,
            isNumpadEnabled = _uiState.value.isNumpadEnabled,
            isSpecialButtonEnabled = _uiState.value.isSpecialButtonEnabled,
        )
    }
    fun onReferenceChange(value: String) {
        _uiState.value = HomeState(
            amount = _uiState.value.amount,
            description = _uiState.value.reference,
            reference = value,
            isNumpadEnabled = _uiState.value.isNumpadEnabled,
            isSpecialButtonEnabled = _uiState.value.isSpecialButtonEnabled,
        )
    }
}

data class HomeState(val amount: Long, val description: String, val reference: String, val isNumpadEnabled: Boolean, val isSpecialButtonEnabled: Boolean)