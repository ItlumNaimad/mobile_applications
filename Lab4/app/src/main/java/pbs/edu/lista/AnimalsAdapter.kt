package pbs.edu.lista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Niestandardowy adapter do obsługi RecyclerView.
 * Adapter extends RecyclerView.Adapter
 */
class AnimalsAdapter(private val animals: Array<String>) :
    RecyclerView.Adapter<AnimalsAdapter.AnimalsViewHolder>() {

    /**
     * Klasa wewnętrzna przechowująca widoki pojedynczego elementu listy (ViewHolder).
     * public static class AnimalsViewHolder extends RecyclerView.ViewHolder
     */
    class AnimalsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtView: TextView = view.findViewById(android.R.id.text1)
    }

    /**
     * Tworzy nowy widok (uruchamiane przez LayoutManager).
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalsViewHolder {
        // Używamy systemowego layoutu simple_list_item_1 dla pojedynczego kafelka
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return AnimalsViewHolder(view)
    }

    /**
     * Zastępuje zawartość widoku (uruchamiane przez LayoutManager).
     */
    override fun onBindViewHolder(holder: AnimalsViewHolder, position: Int) {
        val animal = animals[position]
        holder.txtView.text = animal
    }

    /**
     * Zwraca rozmiar zbioru danych.
     */
    override fun getItemCount(): Int {
        return animals.size
    }
}