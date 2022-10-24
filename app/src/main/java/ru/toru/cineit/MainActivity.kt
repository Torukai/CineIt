package ru.toru.cineit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recycler) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<MaterialButton>(R.id.favoritesList).setOnClickListener{
            val intent = Intent(this@MainActivity, FilmFavoritesActivity::class.java)

            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        initRecycler()
        recyclerView.startLayoutAnimation()
    }

    private fun initRecycler() {
        val films = (application as App).filmsApp

        recyclerView.adapter = FilmItemAdapter(films, object : FilmItemAdapter.FilmClickListener {
            override fun onFilmClick(filmItem: FilmItem, position: Int) {
                Toast.makeText(this@MainActivity, "Film Clicked", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@MainActivity, FilmDetailsActivity::class.java)
                    .putExtra("id", filmItem.id)
                    .putExtra("cover", filmItem.cover)
                    .putExtra("description", filmItem.description)
                    .putExtra("isFavorite", filmItem.isFavorite)

                startActivity(intent)
            }

            override fun onFavoriteClick(filmItem: FilmItem, position: Int) {
                Toast.makeText(this@MainActivity, "Favorite Clicked", Toast.LENGTH_SHORT).show()
                filmItem.isFavorite = !filmItem.isFavorite
                recyclerView.adapter?.notifyItemChanged(position)
            }
        })

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_black_line, theme)
            ?.let { divider.setDrawable(it) }
        recyclerView.addItemDecoration(divider)
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
            .setTitle("Leave?")
            .setMessage("Are you sure that you want to exit now?")
            .setNegativeButton("No") { dialog, which ->
                return@setNegativeButton
            }
            .setPositiveButton("Yes") { dialog, which ->
                super.onBackPressed()
            }
            .create()

        builder.show()
    }
}