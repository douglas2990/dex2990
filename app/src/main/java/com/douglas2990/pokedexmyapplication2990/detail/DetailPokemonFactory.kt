package com.douglas2990.pokedexmyapplication2990.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailPokemonFactory (private val pokemonId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailPokemonViewModel(pokemonId) as T
    }

}