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
import mali.composeapp.generated.resources.payHistory
import org.jetbrains.compose.resources.painterResource

@Serializable
data class AllTenantsRoute(
    val apartmentId: String
)

@Composable
fun AllTenants(
    modifier: Modifier = Modifier,
    navController: NavController,
    apartmentId: String,
    generateTenantPdf: () -> Unit
) {
    val landlordRepository = remember { LandlordRepository() }
    val scope = rememberCoroutineScope()

    val tenantsList by landlordRepository.getTenants(apartmentId).collectAsState(emptyList())

    AllTenantsUi(
        users = tenantsList,
        apartmentId = apartmentId,
        addUser = { tenant -> scope.launch { landlordRepository.addTenant(apartmentId, tenant) } },
        navController = navController,
        generateTenantPdf = generateTenantPdf
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllTenantsUi(
    modifier: Modifier = Modifier,
    apartmentId: String,
    users: List<AddTenant>,
    addUser: (AddTenant) -> Unit,
    navController: NavController,
    generateTenantPdf: () -> Unit
) {
    val landlordRepository = remember { LandlordRepository() }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var selectedUser by remember { mutableStateOf<AddTenant?>(null) }
    var showBottomSheet by remember { mutableStateOf(false) }

    val updateUser: (AddTenant) -> Unit = { updatedTenant ->
        scope.launch {
            landlordRepository.updateTenant(
                apartmentId = apartmentId,
                tenantId = updatedTenant.id,
                updatedTenant = updatedTenant
            )
        }
    }

    val deleteUser: (String) -> Unit = { tenantId ->
        scope.launch {
            landlordRepository.deleteTenant(
                apartmentId = apartmentId,
                tenantId = tenantId
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Tenants List")
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
            FloatingActionButton(onClick = { showBottomSheet = true }) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        text = "Add Tenant"
                    )
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Tenant"
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .padding(vertical = 6.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Button(
                        onClick = {
                            navController.navigate(
                                ApartmentPaymentsRoute(apartmentId = apartmentId)
                            )
                        }
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "View Apartment payments"
                            )
                            Icon(
                                painter = painterResource(Res.drawable.PaymentImg),
                                contentDescription = "View Apartment payments",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.padding(4.dp)
                    )

                    Button(
                        onClick = {
                            navController.navigate(
                                PastTenantsRoute(apartmentId = apartmentId)
                            )
                        }
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "View past tenants"
                            )
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "View Apartment payments",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }

                    Button(
                        onClick = {
                            generateTenantPdf()
                        }
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Generate Apartment tenants Report"
                            )
                            Icon(
                                painter = painterResource(Res.drawable.PaymentImg),
                                contentDescription = "View Apartment payments",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }

                }
            }

            UserList(
                users = users,
                navController = navController,
//                modifier = Modifier.padding(innerPadding)
            ) { user ->
                selectedUser = user
                showBottomSheet = true
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                BottomSheetContent(
                    user = selectedUser,
                    onSave = { user ->
                        scope.launch {
                            if (selectedUser == null) {
                                addUser(user)
                            } else {
                                updateUser(user)
                            }
                            sheetState.hide()
                        }.invokeOnCompletion {
                            showBottomSheet = false
                            selectedUser = null
                        }
                    },
                    onDelete = { user ->
                        scope.launch {
                            user?.id?.let { deleteUser(it) }
//                            user?.let { deleteUser(it) }
                            sheetState.hide()
                        }.invokeOnCompletion {
                            showBottomSheet = false
                            selectedUser = null
                        }
                    }
                )
            }
        }
    }
}
