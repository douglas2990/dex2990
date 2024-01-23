package com.douglas2990.pokedexmyapplication2990.search
import androidx.annotation.StringRes
import com.douglas2990.pokedexmyapplication2990.model.responses.Result

sealed class SearchPokemonScreenState {
    data class Success(val data: List<Result>): SearchPokemonScreenState()
    data class Error(@StringRes val messageId: Int): SearchPokemonScreenState()
    object Loading: SearchPokemonScreenState()
}