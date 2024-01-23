package com.douglas2990.pokedexmyapplication2990.moves

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.api.RestManager
import com.douglas2990.pokedexmyapplication2990.model.moves.Moves
import com.douglas2990.pokedexmyapplication2990.model.moves.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovesViewModel: ViewModel() {

    val state: MutableLiveData<MovesScreenState> by lazy {
        MutableLiveData<MovesScreenState>()
    }
    init {
        state.value = MovesScreenState.Loading
        RestManager.getEndpoints().getAllMoves(1000,0).enqueue(object :Callback<Moves> {
            override fun onResponse(
                call: Call<Moves>,
                response: Response<Moves>
            ) {
                response.body()?.let { body ->
                    state.value = MovesScreenState.Success(body.results)
                } ?: run {state.value = MovesScreenState.Error(R.string.erroApi)}
            }

            override fun onFailure(call: Call<Moves>, t: Throwable) {
                state.value = MovesScreenState.Error(R.string.erro)
            }

        })
    }
    var listaQuery: List<Result> = ArrayList()
    val listaQueryBusca: MutableList<Result> = ArrayList()

    fun pesquisarQuery(texto: String?) {
        listaQueryBusca.clear()

        for (query in listaQuery) {
            if (query.name != null) {
                val nome = query.name.lowercase()
                val id = query.url.replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "").lowercase()
                if (nome.contains(texto !!) || id.contains(texto)) {
                    listaQueryBusca.add(query)
                }
            } else {
                val nome = query.name
                val id = query.url.replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "")
                if (nome.contains(texto !!) || id.contains(texto)) {
                    listaQueryBusca.add(query)
                }
            }
        }
    }
}