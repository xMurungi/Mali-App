package com.joses.mali.tenants.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joses.mali.landlord.ui.AllTenantsRoute
import com.joses.mali.tenants.domain.UserRepository
import com.joses.mali.tenants.network.MpesaStkPushClient
import com.joses.mali.ui.AddRouteDialog
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import mali.composeapp.generated.resources.Aptmnt_Image
import mali.composeapp.generated.resources.BillImg
import mali.composeapp.generated.resources.LadyImage
import mali.composeapp.generated.resources.PaymentImg
import mali.composeapp.generated.resources.Res
import mali.composeapp.generated.resources.boyImage
import mali.composeapp.generated.resources.payHistory
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Serializable
object TenantsScreen

@Serializable
object TenantsScreen2

@Composable
@Preview
fun TenantsScreen(
    navController: NavController,
    mpesaStkPushClient: MpesaStkPushClient,
    repository: UserRepository,
    onGeneratePdf: () -> Unit
) {
    MaterialTheme {

        TenantView(
            mpesaStkPushClient = mpesaStkPushClient,
            navController = navController,
            onGeneratePdf = onGeneratePdf
        )

//        UserScreenContent(
//            users = users,
//            addUser = { scope.launch { repository.addUser(it) } },
//            updateUser = { scope.launch { repository.updateUser(it) } },
//            deleteUser = { scope.launch { repository.deleteUser(it) } },
//        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TenantView(
    mpesaStkPushClient: MpesaStkPushClient,
    navController: NavController,
    onGeneratePdf: () -> Unit
) {
    var showAddHouseDialog by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Tenant")
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

                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        onGeneratePdf()
//                        showAddHouseDialog = !showAddHouseDialog
                    }
                },
                shape = CircleShape
            ) {
                Text(
                    text = "Generate Your Payments Report"
                )
            }
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TenantDetails(
                    modifier = Modifier
                        .padding(10.dp),
                    navController = navController
                )
            }

            items(mastuff) { data ->
                DataView(
                    stuff = data,
                    mpesaStkPushClient = mpesaStkPushClient
                )
            }
        }

        if (showAddHouseDialog) {
            AddRouteDialog(
                onDismissRequest = {
                    showAddHouseDialog = !showAddHouseDialog
                },
                onConfirmation = {
                    showAddHouseDialog = !showAddHouseDialog
                    coroutineScope.launch {
//                        routeViewModel.addRoute()
                    }
                }
            )
        }

    }
}

@Composable
fun TenantDetails(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(Res.drawable.boyImage),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Text(
                text = "Joses Murungi"
            )
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {

                Column(
                    modifier = Modifier
                        .width(80.dp)
                        .clickable{
                            navController.navigate(
                                PaymentHistoryUi(tenantId = "")
                            )
                        },
//                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(Res.drawable.payHistory),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = "Payment History",
                        textAlign = TextAlign.Center
                    )

                }
                Column(
                    modifier = Modifier
                        .width(80.dp)
                        .clickable{
                            navController.navigate(
                                RentStatusUi(tenantId = "")
                            )
                        },
//                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(Res.drawable.PaymentImg),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = "Rent Status",
                        textAlign = TextAlign.Center
                    )

                }
                Column(
                    modifier = Modifier
                        .width(80.dp)
                        .clickable{
                            navController.navigate(
                                UpcomingBillUi(tenantId = "")
                            )
                        },
//                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(Res.drawable.BillImg),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = "Upcoming Bill",
                        textAlign = TextAlign.Center
                    )

                }

            }
        }
    }
}

@Composable
fun DataView(
    stuff: String,
    mpesaStkPushClient: MpesaStkPushClient
) {
    val coroutineScope = rememberCoroutineScope()
    var showProgressIndicator by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(20.dp),
    ) {
        Column {
            Image(
                painterResource(Res.drawable.Aptmnt_Image),
                contentDescription = "Apartment",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
            )
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Amani Apartment"
                )
                Text(
                    text = "House 1B"
                )
                Row {
                    Text(
                        text = "PayBill: "
                    )
                    Text(
                        text = "247247"
                    )
                }
                Row {
                    Text(
                        text = "Accnt. No.: "
                    )
                    Text(
                        text = "221053"
                    )
                }
                Button(
                    onClick = {
                        coroutineScope.launch {
                            try {
                                showProgressIndicator = !showProgressIndicator
                                // Await the completion of mpesaStkPushClient.sendStkPush
                                println(mpesaStkPushClient.initiateStkPush())
                            } catch (e: Exception) {
                                println(e)
                            } finally {
                                showProgressIndicator = !showProgressIndicator
                            }
                        }
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = if (showProgressIndicator) "Processing..." else "Click to Pay"
                        )
                        if (showProgressIndicator) {
                            Spacer(modifier = Modifier.width(8.dp)) // Add spacing
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp), // Adjust size
                                strokeWidth = 2.dp
                            )
                        }
                    }
                }
            }
        }
    }
}

val mastuff = listOf(
    "rwer",
)
