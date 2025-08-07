package com.paynl.pos.services

import android.util.Log
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

data class CreateTerminal(val serviceId: String, val activationCode: String, val name: String, val description: String)

class RestPayService {
    private var client: OkHttpClient = OkHttpClient()
    private var gson = Gson()

    fun activateTerminal(code: String): Boolean {
        val requestBody = CreateTerminal(
            serviceId = SERVICE_ID,
            activationCode = code,
            name = "PAY.POS example app",
            description = "Auto activated"
        )

        val request: Request = Request.Builder()
            .url("https://rest.pay.nl/v2/terminals")
            .post(gson.toJson(requestBody).toRequestBody(JSON))
            .header("Accept", "application/json")
            .header("Authorization", AT)
            .build()

        client.newCall(request).execute().use { response ->
            if (response.code < 400) {
                Log.i("RestPayService", "Create:Terminal success! Response ${response.code} - ${response.body?.string()}")
                return true
            }

            Log.e("RestPayService", "Failed to Create:Terminal... Response ${response.code} - ${response.body?.string()}")
            return false
        }
    }

    companion object {
        val instance: RestPayService = RestPayService()
        val JSON = "application/json".toMediaType()
        val SERVICE_ID = "SL-xxxx-xxxx"
        val AT = "Basic xxxx"
    }
}