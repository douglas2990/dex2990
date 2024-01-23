package com.douglas2990.pokedexmyapplication2990.moves.movespokemon

import androidx.annotation.StringRes
import com.douglas2990.pokedexmyapplication2990.model.responses.Move

sealed class MovesPokemonScreenState {
    data class Success(val data: List<Move>) : MovesPokemonScreenState()
    data class Error(@StringRes val messageId: Int): MovesPokemonScreenState()
    object Loading : MovesPokemonScreenState()
}