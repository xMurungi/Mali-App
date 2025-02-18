package com.joses.mali.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.serialization.Serializable
import mali.composeapp.generated.resources.Aptmnt_Image
import mali.composeapp.generated.resources.Res
import mali.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Serializable
object LandlordsScreen

@Composable
@Preview
fun LandlordsScreen(
    navController: NavController
) {
    MaterialTheme {
        LandlordView()
    }
}

@Composable
fun LandlordView() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Landlord")
                }
            )
        }
    ) {
       val padd = it

        LazyColumn {
            items(mastuff) { data ->
                DataView(
                    stuff = data
                )
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
            .padding(50.dp)
    ) {
        Column {
            Image(
                painterResource(Res.drawable.Aptmnt_Image),
                contentDescription = "Apartment",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
            )
            Text(
                text = "Amani Apartment"
            )
            Text(
                text = "14 Units"
            )
            Text(
                text = "14 Tenants"
            )
            Text(
                text = stuff
            )
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
