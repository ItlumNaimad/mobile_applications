package pbs.edu.animallist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class AnimalsAdapter(private val animalList: ArrayList<Animal>) :
    RecyclerView.Adapter<AnimalsAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.animal_name)
        val image: ImageView = view.findViewById(R.id.animal_image)
        val deleteButton: ImageButton = view.findViewById(R.id.delete_animal_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_list_item_recycle_animal, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val animal = animalList[position]
        holder.name.text = animal.name

        // Ustawienie obrazka (pamiętaj, aby pliki graficzne istniały w res/drawable)
        // Jeśli nie masz konkretnych plików, aplikacja może się wysypać,
        // dlatego używam tu bezpiecznego fallbacku lub zakładam poprawność ID.
        holder.image.setImageResource(animal.image)

        // Obsługa kliknięcia usuwania
        holder.deleteButton.setOnClickListener {
            animalList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, animalList.size)
        }

        // Obsługa długiego kliknięcia na tekst
        holder.name.setOnLongClickListener {
            Toast.makeText(it.context, "Wybrano: ${animal.name}", Toast.LENGTH_SHORT).show()
            true
        }

        // Obsługa kliknięcia na obrazek
        holder.image.setOnClickListener {
            Toast.makeText(it.context, "Kliknięto obrazek: ${animal.name}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return animalList.size
    }
}