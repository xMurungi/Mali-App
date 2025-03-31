package com.joses.mali.landlord.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joses.mali.landlord.domain.LandlordUser

@Composable
fun BottomSheetLandlordContent(
    user: LandlordUser? = null,
    onSave: (LandlordUser) -> Unit,
    onDelete: (LandlordUser?) -> Unit
) {
    var aptName by remember { mutableStateOf(user?.apartmentName ?: "") }
    var units by remember { mutableStateOf(user?.units ?: "") }
    var tenants by remember { mutableStateOf(user?.tenants ?: "") }
    var tillNumber by remember { mutableStateOf(user?.tillNumber ?: "") }
    var phoneNumber by remember { mutableStateOf(user?.phoneNumber ?: "") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = aptName,
            onValueChange = { aptName = it },
            singleLine = true,
            label = { Text("aptName") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = units.toString(),
            onValueChange = { units = it },
            singleLine = true,
            label = { Text("Units") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = tenants.toString(),
            onValueChange = { tenants = it },
            singleLine = true,
            label = { Text("Tenants") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = tillNumber,
            onValueChange = { tillNumber = it },
            singleLine = true,
            label = { Text("tillNumber") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            singleLine = true,
            label = { Text("phoneNumber") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { onSave(LandlordUser(user?.id ?: "", aptName, units, tenants, tillNumber, phoneNumber)) }) {
                Text(text = if (user == null) "Save" else "Update")
            }
            Button(onClick = { onDelete(user) }) {
                Text("Delete")
            }
        }
    }
}
