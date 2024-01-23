package com.douglas2990.pokedexmyapplication2990.moves.movespokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.api.RestManager
import com.douglas2990.pokedexmyapplication2990.model.responses.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovesPokemonViewModel(pokemonId: String): ViewModel() {
    val state: MutableLiveData<MovesPokemonScreenState> by lazy {
        MutableLiveData<MovesPokemonScreenState>()
    }
    init {
        state.value = MovesPokemonScreenState.Loading

        RestManager.getEndpoints().pokemonDittoDetail(pokemonId).enqueue(object : Callback<Pokemon>{
            override fun onResponse(
                call: Call<Pokemon>,
                response: Response<Pokemon>
            ) {
                response.body()?.let{ body ->
                    state.value = MovesPokemonScreenState.Success(body.moves)
                } ?: run{ state.value = MovesPokemonScreenState.Error(R.string.erroApi)}
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                state.value = MovesPokemonScreenState.Error(R.string.erro)
            }
        })


    }
}