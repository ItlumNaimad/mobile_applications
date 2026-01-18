package pbs.edu.lab9

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Komponent UI reprezentujący pojedynczy wiersz filmu na liście.
 * Wyświetla ikonę oraz tytuł filmu wewnątrz zaokrąglonej karty.
 * Jest opatrzony adnotacją @Composable co oznacza, że funkcja ta opisuje fragment
 * interfejsu użytkownika i może być używana wewnątrz innych funkcji kompozytowalnych.
 *
 * @param movie - Tytuł filmu do wyświetlenia.
 * @param onItemClick - Funkcja callback wywoływana po kliknięciu w wiersz,
 * przyjmująca tytuł klikniętego filmu jako argument.
 * @see Card - Komponent karty z biblioteki Material Design.
 */
@Composable
fun MovieRow(
    movie: String,
    onItemClick: (String) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clickable { onItemClick(movie) },
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
                shape = RoundedCornerShape(corner = CornerSize(10.dp)), // Prostokąt z zaokrągleniem
                shadowElevation = 4.dp
            ) {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Movie Image"
                )
            }
            Text(
                text = movie,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}