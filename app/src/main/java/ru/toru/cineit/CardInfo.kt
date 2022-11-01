package ru.toru.cineit

class CardInfo {

    constructor(id_:Int, name_:String, description_: Int, coverId_ : Int, nameColor_ :Int, isFavorite_: Boolean){
        id = id_
        name = name_
        description = description_
        coverId = coverId_
        nameColor = nameColor_
        isFavorite = isFavorite_
    }

    val id: Int
    val name: String
    val description: Int
    val coverId: Int
    var nameColor: Int
    var isFavorite: Boolean
}