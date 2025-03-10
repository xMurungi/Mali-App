package com.joses.mali.tenants.network

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.encodeBase64
import io.ktor.utils.io.charsets.Charsets
import io.ktor.utils.io.core.toByteArray
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class TokenResponse(
    @SerialName("access_token") val accessToken: String,
    @SerialName("expires_in") val expiresIn: String
)

// ✅ Use a global HttpClient instance to prevent multiple initializations
val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
        })
    }
}

suspend fun generateAccessToken(consumerKey: String, consumerSecret: String): TokenResponse {
    val credentials = "$consumerKey:$consumerSecret"
    val encodedCredentials = credentials.toByteArray(Charsets.UTF_8).encodeBase64() // ✅ KMP-Compatible Base64 Encoding

    println("🔹 Requesting Access Token...")

    val response = client.get("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials") {
        headers {
            append(HttpHeaders.Authorization, "Basic $encodedCredentials")
            append(HttpHeaders.Accept, ContentType.Application.Json.toString())
        }
    }

    println("✅ Response Status: ${response.status}")
    println("✅ Response Headers: ${response.headers}")
    println("✅ Response Body: ${response.bodyAsText()}")

    return response.body()
}


// Base64 encoding function for KMP
fun String.encodeBase64(): String {
    val bytes = this.encodeToByteArray()
    return bytes.toBase64()
}

// KMP-compatible Base64 encoding
fun ByteArray.toBase64(): String {
    val base64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
    val output = StringBuilder()
    var paddingCount = 0

    for (i in this.indices step 3) {
        val byte0 = this[i].toInt() and 0xff
        val byte1 = if (i + 1 < this.size) this[i + 1].toInt() and 0xff else -1.also { paddingCount++ }
        val byte2 = if (i + 2 < this.size) this[i + 2].toInt() and 0xff else -1.also { paddingCount++ }

        output.append(base64Chars[(byte0 shr 2) and 0x3f])
        output.append(base64Chars[((byte0 shl 4) or (byte1 shr 4 and 0xf)) and 0x3f])
        if (byte1 != -1) output.append(base64Chars[((byte1 shl 2) or (byte2 shr 6 and 0x3)) and 0x3f]) else output.append('=')
        if (byte2 != -1) output.append(base64Chars[byte2 and 0x3f]) else output.append('=')
    }
    return output.toString()
}
