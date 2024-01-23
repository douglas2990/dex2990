package com.douglas2990.pokedexmyapplication2990.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.api.RestManager
import com.douglas2990.pokedexmyapplication2990.model.responses.PokemonList
import com.douglas2990.pokedexmyapplication2990.model.responses.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList


class SearchPokemonViewModel(): ViewModel() {
    val state: MutableLiveData<SearchPokemonScreenState> by lazy{
        MutableLiveData<SearchPokemonScreenState>()
    }
    init {
        state.value = SearchPokemonScreenState.Loading
        RestManager.getEndpoints().getPokemon(8980,0).enqueue(object : Callback<PokemonList>{
            override fun onResponse(
                call: Call<PokemonList>,
                response: Response<PokemonList>
            ) {
                response.body()?.let { body ->
                    state.value = SearchPokemonScreenState.Success(body.results)
                } ?: run {state.value = SearchPokemonScreenState.Error(R.string.erroApi)}
            }

            override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                state.value = SearchPokemonScreenState.Error(R.string.erro)
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