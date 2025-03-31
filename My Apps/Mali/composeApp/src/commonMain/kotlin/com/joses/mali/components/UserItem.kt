package com.joses.mali.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joses.mali.landlord.domain.AddTenant
import com.joses.mali.landlord.ui.UnitPaymentsHistoryUi
import com.joses.mali.tenants.domain.User

@Composable
fun UserItem(
    user: AddTenant,
    navController: NavController,
    clicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { clicked() }
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
//        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = user.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Unit: ${user.unitAllocated}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Phone: ${user.phoneNumber}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Email: ${user.email}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Date joined: 28th May 2023", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        navController.navigate(
                            route = UnitPaymentsHistoryUi(
                                tenantId = user.id
                            )
                        )
                    },
//                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "View Payments History")
                }

                Box(
                    modifier = Modifier
                        .background(
                            Color(0xFF2E7D32).copy(alpha = 0.15f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Paid",
                        color = Color(0xFF388E3C),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
