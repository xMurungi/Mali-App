package com.joses.mali.landlord.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joses.mali.components.BottomSheetContent
import com.joses.mali.components.UserList
import com.joses.mali.landlord.domain.AddTenant
import com.joses.mali.landlord.repository.LandlordRepository
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import mali.composeapp.generated.resources.PaymentImg
import mali.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource

@Serializable
data class PastTenantsRoute(
    val apartmentId: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PastTenants(
    modifier: Modifier = Modifier,
    navController: NavController,
    apartmentId: String,
    onGeneratePastTenantPdf: () -> Unit
) {
    val landlordRepository = remember { LandlordRepository() }
    val scope = rememberCoroutineScope()

    val tenantsList by landlordRepository.getPastTenants(apartmentId).collectAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Past Tenants")
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
                    onGeneratePastTenantPdf()
                }
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        text = "Generate Report of past Tenant"
                    )
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Report of past tenants"
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        PastTenantsUi(
            modifier = Modifier.padding(innerPadding),
            users = tenantsList,
            apartmentId = apartmentId,
            navController = navController
        )
    }
}

@Composable
fun PastTenantsUi(
    modifier: Modifier = Modifier,
    apartmentId: String,
    users: List<AddTenant>,
    navController: NavController
) {
    var selectedUser by remember { mutableStateOf<AddTenant?>(null) }
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        UserList(
            users = users,
            navController = navController,
//                modifier = Modifier.padding(innerPadding)
        ) { user ->
            selectedUser = user
            showBottomSheet = true
        }
    }
}

