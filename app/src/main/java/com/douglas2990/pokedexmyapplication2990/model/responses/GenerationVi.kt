package com.douglas2990.pokedexmyapplication2990.model.responses

import com.google.gson.annotations.SerializedName

data class GenerationVi(
    //val omegaruby-alphasapphire: OmegarubyAlphasapphire,
    @field:SerializedName("omegaruby-alphasapphire")
    val omegaruby_alphasapphire: OmegarubyAlphasapphire,
    //val x-y: XY
    @field:SerializedName("x-y")
    val x_y: XY
)