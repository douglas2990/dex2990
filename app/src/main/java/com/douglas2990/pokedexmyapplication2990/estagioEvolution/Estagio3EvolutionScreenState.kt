package com.douglas2990.pokedexmyapplication2990.estagioEvolution

import androidx.annotation.StringRes
import com.douglas2990.pokedexmyapplication2990.model.evolution_chain.EvolvesTo
import com.douglas2990.pokedexmyapplication2990.model.evolution_chain.EvolvesToX

sealed class Estagio3EvolutionScreenState {
    data class Success(val data: List<EvolvesToX>): Estagio3EvolutionScreenState()
    data class Error(@StringRes val messageId: Int): Estagio3EvolutionScreenState()
    object Loading: Estagio3EvolutionScreenState()
}