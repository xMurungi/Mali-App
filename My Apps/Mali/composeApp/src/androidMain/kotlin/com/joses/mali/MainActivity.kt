package com.joses.mali

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.FirebaseApp
import com.joses.mali.tenants.network.MpesaStkPushClient
import com.joses.mali.tenants.network.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp
import android.os.Environment
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        setContent {
            val context = LocalContext.current
            val pdfGenerator = PdfGeneratorImpl()
//            val filePath = "${context.filesDir}/sample.pdf"

            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val filePath = File(downloadsDir, "JohnPayments.pdf").absolutePath

            val pdfAptGenerator = PdfApartmentGeneratorImpl()
            val aptFilePath = File(downloadsDir, "UmojaApartment.pdf").absolutePath

            val pdfTenantGenerator = PastTenantPdfGeneratorImpl()
            val tenantFilePath = File(downloadsDir, "PastTenants.pdf").absolutePath

            val allPdfTenantGenerator = TenantPdfGenerator()
            val allTenantFilePath = File(downloadsDir, "AllCurrentTenants.pdf").absolutePath

            App(
                mpesaClient = remember {
                    MpesaStkPushClient(
                        createHttpClient(
                            OkHttp.create()
                        )
                    )
                },
                onGeneratePdf = {
                    pdfGenerator.generatePdf(filePath)
                    Toast.makeText(this, "\uD83D\uDCC4 PDF Generated at $filePath", Toast.LENGTH_LONG).show()
                },
                generateApartmentPaymentReport = {
                    pdfAptGenerator.generatePdf(aptFilePath)
                    Toast.makeText(this, "\uD83D\uDCC4 PDF Generated at $aptFilePath", Toast.LENGTH_LONG).show()
                },
                onGeneratePastTenantPdf = {
                    pdfTenantGenerator.generatePdf(tenantFilePath)
                    Toast.makeText(this, "\uD83D\uDCC4 PDF Generated at $tenantFilePath", Toast.LENGTH_LONG).show()
                },
                generateTenantPdf = {
                    allPdfTenantGenerator.generatePdf(allTenantFilePath)
                    Toast.makeText(this, "\uD83D\uDCC4 PDF Generated at $allTenantFilePath", Toast.LENGTH_LONG).show()
                }
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    val context = LocalContext.current
    val pdfGenerator = PdfGeneratorImpl()
    val filePath = "${context.filesDir}/sample.pdf"

    val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

    val pdfAptGenerator = PdfGeneratorImpl()
    val aptFilePath = File(downloadsDir, "UmojaApartment.pdf").absolutePath

    val pdfTenantGenerator = PastTenantPdfGeneratorImpl()
    val tenantFilePath = File(downloadsDir, "PastTenants.pdf").absolutePath

    val allPdfTenantGenerator = PastTenantPdfGeneratorImpl()
    val allTenantFilePath = File(downloadsDir, "PastTenants.pdf").absolutePath

    App(
        mpesaClient = remember {
            MpesaStkPushClient(
                createHttpClient(
                    OkHttp.create()
                )
            )
        },
        onGeneratePdf = {
            pdfGenerator.generatePdf(filePath)
//            Toast.makeText(this, "PDF Generated at $filePath", Toast.LENGTH_LONG).show()
        },
        generateApartmentPaymentReport = {
            pdfAptGenerator.generatePdf(filePath)
//            Toast.makeText(this, "\uD83D\uDCC4 PDF Generated at $aptFilePath", Toast.LENGTH_LONG).show()
        },
        onGeneratePastTenantPdf = {
            pdfTenantGenerator.generatePdf(tenantFilePath)
//            Toast.makeText(this, "\uD83D\uDCC4 PDF Generated at $tenantFilePath", Toast.LENGTH_LONG).show()
        },
        generateTenantPdf = {
            allPdfTenantGenerator.generatePdf(allTenantFilePath)
//            Toast.makeText(this, "\uD83D\uDCC4 PDF Generated at $allTenantFilePath", Toast.LENGTH_LONG).show()
        }
    )
}
