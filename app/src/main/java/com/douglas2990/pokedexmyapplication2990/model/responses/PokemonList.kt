package com.douglas2990.pokedexmyapplication2990.model.responses

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)