package com.douglas2990.pokedexmyapplication2990.evolution

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.api.RestManager
import com.douglas2990.pokedexmyapplication2990.model.evolution_chain.EvolutionChain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPokemonEvolutionViewModel(pokemonId: String) : ViewModel() {
    val state: MutableLiveData<DetailScreenStateEvolution> by lazy {
        MutableLiveData<DetailScreenStateEvolution>()
    }
    init {
        state.value = DetailScreenStateEvolution.Loading
        RestManager.getEndpoints().getPokemonEvolution(pokemonId).enqueue(object : Callback<EvolutionChain> {
            override fun onResponse(call: Call<EvolutionChain>, response: Response<EvolutionChain>) {
                response.body()?.let{ body ->
                    state.value = DetailScreenStateEvolution.Success(body)
                }?: run{ state.value = DetailScreenStateEvolution.Error(R.string.erroApi)

                }
            }

            override fun onFailure(call: Call<EvolutionChain>, t: Throwable) {
                state.value = DetailScreenStateEvolution.Error(R.string.erro)
            }

        })
    }

}