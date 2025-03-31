package com.joses.mali.tenants

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
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joses.mali.ui.AddRouteDialog
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import mali.composeapp.generated.resources.Aptmnt_Image
import mali.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun LandlordsScreen(
    navController: NavController
) {
    MaterialTheme {
        LandlordView()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandlordView() {
    var showAddHouseDialog by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Landlord")
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
                    contentDescription = "Add house"
                )
            }
        }
    ) {
        val padd = it

        LazyColumn {
            item {
                LandLordDetails(
                    modifier = Modifier
                        .padding(20.dp)
                )
            }

            items(mastuff) { data ->
                DataView(
                    stuff = data
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
fun LandLordDetails(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
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
                Row {
                    Text(
                        text = "Properties: "
                    )
                    Text(
                        text = "${mastuff.size}"
                    )
                }

            }
        }
    }
}

@Composable
fun DataView(
    stuff: String
) {
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
                    text = "14 Units"
                )
                Text(
                    text = "14 Tenants"
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
                Text(
                    text = stuff
                )
            }
        }
    }
}

val mastuff = listOf(
    "rwer",
    "ewre",
    "ihoeow",
    "rwer",
    "ewre",
    "ihoeow",
    "rwer",
    "ewre",
    "ihoeow",
    "rwer"
)
