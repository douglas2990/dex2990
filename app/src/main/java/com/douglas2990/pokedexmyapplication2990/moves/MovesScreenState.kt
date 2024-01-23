package com.douglas2990.pokedexmyapplication2990.moves

import androidx.annotation.StringRes
import com.douglas2990.pokedexmyapplication2990.model.moves.Result

sealed class MovesScreenState {
    data class Success(val data: List<Result>) : MovesScreenState()
    data class Error(@StringRes val messageId: Int): MovesScreenState()
    object Loading: MovesScreenState()
}