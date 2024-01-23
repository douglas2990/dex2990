package com.douglas2990.pokedexmyapplication2990.model.responses

import com.google.gson.annotations.SerializedName

data class Emerald(
    @field:SerializedName("front_default")
    val front_default: String,
    @field:SerializedName("front_shiny")
    val front_shiny: String
)