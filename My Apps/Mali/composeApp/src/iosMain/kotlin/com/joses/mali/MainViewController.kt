package com.joses.mali

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.joses.mali.tenants.network.MpesaStkPushClient
import com.joses.mali.tenants.network.createHttpClient
import io.ktor.client.engine.darwin.Darwin

fun MainViewController() = ComposeUIViewController {
    App(
        mpesaClient = remember {
            MpesaStkPushClient(
                createHttpClient(
                    Darwin.create()
                )
            )
        }
    )
}
