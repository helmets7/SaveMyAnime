package com.example.savemyanime.adaptadoranime


data class Anime(var id: String,
                 var nombre: String,
                 var categoriaRef: MutableList<String>,
                 var directores: String,
                 var desc: MutableList<String>,
                 var ncaps: Int,
                 var portada: String)
