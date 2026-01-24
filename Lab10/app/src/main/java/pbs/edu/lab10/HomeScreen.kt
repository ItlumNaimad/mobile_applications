package pbs.edu.lab10

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pbs.edu.lab10.model.Particle
import pbs.edu.lab10.model.getParticles

/**
 * Główny ekran aplikacji wyświetlający listę cząsteczek elementarnych.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Quantum Particles") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Cyan.copy(alpha = 0.2f)
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Blue.copy(alpha = 0.1f),
                tonalElevation = 9.dp
            ) {
                Text(text = "Elementary Particles Lab", modifier = Modifier.padding(start = 12.dp))
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            MainContent(navController = navController)
        }
    }
}

@Composable
fun MainContent(
    navController: NavController,
    particleList: List<Particle> = getParticles()
) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = particleList) { particle ->
                ParticleRow(particle = particle) { particleName ->
                    Log.d("TAG", "Clicked: $particleName")
                    // Nawigacja do ekranu szczegółów z parametrem
                    navController.navigate("${ParticleScreens.DetailsScreen.name}/$particleName")
                }
            }
        }
    }
}