package com.joses.mali

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.joses.mali.navigation.MaliApp
import com.joses.mali.tenants.network.MpesaStkPushClient
import com.joses.mali.tenants.repository.FirestoreUserRepository
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import com.mmk.kmpauth.google.GoogleButtonUiContainer
import com.mmk.kmpauth.uihelper.google.GoogleSignInButton
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    mpesaClient: MpesaStkPushClient,
    onGeneratePdf: () -> Unit,
    generateApartmentPaymentReport: () -> Unit,
    onGeneratePastTenantPdf: () -> Unit,
    generateTenantPdf: () -> Unit
) {

    val userRepository = remember { FirestoreUserRepository() }

    MaliApp(
        mpesaStkPushClient = mpesaClient,
        userRepository = userRepository,
        onGeneratePdf = onGeneratePdf,
        generateApartmentPaymentReport = generateApartmentPaymentReport,
        onGeneratePastTenantPdf = onGeneratePastTenantPdf,
        generateTenantPdf = generateTenantPdf
    )

}
