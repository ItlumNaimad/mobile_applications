package pbs.edu.animallist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Random

/**
 * Główna aktywność aplikacji.
 * Generuje losową listę zwierząt i wyświetla ją w RecyclerView.
 */
class MainActivity : AppCompatActivity() {

    // Lista przechowująca obiekty Animal
    private val animalsList = ArrayList<Animal>()

    // Referencja do adaptera
    private lateinit var mAdapter: AnimalsAdapter

    // Flaga wyboru layoutu (true = Grid, false = Linear) - sterowanie z kodu jak w instrukcji
    private val wybor = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        // 1. Przygotowanie danych (Tablica możliwych zwierząt) [cite: 35-50]
        // UWAGA: Upewnij się, że masz te obrazki w res/drawable!
        val possibleAnimals = arrayOf(
            Animal("Pies", R.drawable.pies),
            Animal("Kot", R.drawable.kot),
            Animal("Słoń", R.drawable.slon),
            Animal("Lew", R.drawable.lew),
            Animal("Hiena", R.drawable.hiena),
            Animal("Kaczor", R.drawable.kaczor),
            Animal("Kura", R.drawable.kura),
            Animal("Wilk", R.drawable.wilk)
        )

        // 2. Generowanie 100 losowych zwierząt [cite: 53-57]
        val random = Random()
        for (i in 0 until 100) {
            val randomAnimal = possibleAnimals[random.nextInt(possibleAnimals.size)]
            // Tworzymy nowego zwierzaka dodając numer porządkowy do nazwy
            animalsList.add(Animal("${randomAnimal.name} ${i + 1}", randomAnimal.image))
        }

        // 3. Konfiguracja Adaptera
        mAdapter = AnimalsAdapter(animalsList)

        // 4. Konfiguracja LayoutManager (Siatka lub Lista) [cite: 59-61]
        val mLayoutManager: RecyclerView.LayoutManager = if (wybor) {
            // Siatka z 4 kolumnami
            GridLayoutManager(applicationContext, 2) // Zmieniłem na 2, żeby było czytelniej na telefonie
        } else {
            // Lista pionowa
            LinearLayoutManager(this)
        }

        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mAdapter
    }
}