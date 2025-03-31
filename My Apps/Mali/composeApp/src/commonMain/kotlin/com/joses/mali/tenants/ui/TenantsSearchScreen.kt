package com.joses.mali.tenants.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
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

@Composable
fun TenantSearchScreen(
    apartmentId: String,
    landlordRepository: LandlordRepository,
    navController: NavController,
) {
    var searchQuery by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf<List<AddTenant>>(emptyList()) }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                scope.launch {
                    searchResults = landlordRepository.searchAllTenants(searchQuery)
                }
            },
            label = { Text("Search by name or phone") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (searchResults.isEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Icon(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(58.dp),
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "KeyboardArrowUp"
                    )
                    Text(text = "Type your name in the Textbox above to get your data", style = MaterialTheme.typography.titleMedium)
                }
            }
        } else {
            LazyColumn {
                items(searchResults) { tenant ->
                    TenantItem(
                        tenant,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun TenantItem(
    tenant: AddTenant,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    route = TenantsScreen
                )
            }
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = tenant.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Unit: ${tenant.unitAllocated}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Phone: ${tenant.phoneNumber}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
