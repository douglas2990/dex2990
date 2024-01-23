package com.douglas2990.pokedexmyapplication2990.moves

import androidx.annotation.StringRes
import com.douglas2990.pokedexmyapplication2990.model.moves.MovesDetail

sealed class DetailMoveScreenState {
    data class Success(val data: MovesDetail ) : DetailMoveScreenState()
    data class Error(@StringRes val messageId: Int): DetailMoveScreenState()
    object Loading: DetailMoveScreenState()
}