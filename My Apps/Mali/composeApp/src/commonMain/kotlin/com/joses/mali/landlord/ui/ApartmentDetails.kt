package com.joses.mali.landlord.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joses.mali.landlord.domain.AddTenant
import com.joses.mali.landlord.domain.LandlordUser
import com.joses.mali.tenants.ui.TenantsScreen
import mali.composeapp.generated.resources.Aptmnt_Image
import mali.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource

@Composable
fun ApartmentDetails(
    apartment: LandlordUser,
    clicked: (LandlordUser) -> Unit,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    AllTenantsRoute(apartmentId = apartment.id)
                )
            }
            .padding(16.dp),
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
                    text = apartment.apartmentName
                )
                Text(
                    text = "${apartment.units} Units"
                )
                Text(
                    text = "${apartment.tenants} Tenants"
                )
                Row {
                    Text(
                        text = "Till Number: "
                    )
                    Text(
                        text = apartment.tillNumber
                    )
                }
                Row {
                    Text(
                        text = "Landlord. No.: "
                    )
                    Text(
                        text = apartment.phoneNumber
                    )
                }
            }
            Button(
                onClick = {
                    clicked(
                        apartment.copy()
                    )
                }
            ) {
                Text(
                    text = "Edit apartment details"
                )
            }
            Button(
                onClick = {
                    navController.navigate(
                        AllTenantsRoute(apartmentId = apartment.id)
                    )
                }
            ) {
                Text(
                    text = "View all tenants"
                )
            }
        }
    }
}
