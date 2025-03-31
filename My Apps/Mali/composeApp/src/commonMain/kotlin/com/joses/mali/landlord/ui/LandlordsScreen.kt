package com.joses.mali.landlord.ui

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.joses.mali.landlord.domain.LandlordUser
import com.joses.mali.landlord.repository.LandlordRepository
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import mali.composeapp.generated.resources.Aptmnt_Image
import mali.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Serializable
data class LandlordsScreenRoute(
    val name: String
)

@Composable
@Preview
fun LandlordsScreen(
    navController: NavController,
    name: String
) {
    MaterialTheme {
        val landlordRepository = remember { LandlordRepository() }
        val scope = rememberCoroutineScope()

        LandlordView(
            name = name,
            navController = navController,
            addApartment = { scope.launch { landlordRepository.addApartment(it) } },
            updateApartment = { scope.launch { landlordRepository.updateApartment(it) } },
            deleteApartment = { scope.launch { landlordRepository.deleteApartment(it) } },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandlordView(
    name: String,
    navController: NavController,
    addApartment: (LandlordUser) -> Unit,
    updateApartment: (LandlordUser) -> Unit,
    deleteApartment: (LandlordUser) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var selectedUser by remember { mutableStateOf<LandlordUser?>(null) }
    var showBottomSheet by remember { mutableStateOf(false) }

    val landlordRepository = remember { LandlordRepository() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Landlord")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }

                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showBottomSheet = true }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {

        val apartments by landlordRepository.getApartments().collectAsState(emptyList())

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                LandLordDetails(
                    modifier = Modifier
                        .padding(10.dp),
                    apartments = apartments,
                    name = name
                )
            }

            items(apartments) { apartment ->
                ApartmentDetails(
                    apartment = apartment,
                    navController = navController,
                    clicked = {
                        selectedUser = it
                        showBottomSheet = true
                    }
                )
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            BottomSheetLandlordContent(
                user = selectedUser,
                onSave = { user ->
                    scope.launch {
                        if (selectedUser == null) {
                            addApartment(user)
                        } else {
                            updateApartment(user)
                        }
                        sheetState.hide()
                    }.invokeOnCompletion {
                        showBottomSheet = false
                        selectedUser = null
                    }
                },
                onDelete = { user ->
                    scope.launch {
                        user?.let { deleteApartment(it) }
                        sheetState.hide()
                    }.invokeOnCompletion {
                        showBottomSheet = false
                        selectedUser = null
                    }
                }
            )
        }
    }
}

@Composable
fun LandLordDetails(
    modifier: Modifier = Modifier,
    apartments: List<LandlordUser>,
    name: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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
                    text = name
                )
            }
            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = modifier
                ) {
                    Text(
                        text = "Properties: "
                    )
                    Text(
                        text = "${apartments.size}"
                    )
                }

            }
        }
    }
}


