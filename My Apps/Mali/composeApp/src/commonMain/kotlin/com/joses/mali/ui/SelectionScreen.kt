package com.joses.mali.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.joses.mali.landlord.ui.LandlordsScreen
import com.joses.mali.landlord.ui.LandlordsScreenRoute
import com.joses.mali.tenants.ui.TenantsScreen
import com.joses.mali.tenants.ui.TenantsScreen2
import kotlinx.serialization.Serializable

@Serializable
data class SelectionScreen(
    val id: String = "",
    val name: String = "",
    val profilePicUrl: String = ""
)

@Composable
fun SelectionScreen(
    navController: NavController,
    id: String = "",
    name: String = "",
    profilePicUrl: String = ""
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
//            backgroundColor = MaterialTheme.colors.background,
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(defaultElevation = 50.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 50.dp)
                    .fillMaxWidth()
            ) {

                Text(
                    text = "Welcome to Mali App, $name",
                    fontSize = 25.sp
                )

                Spacer(
                    modifier = Modifier.padding(8.dp)
                )

                Button(
                    onClick = {
                        navController.navigate(
                            LandlordsScreenRoute(
                                name = name
                            )
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
                            TenantsScreen2
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
