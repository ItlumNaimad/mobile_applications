package pbs.edu.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import pbs.edu.project.data.AppDatabase
import pbs.edu.project.data.PhotoRepository
import pbs.edu.project.navigation.AppNavigation
import pbs.edu.project.ui.theme.ProjectTheme
import pbs.edu.project.utils.LocationService
import pbs.edu.project.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inicjalizacja Bazy Danych
        val database = AppDatabase.getDatabase(this)
        
        // 2. Inicjalizacja Repozytorium
        val repository = PhotoRepository(database.photoDao())

        // 3. Inicjalizacja ViewModelu z Factory
        val viewModel: HomeViewModel by viewModels {
            HomeViewModel.provideFactory(repository)
        }

        // 4. Inicjalizacja LocationService
        val locationService = LocationService(this)

        setContent {
            ProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 5. Uruchomienie nawigacji
                    AppNavigation(
                        viewModel = viewModel,
                        locationService = locationService
                    )
                }
            }
        }
    }
}
