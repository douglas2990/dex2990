package com.douglas2990.pokedexmyapplication2990.model.responses

import com.google.gson.annotations.SerializedName

data class Crystal(
    @field:SerializedName("back_default")
    val back_default: String,
    @field:SerializedName("back_shiny")
    val back_shiny: String,
    @field:SerializedName("back_shiny_transparent")
    val back_shiny_transparent: String,
    @field:SerializedName("back_transparent")
    val back_transparent: String,
    @field:SerializedName("front_default")
    val front_default: String,
    @field:SerializedName("front_shiny")
    val front_shiny: String,
    @field:SerializedName("front_shiny_transparent")
    val front_shiny_transparent: String,
    @field:SerializedName("front_transparent")
    val front_transparent: String
)