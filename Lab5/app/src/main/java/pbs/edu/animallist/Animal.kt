package pbs.edu.animallist

/**
 * Model danych reprezentujący zwierzę.
 *
 * @property name Nazwa zwierzęcia.
 * @property image Identyfikator zasobu graficznego (R.drawable...) dla zdjęcia zwierzęcia.
 */
data class Animal(
    var name: String,
    var image: Int
)