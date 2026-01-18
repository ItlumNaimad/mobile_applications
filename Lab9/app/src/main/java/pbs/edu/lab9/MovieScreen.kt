package pbs.edu.lab9

/**
 * Typ wyliczeniowy służący do nawigacji po ekranach.
 * @constructor tworzy nowy typ wyliczeniowy
 * @param route - nazwa ekranu typu String
 * @property HomeScreen - ekran główny aplikacji.
 * @property DetailsScreen - ekran szczegółowy filmu.
 * @throws IllegalArgumentException jeżeli podany route nie istnieje
 */
enum class MovieScreens {
    HomeScreen,
    DetailsScreen;

    companion object {
        /**
         * Funkcja zwracająca typ wyliczeniowy na podstawie podanego route.
         * @param route - nazwa ekranu typu String
         */
        fun fromRoute(route: String?): MovieScreens = when (route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            DetailsScreen.name -> DetailsScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}