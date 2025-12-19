package pbs.edu.lista

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Random

/**
 * Aktywność wyświetlająca zaawansowaną listę (siatkę) przy użyciu RecyclerView.
 */
class HarderListsActivity : AppCompatActivity() {

    // Flaga wyboru układu (zgodnie z kodem w instrukcji)
    private val wybor = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_harder_lists)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        // Pula możliwych nazw zwierząt
        val possibleAnimals = arrayOf(
            "Pies", "Kot", "Kangur", "Panda", "Żyrafa",
            "Wirus", "Kaczka", "Donald", "Wilk", "Królik",
            "Słon", "Lew"
        )

        // Tablica na 200 losowych elementów
        val animals = Array(200) { "" }
        val random = Random()

        // Losowanie zwierząt i przypisywanie numerów
        for (i in animals.indices) {
            val randomAnimal = possibleAnimals[random.nextInt(possibleAnimals.size)]
            animals[i] = "$randomAnimal ${i + 1}"
        }

        // Konfiguracja LayoutManagera
        if (wybor) {
            // Ustawienie siatki z 4 kolumnami
            val layoutManager = GridLayoutManager(applicationContext, 4)
            recyclerView.layoutManager = layoutManager
        } else {
            // Alternatywnie lista pionowa
            recyclerView.layoutManager = LinearLayoutManager(this)
        }

        recyclerView.itemAnimator = DefaultItemAnimator()

        // Ustawienie adaptera
        val adapter = AnimalsAdapter(animals)
        recyclerView.adapter = adapter
    }

    /**
     * Obsługa klawisza ESC
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ESCAPE) {
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}