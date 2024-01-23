package com.douglas2990.pokedexmyapplication2990.evolution

import androidx.annotation.StringRes
import com.douglas2990.pokedexmyapplication2990.model.evolution_chain.EvolutionChain

sealed class DetailScreenStateEvolution {
    data class Success(val data: EvolutionChain): DetailScreenStateEvolution()
    data class Error(@StringRes val messageId: Int): DetailScreenStateEvolution()
    object Loading : DetailScreenStateEvolution()
}