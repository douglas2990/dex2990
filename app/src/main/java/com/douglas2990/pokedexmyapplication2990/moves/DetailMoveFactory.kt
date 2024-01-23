package com.douglas2990.pokedexmyapplication2990.moves

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailMoveFactory(private val moveId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailMoveViewModel(moveId) as T
    }
}