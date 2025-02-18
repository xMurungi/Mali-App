package com.joses.mali.navigation

import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joses.mali.ui.LandlordsScreen
import com.joses.mali.ui.SelectionScreen
import com.joses.mali.ui.TenantsScreen

@Composable
fun MaliApp() {

    val navController = rememberNavController()
    val startDestinationString = SelectionScreen

    NavHost(
        navController = navController,
        startDestination = startDestinationString,
        Modifier
            .background(MaterialTheme.colors.background)
    ) {

        composable<SelectionScreen> {
            SelectionScreen(
                navController = navController
            )
        }

        composable<LandlordsScreen> {
            LandlordsScreen(
                navController = navController
            )
        }

        composable<TenantsScreen> {
            TenantsScreen(
                navController = navController
            )
        }

    }

}
