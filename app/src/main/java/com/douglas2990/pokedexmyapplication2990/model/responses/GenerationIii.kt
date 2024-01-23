package com.douglas2990.pokedexmyapplication2990.model.responses

import com.google.gson.annotations.SerializedName

data class GenerationIii(
    @field:SerializedName("emerald")
    val emerald: Emerald,
    //val firered-leafgreen: FireredLeafgreen,
    @field:SerializedName("firered-leafgreen")
    val firered_leafgreen: FireredLeafgreen,
    //val ruby-sapphire: RubySapphire
    @field:SerializedName("ruby-sapphire")
    val ruby_sapphire: RubySapphire
)