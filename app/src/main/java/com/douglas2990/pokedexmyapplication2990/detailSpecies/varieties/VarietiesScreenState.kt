package com.douglas2990.pokedexmyapplication2990.detailSpecies.varieties

import androidx.annotation.StringRes
import com.douglas2990.pokedexmyapplication2990.model.modelSpecies.PokemonSpecies

sealed class VarietiesScreenState {
    data class Success(val data: PokemonSpecies): VarietiesScreenState()
    data class Error(@StringRes val messageId: Int): VarietiesScreenState()
    object Loading : VarietiesScreenState()
}