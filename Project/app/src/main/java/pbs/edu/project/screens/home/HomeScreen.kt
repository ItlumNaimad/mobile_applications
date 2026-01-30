package pbs.edu.project.screens.home

import android.Manifest
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import pbs.edu.project.components.PhotoRow
import pbs.edu.project.viewmodel.HomeViewModel

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel,
    onAddClick: () -> Unit // Callback do nawigacji na ekran kamery
) {
    // Obserwowanie listy zdjęć z ViewModelu
    val photos by viewModel.photos.collectAsState()

    // Uprawnienia wymagane do zrobienia zdjęcia i lokalizacji
    val cameraPermissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("PhotoJournal") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (cameraPermissionsState.allPermissionsGranted) {
                        onAddClick()
                    } else {
                        cameraPermissionsState.launchMultiplePermissionRequest()
                    }
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Take Photo")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(items = photos, key = { it.id }) { photo ->
                PhotoRow(
                    photo = photo,
                    onItemClick = { photoId ->
                        // Nawigacja do szczegółów (zakładam taką trasę w przyszłości)
                        navController.navigate("details/$photoId")
                    }
                )
            }
        }
    }
}
