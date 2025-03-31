package com.joses.mali.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.joses.mali.landlord.domain.AddTenant

@Composable
fun BottomSheetContent(
    user: AddTenant? = null,
    onSave: (AddTenant) -> Unit,
    onDelete: (AddTenant?) -> Unit
) {
    var name by remember { mutableStateOf(user?.name ?: "") }
    var unitAllocated by remember { mutableStateOf(user?.unitAllocated ?: "") }
    var phoneNumber by remember { mutableStateOf(user?.phoneNumber ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = { name = it },
            singleLine = true,
            label = { Text("Name") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = unitAllocated,
            onValueChange = { unitAllocated = it },
            singleLine = true,
            label = { Text("unitAllocated") },
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            singleLine = true,
            label = { Text("phoneNumber") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // 🔹 Number-only keyboard
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            singleLine = true,
            label = { Text("email") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { onSave(AddTenant(user?.id ?: "", name, unitAllocated, phoneNumber, email)) }) {
                Text(text = if (user == null) "Save" else "Update")
            }
            Button(onClick = { onDelete(user) }) {
                Text("Remove tenant")
            }
        }
    }
}
