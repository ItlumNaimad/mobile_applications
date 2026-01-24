package pbs.edu.lab10

/**
 * Typ wyliczeniowy służący do nawigacji po ekranach.
 * Zmieniono nazwy z Movie na Particle zgodnie z nową tematyką.
 */
enum class ParticleScreens {
    HomeScreen,
    DetailsScreen;

    companion object {
        const val PARTICLE_ARG = "particle"

        fun fromRoute(route: String?): ParticleScreens = when (route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            DetailsScreen.name -> DetailsScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}
