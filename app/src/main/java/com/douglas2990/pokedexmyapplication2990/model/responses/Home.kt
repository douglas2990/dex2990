package com.douglas2990.pokedexmyapplication2990.model.responses

import com.google.gson.annotations.SerializedName

data class Home(
    @field:SerializedName("front_default")
    val front_default: String,
    @field:SerializedName("front_female")
    val front_female: String,
    @field:SerializedName("front_shiny")
    val front_shiny: String,
    @field:SerializedName("front_shiny_female")
    val front_shiny_female: String
)