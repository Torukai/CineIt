package ru.toru.cineit

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView

class FilmItemViewHolder(filmView: View) : RecyclerView.ViewHolder(filmView) {

    private val filmCover: ImageView = filmView.findViewById(R.id.cover)
    private val filmTitle: TextView = filmView.findViewById(R.id.title)
    private var filmIsFavorite: ToggleButton = filmView.findViewById(R.id.isFavorite)

    fun bind(film: FilmItem, listener: FilmItemAdapter.FilmClickListener)
    {
        filmCover.setBackgroundResource(film.cover)
        filmTitle.setText(film.name)
        filmIsFavorite.setBackgroundResource(if (film.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border)

        filmCover.setOnClickListener{
            listener.onFilmClick(
                film,
                adapterPosition
            )
        }

        filmIsFavorite.setOnClickListener{
            listener.onFavoriteClick(
                film,
                adapterPosition
            )
        }
    }
}