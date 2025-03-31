package com.joses.mali.landlord.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joses.mali.landlord.repository.LandlordRepository
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class ApartmentPaymentsRoute(
    val apartmentId: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApartmentPayments(
    modifier: Modifier = Modifier,
    navController: NavController,
    apartmentId: String,
    generateApartmentPaymentReport: () -> Unit
) {
    val landlordRepository = remember { LandlordRepository() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Apartment Payments")
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
                    coroutineScope.launch {
                        generateApartmentPaymentReport()
                    }
                }
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        text = "Generate Apartment Payment Report"
                    )
//                    Icon(
//                        imageVector = Icons.Default.Add,
//                        contentDescription = "Add Tenant"
//                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        ApartmentPaymentsUi(
            modifier = Modifier
                .padding(innerPadding)
        )
    }
}

@Composable
fun ApartmentPaymentsUi(
    modifier: Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        LazyColumn {
            items(ApartmentPaymentsData) { payment ->
                PaymentItem(payment)
            }
        }
    }
}

@Composable
fun PaymentItem(payment: ApartmentPaymentsDataClass) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Date: ${payment.date}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Tenant: ${payment.tenantName}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Mpesa Ref: ${payment.mpesaRef}", style = MaterialTheme.typography.bodyMedium)
            }
            Text(
                text = "Ksh ${payment.amount}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

val ApartmentPaymentsData = listOf(
    ApartmentPaymentsDataClass("2025-03-20", "John Doe", 14000, "OCD2K5KI4C"),
    ApartmentPaymentsDataClass("2025-03-21", "Jane Smith", 14000, "XYZ1A2B3C4"),
    ApartmentPaymentsDataClass("2025-03-22", "David Kim", 14000, "LMN9X8Y7Z6"),
    ApartmentPaymentsDataClass("2025-03-23", "Alice Brown", 14000, "PQR5T6U7V8"),
    ApartmentPaymentsDataClass("2025-03-24", "Brian Ouko", 14000, "ABC1D2E3F4")
)

data class ApartmentPaymentsDataClass(
    val date: String,
    val tenantName: String,
    val amount: Int,
    val mpesaRef: String
)
