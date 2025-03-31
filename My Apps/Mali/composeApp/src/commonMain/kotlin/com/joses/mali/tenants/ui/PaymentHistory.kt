package com.joses.mali.tenants.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Serializable
data class PaymentHistoryUi(
    val tenantId: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentHistory(
    modifier: Modifier = Modifier,
    navController: NavController,
    tenantId: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Payment History")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }

                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
//                    showBottomSheet = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {
        PaymentsList(
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
fun PaymentsList(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(payments) { payment ->
            PaymentCard(
                paymentHistory = payment
            )
        }
    }
}

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    paymentHistory: paymentHistory
) {
    Surface {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .clickable { }
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Date paid ${paymentHistory.date}",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Amount: ${paymentHistory.amount}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Mpesa Ref.: ${paymentHistory.mpesaRef}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(2.dp))

            }
        }
    }
}

data class paymentHistory (
    val date: String,
    val amount: String,
    val mpesaRef: String,
)

val payments = listOf(
    paymentHistory(
        date = "1st October 2025",
        amount = "14,000",
        mpesaRef = "OCD2K5KI4C"
    ),
    paymentHistory(
        date = "1st November 2024",
        amount = "14,000",
        mpesaRef = "PCD2K5KI4C"
    ),
    paymentHistory(
        date = "1st December 2024",
        amount = "14,000",
        mpesaRef = "QCD2K5KI4C"
    ),
    paymentHistory(
        date = "1st January 2025",
        amount = "14,000",
        mpesaRef = "RCD2K5KI4C"
    ),
    paymentHistory(
        date = "1st February 2025",
        amount = "14,000",
        mpesaRef = "SCD2K5KI4C"
    ),
    paymentHistory(
        date = "1st March 2025",
        amount = "14,000",
        mpesaRef = "TCD2K5KI4C"
    ),
)
