package com.douglas2990.pokedexmyapplication2990.model

import com.google.gson.annotations.SerializedName

data class Results(
    @field:SerializedName("name")
    val name: String ,
    @field:SerializedName("url")
    val url: String
)
