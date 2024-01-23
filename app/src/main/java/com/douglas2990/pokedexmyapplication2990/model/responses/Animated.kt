package com.douglas2990.pokedexmyapplication2990.model.responses

import com.google.gson.annotations.SerializedName

data class Animated(
    @field:SerializedName("back_default")
    val back_default: String,
    @field:SerializedName("back_female")
    val back_female: String,
    @field:SerializedName("back_shiny")
    val back_shiny: String,
    @field:SerializedName("back_shiny_female")
    val back_shiny_female: String,
    @field:SerializedName("front_default")
    val front_default: String,
    @field:SerializedName("front_female")
    val front_female: String,
    @field:SerializedName("front_shiny")
    val front_shiny: String,
    @field:SerializedName("front_shiny_female")
    val front_shiny_female: String
)