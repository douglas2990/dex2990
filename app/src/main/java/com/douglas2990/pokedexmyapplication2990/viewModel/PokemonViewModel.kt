package com.douglas2990.pokedexmyapplication2990.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.api.RestManager
import com.douglas2990.pokedexmyapplication2990.model.responses.PokemonList
import com.douglas2990.pokedexmyapplication2990.screenState.FirstScreenState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel: ViewModel() {
    val state: MutableLiveData<FirstScreenState> by lazy{
        MutableLiveData<FirstScreenState>()
    }
    init {
        state.value = FirstScreenState.Loading
        RestManager.getEndpoints().getPokemon(898,0).enqueue(object : Callback<PokemonList>{
            override fun onResponse(
                call: Call<PokemonList>,
               response: Response<PokemonList>
            ) {
                response.body()?.let { body ->
                    state.value = FirstScreenState.Success(body.results)
                } ?: run {state.value = FirstScreenState.Error(R.string.erroApi)}
            }

            override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                state.value = FirstScreenState.Error(R.string.erro)
            }
        })
    }

}







