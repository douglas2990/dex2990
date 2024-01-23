package com.douglas2990.pokedexmyapplication2990.detailSpecies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailPokemonSpeciesFactory(private val pokemonId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailPokemonSpeciesViewModel(pokemonId) as T
    }
}