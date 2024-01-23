package com.douglas2990.pokedexmyapplication2990.model.responses

import com.google.gson.annotations.SerializedName

data class GenerationVii(
    @field:SerializedName("icons")
    val icons: Icons,
    //val ultra-sun-ultra-moon: UltraSunUltraMoon
    @field:SerializedName("ultra-sun-ultra-moon")
    val ultra_sun_ultra_moon: UltraSunUltraMoon
)