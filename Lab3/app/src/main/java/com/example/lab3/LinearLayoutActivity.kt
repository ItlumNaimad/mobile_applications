package com.example.lab3

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity

class LinearLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ustawienie odpowiedniego pliku layoutu dla tej Aktywności
        setContentView(R.layout.activity_linear_layout)
    }

    /**
     * Ta funkcja jest wywoływana za każdym razem, gdy użytkownik
     * wciśnie fizyczny klawisz (np. na klawiaturze emulatora lub telefonu).
     *
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Sprawdzamy, czy wciśnięty klawisz to ESCAPE
        if (keyCode == KeyEvent.KEYCODE_ESCAPE) {
            // finish() zamyka bieżącą Aktywność i wraca do poprzedniej (MainActivity)
            finish()
            // Zwracamy 'true', aby poinformować system, że obsłużyliśmy to zdarzenie
            return true
        }
        // Dla każdego innego klawisza, pozwól systemowi obsłużyć go domyślnie
        return super.onKeyDown(keyCode, event)
    }
}