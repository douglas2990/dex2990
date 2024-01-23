package com.douglas2990.pokedexmyapplication2990.detailSpecies

import androidx.annotation.StringRes
import com.douglas2990.pokedexmyapplication2990.model.modelSpecies.PokemonSpecies

sealed class DetailScreenStateSpecies {
    data class Success(val data: PokemonSpecies): DetailScreenStateSpecies()
    data class Error(@StringRes val messageId: Int): DetailScreenStateSpecies()
    object Loading : DetailScreenStateSpecies()
}