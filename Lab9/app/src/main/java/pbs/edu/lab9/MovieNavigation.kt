package pbs.edu.lab9

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pbs.edu.lab9.MovieScreens

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name
    ) {
        composable(MovieScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        // Placeholder na przyszły DetailsScreen (z Lab 9 cz. 2)
        composable(MovieScreens.DetailsScreen.name) {
            // Tu będzie DetailsScreen
        }
    }
}