package ru.toru.cineit

import android.app.Application

class App : Application() {

    private val film1 = CardInfo(R.id.witcher_preview, "Witcher", R.string.witcher_description, R.drawable.witcher_image, R.color.white,false)
    private val film2 = CardInfo(R.id.utopia_preview, "Utopia", R.string.utopia_description, R.drawable.utopia_cover, R.color.white,false)
    private val film3 = CardInfo(R.id.arcane_preview, "Arcane", R.string.arcane_description, R.drawable.arcane_cover, R.color.white,false)
    private val film4 = CardInfo(R.id.stranger_things_preview, "Stranger things", R.string.stranger_things_description, R.drawable.stranger_things_cover, R.color.white,false)

    val films = arrayOf(film1, film2, film3, film4)
}