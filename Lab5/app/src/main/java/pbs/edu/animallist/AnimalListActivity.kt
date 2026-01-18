package pbs.edu.animallist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Random

class AnimalListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var mAdapter: AnimalsAdapter
    private val animalList = ArrayList<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lists)

        recyclerView = findViewById(R.id.recycler_view)

        // Możesz użyć GridLayoutManager zamiast LinearLayoutManager, jeśli wolisz siatkę w tej aktywności
        // recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()

        mAdapter = AnimalsAdapter(animalList)
        recyclerView.adapter = mAdapter

        prepareAnimalData()
    }

    private fun prepareAnimalData() {
        // Tablica dostępnych nazw i obrazków.
        // UWAGA: Upewnij się, że masz te pliki w res/drawable lub zamień R.drawable.x na systemowe ikony
        // W przykładzie używam placeholderów, jeśli nie masz plików, kod się nie skompiluje.
        // Zamień R.drawable.dog na np. R.drawable.ic_launcher_background jeśli brakuje grafik.

        val names = arrayOf("Pies", "Kot", "Słoń", "Lemur", "Koń", "Hiena", "Borsuk", "Lama")

        // Tutaj mapujemy nazwy na zasoby. Jeśli nie masz tych plików, podmień je!
        val images = mapOf(
            "Pies" to R.drawable.pies,      // lub R.drawable.ic_launcher_foreground
            "Kot" to R.drawable.kot,
            "Słoń" to R.drawable.slon,
            "Kaczka" to R.drawable.kaczka,
            "Kaczor" to R.drawable.kaczor,
            "Hiena" to R.drawable.hiena,
            "Kangur" to R.drawable.kangur,
            "Wilk" to R.drawable.wilk,
            "Wirus" to R.drawable.wirus,
            "Lew" to R.drawable.lew,
            "Żyrafa" to R.drawable.zyrafa,
            "Panda" to R.drawable.panda
        )

        // Jeśli nie ma tych plików drawable, użyj tej bezpiecznej listy do testów:
        /*
        val images = mapOf(
             "Pies" to android.R.drawable.ic_menu_compass,
             "Kot" to android.R.drawable.ic_menu_camera,
             ...
        )
        */

        val random = Random()
        for (i in 0..99) {
            val randomName = names[random.nextInt(names.size)]
            // Pobieramy ID obrazka lub domyślny launcher jeśli null (dla bezpieczeństwa)
            val randomImage = images[randomName] ?: R.drawable.ic_launcher_foreground

            val animal = Animal(randomName, randomImage)
            animalList.add(animal)
        }

        mAdapter.notifyDataSetChanged()
    }
}