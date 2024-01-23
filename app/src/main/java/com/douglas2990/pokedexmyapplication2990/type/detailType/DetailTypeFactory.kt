package com.douglas2990.pokedexmyapplication2990.type.detailType

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailTypeFactory(private val typeId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailTypeViewModel(typeId) as T
    }
}