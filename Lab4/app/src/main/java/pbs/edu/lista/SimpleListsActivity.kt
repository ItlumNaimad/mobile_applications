package pbs.edu.lista

import android.os.Bundle
import android.view.KeyEvent
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Aktywność wyświetlająca prostą listę zwierząt przy użyciu ListView.
 */
class SimpleListsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_lists)

        // Znalezienie widoku listy
        val animalList = findViewById<ListView>(R.id.animalList)

        // Definicja danych - tablica zwierząt
        val animals = arrayOf(
            "Kot", "Pies", "Słoń", "Kot", "Pies", "Słoń",
            "Kot", "Wilk", "Słoń", "Kot", "Pies", "Słoń",
            "Lew", "Hiena", "Kaczor", "Kura"
        )

        // Utworzenie adaptera (mostu między danymi a widokiem)
        // Używamy wbudowanego w Androida layoutu 'simple_list_item_1'
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            animals
        )

        // Przypisanie adaptera do listy
        animalList.adapter = adapter

        // Obsługa kliknięcia w element listy
        animalList.setOnItemClickListener { parent, view, position, id ->
            val selectedAnimal = animals[position]
            Toast.makeText(
                applicationContext,
                "Wybrano element: $position czyli: $selectedAnimal",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**
     * Obsługa fizycznego klawisza (np. ESC w emulatorze).
     * Wymagane przez instrukcję
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ESCAPE) {
            finish() // Zamknij aktywność i wróć do menu
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}