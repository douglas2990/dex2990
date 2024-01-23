package com.douglas2990.pokedexmyapplication2990.moves.movespokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovesPokemonFactory(private val pokemonId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovesPokemonViewModel(pokemonId) as T
    }

}