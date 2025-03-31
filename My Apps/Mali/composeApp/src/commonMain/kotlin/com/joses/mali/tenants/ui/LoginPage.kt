package com.joses.mali.tenants.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.joses.mali.ui.SelectionScreen
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import com.mmk.kmpauth.google.GoogleButtonUiContainer
import com.mmk.kmpauth.uihelper.google.GoogleSignInButton
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import mali.composeapp.generated.resources.Aptmnt_Image
import mali.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource

@Serializable
object LoginPageUi

@Composable
fun LoginPage(
    navController: NavController
) {
    var authReady by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var userData by remember { mutableStateOf<GoogleUser?>(null) }

    LaunchedEffect(Unit) {
        GoogleAuthProvider.create(
            credentials = GoogleAuthCredentials(
                serverId = "251568000355-v3ft9i74lreel0k9ngjprsp9jlrkh9te.apps.googleusercontent.com"
            )
        )
        authReady = true
    }

    if (authReady) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // App Logo (Replace with your own drawable)
                Image(
                    painter = painterResource(Res.drawable.Aptmnt_Image),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .clip(RoundedCornerShape(26.dp))
                        .size(400.dp)
                        .padding(bottom = 16.dp)
                )

                // Welcome Text
                Text(
                    text = "Welcome to Mali Rent Manager",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF333333)
                )

                Text(
                    text = "Easily manage your rent payments with a single sign-in",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
//                        .shadow(10.dp, RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        GoogleButtonUiContainer(
                            onGoogleSignInResult = { googleUser ->
                                if (googleUser != null) {
                                    userData = GoogleUser(
                                        id = googleUser.idToken ?: "",
                                        name = googleUser.displayName ?: "Unknown",
                                        profilePicUrl = googleUser.profilePicUrl ?: ""
                                    )
                                    coroutineScope.launch {
                                        navController.navigate(
                                            SelectionScreen(
                                                userData!!.id,
                                                userData!!.name,
                                                userData!!.profilePicUrl
                                            )
                                        )
                                    }
                                }
                            }
                        ) {
                            GoogleSignInButton(
                                onClick = {
                                    this.onClick()
                                }
                            )
                        }
                    }

                }
            }
        }
    }
}

@Serializable
data class GoogleUser(
    val id: String = "",
    val name: String = "",
    val profilePicUrl: String = ""
)

