package pbs.edu.lab10

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import pbs.edu.lab10.model.Particle

@Composable
fun ParticleRow(
    particle: Particle,
    onItemClick: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300
                )
            )
            .clickable {
                // Optional: Navigate on card click, or just expand
                // onItemClick(particle.name) 
                expanded = !expanded
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Surface(
                    modifier = Modifier
                        .padding(12.dp)
                        .size(100.dp),
                    shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                    shadowElevation = 4.dp
                ) {
                    // Use Coil to load image
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(particle.imageUrls.firstOrNull())
                            .crossfade(true)
                            .build(),
                        contentDescription = "Particle Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Column(modifier = Modifier.padding(4.dp)) {
                    Text(
                        text = particle.name,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = "Symbol: ${particle.symbol}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Expand",
                    modifier = Modifier
                        .padding(25.dp)
                        .clickable { expanded = !expanded }
                )
            }

            AnimatedVisibility(
                visible = expanded,
                enter = slideInVertically() + expandVertically() + fadeIn(),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Description:",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = particle.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { onItemClick(particle.name) },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "More Details")
                    }
                }
            }
        }
    }
}