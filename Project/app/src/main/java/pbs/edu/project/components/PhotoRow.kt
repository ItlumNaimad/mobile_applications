package pbs.edu.project.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import pbs.edu.project.model.PhotoEntry
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PhotoRow(
    photo: PhotoEntry,
    onItemClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clickable { onItemClick(photo.id) },
        shape = RoundedCornerShape(CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            // 1. Miniatura zdjęcia (Coil)
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RoundedCornerShape(corner = CornerSize(8.dp)), // Lekkie zaokrąglenie zdjęcia
                tonalElevation = 4.dp
            ) {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(photo.uri)
                        .size(Size.ORIGINAL) // Lub konkretny rozmiar dla optymalizacji
                        .crossfade(true)
                        .build()
                )
                
                Image(
                    painter = painter,
                    contentDescription = "Photo thumbnail",
                    contentScale = ContentScale.Crop, // Crop, żeby wypełnić kwadrat
                    modifier = Modifier.fillMaxSize()
                )
            }

            // 2. Kolumna z informacjami
            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    text = photo.title,
                    style = MaterialTheme.typography.titleMedium
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "Lat: ${String.format("%.4f", photo.latitude)}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Lng: ${String.format("%.4f", photo.longitude)}",
                    style = MaterialTheme.typography.bodySmall
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                // Formatowanie daty
                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
                val dateString = dateFormat.format(Date(photo.timestamp))
                
                Text(
                    text = dateString,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}
