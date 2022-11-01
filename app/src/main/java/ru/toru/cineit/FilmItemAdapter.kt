package ru.toru.cineit

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FilmItemAdapter(
    private val films: List<FilmItem>,
    private val listener: FilmClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("Adapter", "onCreateViewHolder: $viewType")

        val inflater = LayoutInflater.from(parent.context)
        return FilmItemViewHolder(inflater.inflate(R.layout.film_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewHolder: $position")

        when (holder) {
            is FilmItemViewHolder -> {
                holder.bind(films[position], listener)
            }
        }
    }

    override fun getItemCount(): Int = films.size
    interface FilmClickListener {
        fun onFilmClick(filmItem: FilmItem, position: Int)
        fun onFavoriteClick(filmItem: FilmItem, position: Int)
    }
}