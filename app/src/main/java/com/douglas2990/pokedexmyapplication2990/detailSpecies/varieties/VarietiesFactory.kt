package com.douglas2990.pokedexmyapplication2990.detailSpecies.varieties

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VarietiesFactory(private val pokemonId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VarietiesViewModel(pokemonId) as T
    }
}