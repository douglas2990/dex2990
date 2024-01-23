package com.douglas2990.pokedexmyapplication2990.model.responses

import com.google.gson.annotations.SerializedName

data class GenerationI(
    //val red-blue: RedBlue,
    @field:SerializedName("red-blue")
    val red_blue: RedBlue,
    @field:SerializedName("yellow")
    val yellow: Yellow
)