package com.joses.mali.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Serializable
object SelectionScreen

@Composable
fun SelectionScreen(
    navController: NavController
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .padding(vertical = 50.dp)
                .fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.background,
            shape = MaterialTheme.shapes.medium,
            elevation = 50.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 50.dp)
            ) {
                Text(
                    text = "Welcome to Mali App",
                    fontSize = 25.sp
                )

                Button(
                    onClick = {
                        navController.navigate(
                            LandlordsScreen
                        )
                    },
                ) {
                    Text(
                        text = "Go to Landlords screen"
                    )
                }

                Button(
                    onClick = {
                        navController.navigate(
                            TenantsScreen
                        )
                    },
                ) {
                    Text(
                        text = "Go to Tenants screen"
                    )
                }
            }

        }
    }
}
