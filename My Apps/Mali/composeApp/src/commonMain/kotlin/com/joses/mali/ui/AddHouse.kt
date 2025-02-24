package com.joses.mali.ui

import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
//import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
//import androidx.compose.material3.CardColors
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddRouteDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
//    routeViewModel: RouteViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val keyboardHeight = WindowInsets.ime.getBottom(LocalDensity.current)

    LaunchedEffect(key1 = keyboardHeight) {
        coroutineScope.launch {
            scrollState.scrollBy(keyboardHeight.toFloat())
        }
    }

    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .imePadding(),
            shape = RoundedCornerShape(16.dp),
//            colors = CardColors(
//                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
//                containerColor = MaterialTheme.colorScheme.secondaryContainer,
//                disabledContainerColor = MaterialTheme.colorScheme.background,
//                disabledContentColor = MaterialTheme.colorScheme.background
//            )
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .focusable()
                    .imePadding(),
//                    .imeNestedScroll(),
                userScrollEnabled = true
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .imePadding(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp, end = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(
                                onClick = {
                                    onDismissRequest()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close Route Dialog"
                                )
                            }
                            Text(
                                text = "Add Route",
                                fontWeight = FontWeight.Bold
                            )
                            TextButton(
                                onClick = {
                                    coroutineScope.launch {
//                                        routeViewModel.addRoute()
                                        onDismissRequest()
                                    }
                                },
                                content = {
                                    Text(
                                        text = "Save"
                                    )
                                },
                            )
                        }

                        CustomTextField(
                            placeholder = "Route name",
                            value = "routeViewModel.routeUiDetails.route.routeName",
                            onValueChange = {
//                                routeViewModel.onRouteNameChange(it)
                            }
                        )

                        Spacer(
                            modifier = Modifier.padding(8.dp)
                        )

                        RouteData(
                            title = "Driver details",
                            value1 = "routeViewModel.routeUiDetails.route.driverName",
                            value2 = "routeViewModel.routeUiDetails.route.driverTel",
                            placeholder1 = "Driver name",
                            placeholder2 = "Driver tel.",
                            onValueChange1 = {
//                                routeViewModel.onDriverNameChange(it)
                                             },
                            onValueChange2 = {
//                                routeViewModel.onDriverTelChange(it)
                            }
                        )

                        Spacer(
                            modifier = Modifier.padding(8.dp)
                        )

//                        HorizontalDivider(
//                            modifier = Modifier.padding(18.dp),
//                            color = MaterialTheme.colorScheme.onSecondaryContainer
//                        )

                        RouteData(
                            title = "Assistant details",
                            value1 = "routeViewModel.routeUiDetails.route.assistantName",
                            value2 = "routeViewModel.routeUiDetails.route.assistantTel",
                            placeholder1 = "Assistant name",
                            placeholder2 = "Assistant tel.",
                            onValueChange1 = {
//                                routeViewModel.onAssistantNameChange(it)
                                             },
                            onValueChange2 = {
//                                routeViewModel.onAssistantTelChange(it)
                            }
                        )

                        Spacer(
                            modifier = Modifier.padding(8.dp)
                        )

//                        HorizontalDivider(
//                            modifier = Modifier.padding(18.dp),
//                            color = MaterialTheme.colorScheme.onSecondaryContainer
//                        )

                        RouteData(
                            title = "Bus details",
                            value1 = "routeViewModel.routeUiDetails.route.busPlate",
                            value2 = null,
                            placeholder1 = "Bus plate",
                            placeholder2 = null,
                            onValueChange1 = {
//                                routeViewModel.onBusPlateChange(it)
                                             },
                            onValueChange2 = null
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            TextButton(
                                onClick = { onDismissRequest() },
                                modifier = Modifier.padding(8.dp),
                            ) {
                                Text("Dismiss")
                            }
                            TextButton(
                                onClick = { onConfirmation() },
                                modifier = Modifier.padding(8.dp),
                            ) {
                                Text("Confirm")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RouteData(
    modifier: Modifier = Modifier.imePadding(),
    title: String,
    value1: String,
    value2: String?,
    placeholder1: String,
    placeholder2: String?,
    onValueChange1: (String) -> Unit,
    onValueChange2: ((String) -> Unit?)?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
//        colors = CardColors(
//            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
//            containerColor = MaterialTheme.colorScheme.secondaryContainer,
//            disabledContainerColor = MaterialTheme.colorScheme.background,
//            disabledContentColor = MaterialTheme.colorScheme.background
//        )
    ) {
        Column {
            Text(
                text = title,
                fontFamily = FontFamily.SansSerif,
//                color = MaterialTheme.colorScheme.error
            )
            Column(
                modifier = Modifier
                    .padding(start = 28.dp)
                    .imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CustomTextField(
                    placeholder = placeholder1,
                    value = value1,
                    onValueChange = { onValueChange1(it) }
                )

                if (placeholder2 != null && value2 != null && onValueChange2 != null) {
                    CustomTextField(
                        placeholder = placeholder2,
                        value = value2,
                        onValueChange = {
                            onValueChange2(it)
                        }
                    )
                }
            }

        }
    }
}
