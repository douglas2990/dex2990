package com.douglas2990.pokedexmyapplication2990.model.evolution_chain

data class Chain(
    val evolution_details: List<Any>,
    val evolves_to: List<EvolvesTo>,
    val is_baby: Boolean,
    val species: SpeciesXX
)