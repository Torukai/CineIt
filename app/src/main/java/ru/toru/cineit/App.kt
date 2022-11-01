package ru.toru.cineit

import android.app.Application

class App : Application() {

    val filmsApp = mutableListOf(
        FilmItem( R.string.witcher_name, R.string.witcher_description, R.drawable.witcher_image, R.color.white, false),
        FilmItem( R.string.utopia_name, R.string.utopia_description, R.drawable.utopia_cover, R.color.white, false),
        FilmItem( R.string.stranger_things_name, R.string.stranger_things_description, R.drawable.stranger_things_cover, R.color.white, false),
        FilmItem( R.string.arcane_name, R.string.arcane_description, R.drawable.arcane_cover, R.color.white, false),
        FilmItem( R.string.rick_and_morty_name, R.string.rick_and_morty_description, R.drawable.rick_and_morty_cover, R.color.white, false),
        FilmItem( R.string.friends_name, R.string.friends_description, R.drawable.friends_cover, R.color.white, false),
        FilmItem( R.string.squid_games_name, R.string.squid_games_description, R.drawable.squid_games_cover, R.color.white, false),
        FilmItem( R.string.love_death_and_robots_name, R.string.love_death_and_robots_description, R.drawable.love_death_and_robots_cover, R.color.white, false)
    )

    fun  getFavorites():MutableList<FilmItem>{
        val favoriteFilms : MutableList<FilmItem> = mutableListOf()

        for (film in filmsApp){
            if(film.isFavorite){
                favoriteFilms.add(film)
            }
        }

        return favoriteFilms
    }
}