package com.douglas2990.pokedexmyapplication2990.imagesDetail

import androidx.annotation.StringRes
import com.douglas2990.pokedexmyapplication2990.model.responses.Pokemon

sealed class ImageScreenState{
    data class Success(val data: Pokemon)  : ImageScreenState()
    data class Error(@StringRes val messageId: Int) : ImageScreenState()
    object Loading : ImageScreenState()
}