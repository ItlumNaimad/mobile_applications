package pbs.edu.animallist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter do obsługi listy zwierząt w RecyclerView.
 * Obsługuje wyświetlanie, kolorowanie wierszy oraz interakcje użytkownika.
 *
 * @param animalsList Lista modyfikowalna przechowująca obiekty Animal.
 */
class AnimalsAdapter(private val animalsList: ArrayList<Animal>) :
    RecyclerView.Adapter<AnimalsAdapter.AnimalsViewHolder>() {

    /**
     * Klasa ViewHolder przechowująca referencje do widoków w pojedynczym wierszu.
     */
    class AnimalsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = view.findViewById(R.id.animal_name)
        val imgImage: ImageView = view.findViewById(R.id.animal_image)
        val btnDelete: ImageButton = view.findViewById(R.id.delete_animal_button)
    }

    /**
     * Tworzy nowy widok wiersza (inflating layoutu).
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_list_item_recycle_animal, parent, false)
        return AnimalsViewHolder(itemView)
    }

    /**
     * Przypisuje dane do widoków i ustawia słuchacze zdarzeń.
     */
    override fun onBindViewHolder(holder: AnimalsViewHolder, position: Int) {
        val animal = animalsList[position]

        // Ustawienie danych
        holder.txtName.text = animal.name
        holder.imgImage.setImageResource(animal.image)

        // Kolorowanie tła wierszy (na wzór Rys. z Page 9 instrukcji)
        // Parzyste - turkusowe, Nieparzyste - zielone (lub inne wybrane kolory)
        if (position % 2 == 0) {
            holder.txtName.setBackgroundColor(Color.parseColor("#00FFFF")) // Cyan
        } else {
            holder.txtName.setBackgroundColor(Color.parseColor("#00FF00")) // Green
        }

        // --- OBSŁUGA ZDARZEŃ ---

        // 1. Kliknięcie na przycisk usuwania (ImageButton) [cite: 74]
        holder.btnDelete.setOnClickListener {
            // Usuwamy element z listy danych
            animalsList.removeAt(holder.adapterPosition)
            // Powiadamiamy adapter o usunięciu (animacja + odświeżenie)
            notifyItemRemoved(holder.adapterPosition)
            notifyItemRangeChanged(holder.adapterPosition, animalsList.size)

            Toast.makeText(holder.itemView.context, "Usunięto: ${animal.name}", Toast.LENGTH_SHORT).show()
        }

        // 2. Kliknięcie na zdjęcie [cite: 75-76]
        holder.imgImage.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Kliknięto zdjęcie: ${animal.name}",
                Toast.LENGTH_SHORT
            ).show()
        }

        // 3. Długie kliknięcie na tekst (nazwę) [cite: 75]
        holder.txtName.setOnLongClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Długie kliknięcie: ${animal.name}",
                Toast.LENGTH_SHORT
            ).show()
            true // Zwracamy true, aby oznaczyć zdarzenie jako obsłużone
        }
    }

    /**
     * Zwraca liczbę elementów w liście.
     */
    override fun getItemCount(): Int {
        return animalsList.size
    }
}