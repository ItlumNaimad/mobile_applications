package pbs.edu.lista

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
 * Główna aktywność aplikacji pełniąca rolę menu.
 * Pozwala na wybór pomiędzy listą prostą a zaawansowaną.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicjalizacja przycisków
        val btnSimple = findViewById<Button>(R.id.btnLinear)
        val btnHarder = findViewById<Button>(R.id.btnRelative)

        // Obsługa kliknięcia - przejście do listy prostej [cite: 227]
        btnSimple.setOnClickListener {
            val intent = Intent(this, SimpleListsActivity::class.java)
            startActivity(intent)
        }

        // Obsługa kliknięcia - przejście do listy zaawansowanej [cite: 228]
        btnHarder.setOnClickListener {
            val intent = Intent(this, HarderListsActivity::class.java)
            startActivity(intent)
        }
    }
}