package ru.toru.cineit

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class FilmItem (val id: Int, @StringRes val name: Int, @StringRes val description: Int, @DrawableRes val cover: Int, @ColorRes var color: Int, var isFavorite: Boolean)
