package com.douglas2990.pokedexmyapplication2990.screenState

import androidx.annotation.StringRes
import com.douglas2990.pokedexmyapplication2990.model.responses.Result


sealed class FirstScreenState {
    data class Success(val data: List<Result>) : FirstScreenState()
    data class Error(@StringRes val messageId: Int): FirstScreenState()
    object Loading : FirstScreenState()
}