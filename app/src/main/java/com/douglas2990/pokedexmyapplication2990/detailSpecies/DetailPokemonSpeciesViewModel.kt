package com.douglas2990.pokedexmyapplication2990.detailSpecies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.api.RestManager
import com.douglas2990.pokedexmyapplication2990.model.modelSpecies.PokemonSpecies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPokemonSpeciesViewModel(pokemonId: String) : ViewModel() {
    val state: MutableLiveData<DetailScreenStateSpecies> by lazy {
        MutableLiveData<DetailScreenStateSpecies>()
    }
    init {
        state.value = DetailScreenStateSpecies.Loading
        RestManager.getEndpoints().getPokemonSpecies(pokemonId).enqueue(object : Callback<PokemonSpecies> {
            override fun onResponse(call: Call<PokemonSpecies>, response: Response<PokemonSpecies>) {
                response.body()?.let{ body ->
                    state.value = DetailScreenStateSpecies.Success(body)
                }?: run{ state.value = DetailScreenStateSpecies.Error(R.string.erroApi)

                }
            }

            override fun onFailure(call: Call<PokemonSpecies>, t: Throwable) {
                state.value = DetailScreenStateSpecies.Error(R.string.erro)
            }

        })
    }

}