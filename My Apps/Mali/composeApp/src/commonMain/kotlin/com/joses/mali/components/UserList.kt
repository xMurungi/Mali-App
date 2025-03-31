package com.joses.mali.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joses.mali.landlord.domain.AddTenant
import com.joses.mali.tenants.domain.User

@Composable
fun UserList(
    users: List<AddTenant>,
    navController: NavController,
    modifier: Modifier = Modifier,
    userClicked: (AddTenant) -> Unit)
{
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(
            users,
            key = { it.id }
        ) { user ->
            UserItem(
                user = user,
                navController = navController
            ) {
                userClicked(user)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
