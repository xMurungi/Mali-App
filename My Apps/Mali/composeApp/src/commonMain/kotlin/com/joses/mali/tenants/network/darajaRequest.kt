package com.joses.mali.tenants.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class MpesaStkPushClient(
    private val httpClient: HttpClient
) {
    @OptIn(ExperimentalEncodingApi::class)
    fun generatePassword(businessShortCode: Int, passkey: String, timestamp: String): String {
        val rawPassword = "$businessShortCode$passkey$timestamp"
        return Base64.encode(rawPassword.encodeToByteArray())
    }

    suspend fun initiateStkPush(
        consumerKey: String = "qDHQ5qZUHUIhjklabkqHzatJVUiMoUGEQou9v94ZfTXKSilt",
        consumerSecret: String = "cOvEaGusEPnEgsNYDdGRrnJvDSYjt94MNqcrlP458gfBXmGbaPLgO5JASyUGT6vK",
        businessShortCode: Int = 174379,
        passkey: String = "bfb279f9aa9bdbcf158e97dd71a467cd2b0cdeedfcccd2a83590e004d5e04bed"
    ) {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }

        try {
            // Step 1: Generate Access Token
            val accessTokenResponse = generateAccessToken(client, consumerKey, consumerSecret)
            if (accessTokenResponse.access_token.isEmpty()) {
                println("❌ Failed to generate access token")
                return
            }

            // Step 2: Generate Timestamp
            val timestamp = getCurrentTimestamp()

            // Step 3: Generate Password
            val password = generatePassword(businessShortCode, passkey, timestamp)
            println("🔑 Generated Password: $password")

            // Step 4: Create STK Push Request Data
            val stkRequest = MpesaRequest(
                BusinessShortCode = businessShortCode,
                Password = password,
                Timestamp = timestamp,
                TransactionType = "CustomerPayBillOnline",
                Amount = 1,
                PartyA = 254708374149,
                PartyB = businessShortCode,
                PhoneNumber = 254796478541,
                CallBackURL = "https://mydomain.com/path",
                AccountReference = "TestPayment",
                TransactionDesc = "Payment of Test Service"
            )

            // Step 5: Make STK Push Request
            val stkPushResponse: HttpResponse = client.post("https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest") {
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${accessTokenResponse.access_token}")
                }
                setBody(stkRequest)
            }

            // Parse and log the response
            val responseBody1 = stkPushResponse.bodyAsText()
            println("📡 STK Push Response Status: ${stkPushResponse.status}")
            println("📡 STK Push Response Body: $responseBody1")

            // Try to parse the error response
            try {
                val errorResponse = Json.decodeFromString<StkPushErrorResponse>(responseBody1)
                println("❌ Detailed Error Information:")
                println("   Request ID: ${errorResponse.requestId}")
                println("   Error Code: ${errorResponse.errorCode}")
                println("   Error Message: ${errorResponse.errorMessage}")
            } catch (parseEx: Exception) {
                println("❌ Could not parse error response: ${parseEx.message}")
            }

            val responseBody = stkPushResponse.bodyAsText()
            println("📡 STK Push Response: $responseBody")

        } catch (e: Exception) {
            println("❌ Error in STK Push: ${e.message}")
            e.printStackTrace()
        } finally {
            client.close()
        }
    }

    private suspend fun generateAccessToken(
        client: HttpClient,
        consumerKey: String,
        consumerSecret: String
    ): AccessTokenResponse {
        return try {
            @OptIn(ExperimentalEncodingApi::class)
            val encodedCredentials = Base64.encode("$consumerKey:$consumerSecret".encodeToByteArray())

            val response: HttpResponse = client.get("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials") {
                headers {
                    append(HttpHeaders.Authorization, "Basic $encodedCredentials")
                    append(HttpHeaders.ContentType, "application/x-www-form-urlencoded")
                }
//                setBody("grant_type=client_credentials")
            }

            // Manually parse the response
            val responseBody = response.bodyAsText()
            println("🔑 Access Token Response Body: $responseBody")

            Json.decodeFromString<AccessTokenResponse>(responseBody)
        } catch (e: Exception) {
            println("❌ Access Token Error: ${e.message}")
            AccessTokenResponse()
        }
    }

    fun getCurrentTimestamp(): String {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        return "${now.year}" +
                "${now.monthNumber.toString().padStart(2, '0')}" +
                "${now.dayOfMonth.toString().padStart(2, '0')}" +
                "${now.hour.toString().padStart(2, '0')}" +
                "${now.minute.toString().padStart(2, '0')}" +
                "${now.second.toString().padStart(2, '0')}"
    }
}

@Serializable
data class MpesaRequest(
    val BusinessShortCode: Int,
    val Password: String,
    val Timestamp: String,
    val TransactionType: String,
    val Amount: Int,
    val PartyA: Long,
    val PartyB: Int,
    val PhoneNumber: Long,
    val CallBackURL: String,
    val AccountReference: String,
    val TransactionDesc: String
)

@Serializable
data class AccessTokenResponse(
    val access_token: String = "",
    val expires_in: String = ""
)

@Serializable
data class StkPushErrorResponse(
    val requestId: String? = null,
    val errorCode: String? = null,
    val errorMessage: String? = null
)