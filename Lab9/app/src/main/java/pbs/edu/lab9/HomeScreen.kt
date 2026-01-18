package pbs.edu.lab9

import android.util.Log
import android.widget.Toast // Dodany import do dymków
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext // Dodany import do kontekstu
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/**
 * Główny ekran aplikacji wyświetlający listę filmów w układzie [Scaffold].
 * Zawiera górny pasek aplikacji (TopAppBar) oraz dolny pasek (BottomAppBar).
 *
 * Adnotacja @OptIn(ExperimentalMaterial3Api::class) informuje kompilator, że świadomie
 * używamy eksperymentalnych komponentów z biblioteki Material 3 (np. TopAppBar).
 * Jest to konieczne, ponieważ te elementy API mogą ulec zmianie w przyszłych wersjach.
 *
 * @param navController - Kontroler nawigacji umożliwiający przechodzenie między ekranami.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movies TopAppBar Wykład") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Magenta.copy(alpha = 0.1f)
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Blue.copy(alpha = 0.1f),
                tonalElevation = 9.dp
            ) {
                Text(text = "Movies bottomBar Wykład", modifier = Modifier.padding(start = 12.dp))
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            MainContent(navController = navController)
        }
    }
}

/**
 * Komponent definiujący główną zawartość ekranu domowego.
 * Wyświetla listę filmów przy użyciu [LazyColumn], co pozwala na wydajne przewijanie dużych zbiorów danych.
 *
 * Funkcja wykorzystuje [LocalContext.current] do uzyskania kontekstu Androida, co jest niezbędne
 * do wyświetlenia powiadomienia [Toast] po kliknięciu w element listy.
 *
 * @param navController - Kontroler nawigacji (przygotowany pod przyszłą nawigację do detali).
 * @param movieList - Lista tytułów filmów do wyświetlenia (domyślnie zawiera predefiniowany zestaw).
 */
@Composable
fun MainContent(
    navController: NavController,
    movieList: List<String> = listOf(
        "Avatar", "555", "Harry Potter", "Life", "Lolek", "Bolek",
        "Krecik", "Idą Święta", "Wykład"
    )
) {
    // Pobieramy kontekst, aby móc wyświetlić Toast
    val context = LocalContext.current

    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = movieList) { movie ->
                MovieRow(movie = movie) { movieName ->
                    // 1. To było w instrukcji - zapis do konsoli (niewidoczne na ekranie)
                    Log.d("TAG", "MainContent: $movieName")

                    // 2. Dodatek: Wyświetlenie powiadomienia Toast (dymka) na ekranie
                    Toast.makeText(context, "Kliknięto: $movieName", Toast.LENGTH_SHORT).show()

                    // Tu w przyszłości będzie nawigacja:
                    // navController.navigate(...)
                }
            }
        }
    }
}