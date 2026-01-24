package pbs.edu.lab10

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CompareArrows
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * Komponent wyświetlający pojedynczy wiersz z nazwą cząsteczki.
 * Teraz z dynamiczną ikoną i kolorem zależnym od typu cząsteczki.
 */
@Composable
fun ParticleRow(
    particle: String,
    onItemClick: (String) -> Unit = {}
) {
    val (icon, color) = getParticleVisuals(particle)

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clickable { onItemClick(particle) },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                shadowElevation = 4.dp,
                color = color.copy(alpha = 0.15f) // Delikatne tło w kolorze cząsteczki
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Particle Icon",
                    tint = color,
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxSize()
                )
            }
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = particle,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Tap for details",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}

/**
 * Funkcja pomocnicza mapująca nazwę cząsteczki na ikonę i kolor.
 */
private fun getParticleVisuals(name: String): Pair<ImageVector, Color> {
    return when (name) {
        "Electron" -> Pair(Icons.Default.Bolt, Color(0xFFFFD700))       // Żółty (Elektryczność)
        "Proton" -> Pair(Icons.Default.AddCircle, Color(0xFF2196F3))    // Niebieski (Plus)
        "Neutron" -> Pair(Icons.Default.Lens, Color(0xFF9E9E9E))        // Szary (Neutralny)
        "Photon" -> Pair(Icons.Default.WbSunny, Color(0xFFFF5722))      // Pomarańczowy (Światło)
        "Quark" -> Pair(Icons.Default.Extension, Color(0xFF9C27B0))     // Fioletowy (Element budulcowy)
        "Gluon" -> Pair(Icons.Default.Link, Color(0xFF4CAF50))          // Zielony (Klej/Wiązanie)
        "Higgs Boson" -> Pair(Icons.Default.AutoAwesome, Color(0xFFE91E63)) // Różowy (Boska cząstka)
        "Neutrino" -> Pair(Icons.Default.BlurOn, Color(0xFF00BCD4))     // Cyjan (Duch/Przenikanie)
        "Muon" -> Pair(Icons.Default.FlashOn, Color(0xFF3F51B5))        // Ciemny niebieski
        "Tau" -> Pair(Icons.Default.FlashOn, Color(0xFF673AB7))         // Ciemny fiolet
        "Z Boson" -> Pair(Icons.AutoMirrored.Filled.CompareArrows, Color(0xFF795548)) // Brązowy (Oddziaływanie)
        "W Boson" -> Pair(Icons.AutoMirrored.Filled.CompareArrows, Color(0xFF795548)) // Brązowy
        else -> Pair(Icons.Default.Science, Color.Black)                // Domyślny
    }
}
