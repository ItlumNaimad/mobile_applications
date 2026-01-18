package pbs.edu.animallist

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.gridlayout.widget.GridLayout

class HarderListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_harder_lists)

        val gridLayout: GridLayout = findViewById(R.id.animalsGrid)
        val animals = listOf("Żyrafa", "Donald", "Wilk", "Kot", "Wirus", "Kangur", "Królik")

        // Generujemy 48 elementów (jak na obrazku w PDF)
        for (i in 1..48) {
            val animalName = animals[i % animals.size]

            val textView = TextView(this).apply {
                text = "$animalName $i"
                textSize = 14f
                setPadding(16, 16, 16, 16)
                gravity = Gravity.CENTER
                setBackgroundColor(if (i % 2 == 0) Color.LTGRAY else Color.WHITE) // "Zebra" effect

                // Parametry układu w siatce
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 0 // Waga wiersza/kolumny załatwi szerokość
                    height = GridLayout.LayoutParams.WRAP_CONTENT
                    rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    setMargins(4, 4, 4, 4)
                }
            }
            gridLayout.addView(textView)
        }
    }
}