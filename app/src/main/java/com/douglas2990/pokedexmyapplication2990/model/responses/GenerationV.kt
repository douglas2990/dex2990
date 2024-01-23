package com.douglas2990.pokedexmyapplication2990.model.responses

import com.google.gson.annotations.SerializedName

data class GenerationV(
    //val black-white: BlackWhite
    @field:SerializedName("black-white")
    val black_white: BlackWhite
)