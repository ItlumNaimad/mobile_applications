package pbs.edu.project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pbs.edu.project.screens.camera.AddPhotoScreen
import pbs.edu.project.screens.details.DetailsScreen
import pbs.edu.project.screens.home.HomeScreen
import pbs.edu.project.utils.LocationService
import pbs.edu.project.viewmodel.HomeViewModel

enum class AppScreens {
    Home,
    AddPhoto,
    Details
}

@Composable
fun AppNavigation(
    viewModel: HomeViewModel,
    locationService: LocationService
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.Home.name
    ) {
        // Ekran główny (Lista)
        composable(route = AppScreens.Home.name) {
            HomeScreen(
                navController = navController,
                viewModel = viewModel,
                onAddClick = {
                    navController.navigate(AppScreens.AddPhoto.name)
                }
            )
        }

        // Ekran robienia zdjęcia (Kamera)
        composable(route = AppScreens.AddPhoto.name) {
            AddPhotoScreen(
                navController = navController,
                viewModel = viewModel,
                locationService = locationService
            )
        }

        // Ekran szczegółów
        composable(
            route = "${AppScreens.Details.name}/{photoId}",
            arguments = listOf(navArgument("photoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val photoId = backStackEntry.arguments?.getInt("photoId") ?: 0
            DetailsScreen(
                navController = navController,
                viewModel = viewModel,
                photoId = photoId
            )
        }
    }
}
