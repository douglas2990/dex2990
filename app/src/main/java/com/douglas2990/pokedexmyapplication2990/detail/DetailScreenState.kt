package com.douglas2990.pokedexmyapplication2990.detail

import androidx.annotation.StringRes
import com.douglas2990.pokedexmyapplication2990.model.responses.Pokemon


sealed class DetailScreenState {
    data class Success(val data: Pokemon)  : DetailScreenState()
    data class Error(@StringRes val messageId: Int) : DetailScreenState()
    object Loading : DetailScreenState()
}