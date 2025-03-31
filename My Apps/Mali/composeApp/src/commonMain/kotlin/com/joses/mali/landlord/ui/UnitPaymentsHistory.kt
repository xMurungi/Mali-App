package com.joses.mali.landlord.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.joses.mali.tenants.ui.PaymentsList
import kotlinx.serialization.Serializable

@Serializable
data class UnitPaymentsHistoryUi(
    val tenantId: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitPaymentsHistory(
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
        UnitPaymentsHistoryList(
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
fun UnitPaymentsHistoryList(
    modifier: Modifier = Modifier
) {
    
}