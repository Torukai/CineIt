package ru.toru.cineit

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class FilmFavoritesActivity: AppCompatActivity() {


    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerFavorites) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        initRecycler()
    }

    private fun initRecycler() {

        val films = (application as App).getFavorites()
//        val layoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = FilmItemAdapter(films, object : FilmItemAdapter.FilmClickListener {
            override fun onFilmClick(filmItem: FilmItem, position: Int) {
                Toast.makeText(this@FilmFavoritesActivity, "Film Clicked", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@FilmFavoritesActivity, FilmDetailsActivity::class.java)
                    .putExtra("cover", filmItem.cover)
                    .putExtra("description", filmItem.description)
                    .putExtra("isFavorite", filmItem.isFavorite)

                startActivity(intent)
            }

            override fun onFavoriteClick(filmItem: FilmItem, position: Int) {
                Toast.makeText(this@FilmFavoritesActivity, "Favorite Clicked", Toast.LENGTH_SHORT).show()
                filmItem.isFavorite = !filmItem.isFavorite
                films.removeAt(position)
                recyclerView.adapter?.notifyItemRemoved(position)
            }
        })

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_black_line, theme)
            ?.let { divider.setDrawable(it) }
        recyclerView.addItemDecoration(divider)
    }
}