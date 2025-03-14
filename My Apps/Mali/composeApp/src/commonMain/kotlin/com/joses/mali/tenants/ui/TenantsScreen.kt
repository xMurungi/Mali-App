package com.joses.mali.tenants.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joses.mali.tenants.network.MpesaStkPushClient
import com.joses.mali.tenants.network.createHttpClient
import com.joses.mali.ui.AddRouteDialog
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import mali.composeapp.generated.resources.Aptmnt_Image
import mali.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Serializable
object TenantsScreen

@Composable
@Preview
fun TenantsScreen(
    navController: NavController,
    mpesaStkPushClient: MpesaStkPushClient
) {
    MaterialTheme {

        TenantView(
            mpesaStkPushClient = mpesaStkPushClient
        )

    }
}

@Composable
fun TenantView(
    mpesaStkPushClient: MpesaStkPushClient
) {
    var showAddHouseDialog by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Tenant")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        showAddHouseDialog = !showAddHouseDialog
                    }
                },
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Tenant"
                )
            }
        }
    ) {
        val padd = it

        LazyColumn {
            item {
                TenantDetails(
                    modifier = Modifier
                        .padding(20.dp)
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
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp
    ) {
        Row(
            modifier = modifier
        ) {
            Column {
                Image(
                    painter = painterResource(Res.drawable.Aptmnt_Image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = "John Doe"
                )
            }
            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.Aptmnt_Image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = "Payment History"
                )

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

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),
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
                                // Await the completion of mpesaStkPushClient.sendStkPush
                                println(mpesaStkPushClient.initiateStkPush())

                            } catch (e: Exception) {
                                println(e)
                            } finally {

                            }
                        }
                    }
                ) {
                    Text(
                        text = "Click to Pay"
                    )
                }
            }
        }
    }
}

val mastuff = listOf(
    "rwer",
)
