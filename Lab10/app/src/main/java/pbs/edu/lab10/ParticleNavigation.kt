package pbs.edu.lab10

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pbs.edu.lab10.screens.details.DetailsScreen

@Composable
fun ParticleNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ParticleScreens.HomeScreen.name
    ) {
        // Ekran główny
        composable(ParticleScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        // Ekran szczegółów z parametrem
        composable(
            route = "${ParticleScreens.DetailsScreen.name}/{${ParticleScreens.PARTICLE_ARG}}",
            arguments = listOf(navArgument(ParticleScreens.PARTICLE_ARG) { type = NavType.StringType })
        ) { backStackEntry ->
            val particleName = backStackEntry.arguments?.getString(ParticleScreens.PARTICLE_ARG)
            DetailsScreen(navController = navController, particleData = particleName)
        }
    }
}
