package com.douglas2990.pokedexmyapplication2990.type.detailType

import androidx.annotation.StringRes
import com.douglas2990.pokedexmyapplication2990.model.type.typeDetail.DetailType


sealed class DetailTypeScreenState {
    data class Success(val data: DetailType) : DetailTypeScreenState()
    data class Error(@StringRes val messageId: Int): DetailTypeScreenState()
    object Loading: DetailTypeScreenState()
}