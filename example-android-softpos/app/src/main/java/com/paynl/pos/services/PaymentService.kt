package com.paynl.pos.services

import android.content.Context
import android.util.Log
import com.paynl.pos.sdk.PosService
import com.paynl.pos.sdk.shared.exceptions.SVErrorBaseException
import com.paynl.pos.sdk.shared.models.offline.OfflineQueueModel
import com.paynl.pos.sdk.shared.models.paynl.PayNlConfiguration
import com.paynl.pos.sdk.shared.models.paynl.PayNlInitResult
import com.paynl.pos.sdk.shared.models.paynl.PinPadLayoutParams
import com.paynl.pos.sdk.shared.models.paynl.transaction.PayNlTransaction
import com.paynl.pos.sdk.shared.models.paynl.transaction.PayNlTransactionAmount
import com.paynl.pos.sdk.shared.models.paynl.transaction.PayNlTransactionResult
import com.paynl.pos.sdk.shared.models.paynl.transaction.PayNlTransactionStats
import com.paynl.pos.sdk.shared.models.paynl.transaction.PayNlTransactionStatus
import com.paynl.pos.sdk.shared.payment.PaymentOverlayParams
import java.util.concurrent.Executors

class PaymentService {
    private var paynlPosService: PosService? = null
    private val executorService = Executors.newSingleThreadExecutor();

    fun setContext(context: Context) {
        val configuration = PayNlConfiguration.Builder()
            .setIntegrationId("") // Received via PayNL support
            .setLicenseName("") // License for testing ONLY
            .setEnableSound(true)
            .setEnableLogging(true) // If set to false, PayNL cannot provide support
            .setUseExternalDisplayIfAvailable(true) // Disable if you want the pinpad to always be shown on the primary screen
            .setEnableOfflineProcessing(true)
            .setEnforcePinCodeDuringOfflineProcessing(true)
            .setOverlayParams(PaymentOverlayParams())
            .setPinPadLayoutParams(PinPadLayoutParams())
            .build()

        paynlPosService = PosService(context, configuration)
    }

    fun initSdk(): PayNlInitResult? {
        return paynlPosService?.initSdk()
    }

    fun getActivationCode(): String? {
        val service = paynlPosService ?: return null

        try {
            return service.activationCode.code
        } catch (exception: SVErrorBaseException) {
            Log.e("PaymentService", "Failed to get activation code: ${exception.code} - ${exception.description}")
            return null
        }
    }

    fun activate(code: String): Boolean {
        val service = paynlPosService ?: return false

        if (!RestPayService.instance.activateTerminal(code)) {
            return false
        }

        // Wait 5s before activating SDK
        Thread.sleep(5000)

        try {
            service.loginViaCode(code)
            return true
        } catch (exception: SVErrorBaseException) {
            Log.e("PaymentService", "Failed to login via code: ${exception.code} - ${exception.description}")
            return false
        }
    }

    fun startPayment(amount: Long, description: String, reference: String, resetForm: () -> Unit) {
        val service = paynlPosService ?: return

        val transaction = PayNlTransaction.Builder()
            .setAmount(PayNlTransactionAmount(amount, "EUR"))
            .setDescription(description)
            .setReference(reference)
            .build()

        // Make sure you start transaction on background thread
        executorService.submit {
            val transactionResult = service.startTransaction(transaction, null)
            resetForm()

            if (transactionResult.statusAction == PayNlTransactionStatus.OFFLINE) {
                Log.w("PaymentService", "Payment in Offline queue")
                return@submit
            }

            if (transactionResult.statusAction != PayNlTransactionStatus.PAID) {
                Log.e("PaymentService", "Payment failed...")
                return@submit
            }

            Log.i("PaymentService", String.format("Payment succeeded!"))
        }
    }

    fun getOfflineQueue(): OfflineQueueModel {
        return paynlPosService?.offlineQueue ?: OfflineQueueModel(0, ArrayList())
    }

    fun triggerOfflineProcessing(): List<PayNlTransactionResult>? {
        return try {
            paynlPosService?.triggerFullOfflineProcessing()
        } catch (exception: SVErrorBaseException) {
            null
        }
    }

    fun triggerSingleOfflineProcessing(id: String): PayNlTransactionResult? {
        return try {
            paynlPosService?.triggerSingleOfflineProcessing(id)
        } catch (exception: SVErrorBaseException) {
            null
        }
    }

    companion object {
        val instance: PaymentService = PaymentService()
    }
}
