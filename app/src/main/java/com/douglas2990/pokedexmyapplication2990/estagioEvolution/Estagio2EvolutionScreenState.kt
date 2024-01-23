package com.douglas2990.pokedexmyapplication2990.estagioEvolution

import androidx.annotation.StringRes
import com.douglas2990.pokedexmyapplication2990.model.evolution_chain.EvolvesTo

sealed class Estagio2EvolutionScreenState {
    data class Success(val data: List<EvolvesTo>): Estagio2EvolutionScreenState()
    data class Error(@StringRes val messageId: Int): Estagio2EvolutionScreenState()
    object Loading: Estagio2EvolutionScreenState()
}