package com.douglas2990.pokedexmyapplication2990.imagesDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailImagesFactory(private val pokemonId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImagesViewModel(pokemonId) as T
    }

}