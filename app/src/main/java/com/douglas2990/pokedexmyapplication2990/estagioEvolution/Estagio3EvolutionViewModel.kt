package com.douglas2990.pokedexmyapplication2990.estagioEvolution

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.api.RestManager
import com.douglas2990.pokedexmyapplication2990.model.evolution_chain.EvolutionChain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Estagio3EvolutionViewModel(pokemonId: String): ViewModel() {
    val state: MutableLiveData<Estagio3EvolutionScreenState> by lazy {
        MutableLiveData<Estagio3EvolutionScreenState>()
    }
    init {
        state.value = Estagio3EvolutionScreenState.Loading

        RestManager.getEndpoints().getPokemonEstagioEvolution(pokemonId)
            .enqueue(object :
            Callback<EvolutionChain> {
            override fun onResponse(
                call: Call<EvolutionChain>,
                response: Response<EvolutionChain>
            ) {
                response.body()?.let { body ->
                    state.value = Estagio3EvolutionScreenState.Success(body.chain.evolves_to[0].evolves_to)
                } ?: run{ state.value = Estagio3EvolutionScreenState.Error(R.string.erroApi) }
            }

            override fun onFailure(call: Call<EvolutionChain>, t: Throwable) {
                state.value = Estagio3EvolutionScreenState.Error(R.string.erro)
            }

        })
    }
}
