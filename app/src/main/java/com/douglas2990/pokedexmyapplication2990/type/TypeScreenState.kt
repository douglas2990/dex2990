package com.douglas2990.pokedexmyapplication2990.type

import androidx.annotation.StringRes
import com.douglas2990.pokedexmyapplication2990.model.type.Result

sealed class TypeScreenState {
    data class Success(val data: List<Result>) : TypeScreenState()
    data class Error(@StringRes val messageId: Int) :TypeScreenState()
    object Loading: TypeScreenState()
}