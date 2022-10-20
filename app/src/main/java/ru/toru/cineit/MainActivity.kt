package ru.toru.cineit

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var films = (application as App).films
        films.forEachIndexed {index, film->
            findViewById<TextView>(film.id).setOnClickListener {
                val intent = Intent(this, FilmDetails::class.java)
                    .putExtra("id", index)
                    .putExtra("isFavorite", film.isFavorite)

                film.nameColor = R.color.teal_700
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val films = (application as App).films

        for (film in films)
        {
            findViewById<TextView>(film.id).setTextColor(ContextCompat.getColor(baseContext, film.nameColor))
            film.nameColor=R.color.white
        }
    }
}