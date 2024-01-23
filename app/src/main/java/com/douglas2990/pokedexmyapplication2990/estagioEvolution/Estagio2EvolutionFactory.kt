package com.douglas2990.pokedexmyapplication2990.estagioEvolution

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Estagio2EvolutionFactory(private val pokemonId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Estagio2EvolutionViewModel(pokemonId) as T
    }
}