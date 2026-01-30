package pbs.edu.project.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import pbs.edu.project.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: HomeViewModel,
    photoId: Int
) {
    val photos by viewModel.photos.collectAsState()
    val photo = photos.find { it.id == photoId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(photo?.title ?: "Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (photo != null) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                // Duże zdjęcie
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(photo.uri)
                            .crossfade(true)
                            .build()
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )

                // Szczegóły
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Coordinates:", style = MaterialTheme.typography.titleMedium)
                    Text("Latitude: ${photo.latitude}")
                    Text("Longitude: ${photo.longitude}")
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Timestamp: ${photo.timestamp}")
                    
                    Button(
                        onClick = {
                            viewModel.deleteEntry(photo)
                            navController.popBackStack()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Delete Photo")
                    }
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text("Photo not found")
            }
        }
    }
}
