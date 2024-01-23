package com.douglas2990.pokedexmyapplication2990.type.detailType

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.api.RestManager
import com.douglas2990.pokedexmyapplication2990.model.type.typeDetail.DetailType
import com.douglas2990.pokedexmyapplication2990.model.type.typeDetail.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTypeViewModel(typeID: String) : ViewModel() {
    val state: MutableLiveData<DetailTypeScreenState> by lazy {
        MutableLiveData<DetailTypeScreenState>()
    }
    init {
        state.value = DetailTypeScreenState.Loading
        RestManager.getEndpoints().getTypeDetail(typeID).enqueue(object : Callback<DetailType> {
            override fun onResponse(call: Call<DetailType>, response: Response<DetailType>) {
                    response.body()?.let { body ->
                    state.value = DetailTypeScreenState.Success(body)
                }?: run{state.value = DetailTypeScreenState.Error(R.string.erro)}
            }

            override fun onFailure(call: Call<DetailType>, t: Throwable) {
               state.value = DetailTypeScreenState.Error(R.string.erro)
            }

        })
    }

    var listaQuery: List<Pokemon> = ArrayList()
    val listaQueryBusca: MutableList<Pokemon> = ArrayList()

    fun pesquisarQuery(texto: String?) {
        listaQueryBusca.clear()

        for (query in listaQuery) {
            if (query.pokemon.name != null) {
                val nome = query.pokemon.name.lowercase()
                val id = query.pokemon.url.replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "").lowercase()
                if (nome.contains(texto !!) || id.contains(texto)) {
                    listaQueryBusca.add(query)
                }
            } else {
                val nome = query.pokemon.name
                val id = query.pokemon.url.replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "")
                if (nome.contains(texto !!) || id.contains(texto)) {
                    listaQueryBusca.add(query)
                }
            }
        }
    }
}