package com.douglas2990.pokedexmyapplication2990.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.api.RestManager
import com.douglas2990.pokedexmyapplication2990.model.responses.Pokemon

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPokemonViewModel(pokemonId: String) : ViewModel() {
    val state:MutableLiveData<DetailScreenState> by lazy {
        MutableLiveData<DetailScreenState>()
    }
    init {
        state.value = DetailScreenState.Loading
        RestManager.getEndpoints().pokemonDittoDetail(pokemonId).enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let { body ->
                    state.value = DetailScreenState.Success(body)
                }?: run{ state.value = DetailScreenState.Error(R.string.erroApi)

                }
            }
            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                state.value = DetailScreenState.Error(R.string.erro)
            }
        })

    }
}


