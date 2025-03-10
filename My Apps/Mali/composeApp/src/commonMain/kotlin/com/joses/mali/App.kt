package com.joses.mali

import androidx.compose.runtime.Composable
import com.joses.mali.navigation.MaliApp
import com.joses.mali.tenants.network.MpesaStkPushClient
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    mpesaClient: MpesaStkPushClient,
) {
    MaliApp(
        mpesaStkPushClient = mpesaClient
    )
}