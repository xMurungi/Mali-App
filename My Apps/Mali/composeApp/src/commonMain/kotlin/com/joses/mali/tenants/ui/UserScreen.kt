//package com.joses.mali.tenants.ui
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import com.joses.mali.components.BottomSheetContent
//import com.joses.mali.components.UserList
//import com.joses.mali.tenants.domain.User
//import kotlinx.coroutines.launch
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun UserScreenContent(
//    users: List<User>,
//    addUser: (User) -> Unit,
//    updateUser: (User) -> Unit,
//    deleteUser: (User) -> Unit,
//) {
//    val sheetState = rememberModalBottomSheetState()
//    val scope = rememberCoroutineScope()
//    var selectedUser by remember { mutableStateOf<User?>(null) }
//    var showBottomSheet by remember { mutableStateOf(false) }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(text = "User List")
//                }
//            )
//        },
//        floatingActionButton = {
//            FloatingActionButton(onClick = { showBottomSheet = true }) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Add"
//                )
//            }
//        },
//        floatingActionButtonPosition = FabPosition.End
//    ) { innerPadding ->
//        UserList(
//            users = users,
//            modifier = Modifier.padding(innerPadding)
//        ) { user ->
//            selectedUser = user
//            showBottomSheet = true
//        }
//
//        if (showBottomSheet) {
//            ModalBottomSheet(
//                onDismissRequest = {
//                    showBottomSheet = false
//                },
//                sheetState = sheetState
//            ) {
//                BottomSheetContent(
//                    user = selectedUser,
//                    onSave = { user ->
//                        scope.launch {
//                            if (selectedUser == null) {
//                                addUser(user)
//                            } else {
//                                updateUser(user)
//                            }
//                            sheetState.hide()
//                        }.invokeOnCompletion {
//                            showBottomSheet = false
//                            selectedUser = null
//                        }
//                    },
//                    onDelete = { user ->
//                        scope.launch {
//                            user?.let { deleteUser(it) }
//                            sheetState.hide()
//                        }.invokeOnCompletion {
//                            showBottomSheet = false
//                            selectedUser = null
//                        }
//                    }
//                )
//            }
//        }
//    }
//}
