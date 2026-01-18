package pbs.edu.animallist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLinear: Button = findViewById(R.id.btnLinear)
        val btnRelative: Button = findViewById(R.id.btnRelative)
        val btnTable: Button = findViewById(R.id.btnTable)

        // Przycisk 1: Prosta lista (Linear Layout)
        btnLinear.setOnClickListener {
            val intent = Intent(this, SimpleListActivity::class.java)
            startActivity(intent)
        }

        // Przycisk 2: Trudniejsza lista (Grid/Relative)
        btnRelative.setOnClickListener {
            val intent = Intent(this, HarderListActivity::class.java)
            startActivity(intent)
        }

        // Przycisk 3: RecyclerView (Lab 4 logic)
        btnTable.setOnClickListener {
            val intent = Intent(this, AnimalListActivity::class.java)
            startActivity(intent)
        }
    }
}