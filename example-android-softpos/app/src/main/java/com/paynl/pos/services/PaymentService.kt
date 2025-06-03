package com.paynl.pos.services

import android.content.Context
import android.util.Log
import com.paynl.pos.sdk.PosService
import com.paynl.pos.sdk.shared.exceptions.SVErrorBaseException
import com.paynl.pos.sdk.shared.models.paynl.PayNlInitResult
import com.paynl.pos.sdk.shared.models.paynl.transaction.PayNlTransaction
import com.paynl.pos.sdk.shared.models.paynl.transaction.PayNlTransactionAmount
import com.paynl.pos.sdk.shared.models.paynl.transaction.PayNlTransactionStatus
import com.paynl.pos.sdk.shared.payment.PaymentOverlayParams
import java.util.concurrent.Executors

class PaymentService {
    private var paynlPosService: PosService? = null
    private val executorService = Executors.newSingleThreadExecutor();

    fun setContext(context: Context) {
        val integrationId = "b1fe4701-a473-4b20-a9a4-1f2ef8602909" // Received via PayNL support
        val license = "PayNL Partner SDK - Testing-lic_01JQNMTHEAEFC6GJT4SHVVJSV1-20250331_082424.license" // License for testing ONLY
        val overlayParams = PaymentOverlayParams()

        paynlPosService = PosService(context, integrationId, license, overlayParams, true, true, true)
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

    fun startPayment(amount: Long, description: String, reference: String) {
        val service = paynlPosService ?: return

        val transaction = PayNlTransaction.Builder()
            .setAmount(PayNlTransactionAmount(amount, "EUR"))
            .setDescription(description)
            .setReference(reference)
            .build()

        // Make sure you start transaction on background thread
        executorService.submit {
            val transactionResult = service.startTransaction(transaction, null)

            if (transactionResult.statusAction != PayNlTransactionStatus.PAID) {
                Log.e("PaymentService", "Payment failed...")
                return@submit
            }

            Log.i("PaymentService", String.format("Payment succeeded!"))
        }
    }

    companion object {
        val instance: PaymentService = PaymentService()
    }
}
