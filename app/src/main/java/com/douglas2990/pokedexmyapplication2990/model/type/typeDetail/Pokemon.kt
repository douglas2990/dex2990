package com.douglas2990.pokedexmyapplication2990.model.type.typeDetail

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @field:SerializedName("pokemon")
    val pokemon: PokemonX,
    val slot: Int
)