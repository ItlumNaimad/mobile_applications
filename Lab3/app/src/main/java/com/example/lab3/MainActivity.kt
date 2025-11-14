package com.example.lab3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Krok 1: Znajdź przyciski w layoucie za pomocą ich ID
        val btnLinear = findViewById<Button>(R.id.btnLinear)
        val btnRelative = findViewById<Button>(R.id.btnRelative)
        val btnTable = findViewById<Button>(R.id.btnTable)

        // Krok 2: Ustaw nasłuchiwanie na kliknięcie dla przycisku LinearLayout
        // To jest implementacja kodu z Rys. 5
        btnLinear.setOnClickListener {
            // Stwórz "Intencję" (Intent) - żądanie uruchomienia innej Aktywności
            val intent = Intent(this, LinearLayoutActivity::class.java)
            // Uruchom tę Aktywność
            startActivity(intent)
        }

        // Krok 3: Ustaw nasłuchiwanie dla przycisku RelativeLayout
        // To jest implementacja kodu z Rys. 6
        btnRelative.setOnClickListener {
            val intent = Intent(this, RelativeLayoutActivity::class.java)
            startActivity(intent)
        }

        // Krok 4: Ustaw nasłuchiwanie dla przycisku TableLayout
        // To jest implementacja kodu z Rys. 7
        btnTable.setOnClickListener {
            val intent = Intent(this, TableLayoutActivity::class.java)
            startActivity(intent)
        }
    }
}