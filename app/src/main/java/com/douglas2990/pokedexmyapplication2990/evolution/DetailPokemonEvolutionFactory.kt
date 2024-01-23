package com.douglas2990.pokedexmyapplication2990.evolution

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailPokemonEvolutionFactory(private val pokemonId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailPokemonEvolutionViewModel(pokemonId) as T
    }
}