package com.douglas2990.pokedexmyapplication2990.model.responses

import com.google.gson.annotations.SerializedName

data class FireredLeafgreen(
    @field:SerializedName("back_default")
    val back_default: String,
    @field:SerializedName("back_shiny")
    val back_shiny: String,
    @field:SerializedName("front_default")
    val front_default: String,
    @field:SerializedName("front_shiny")
    val front_shiny: String
)