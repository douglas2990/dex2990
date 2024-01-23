package com.douglas2990.pokedexmyapplication2990.model.moves

data class Moves(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)