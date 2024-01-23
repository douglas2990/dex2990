package com.douglas2990.pokedexmyapplication2990.type

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.api.RestManager
import com.douglas2990.pokedexmyapplication2990.model.type.AllTypes
import com.douglas2990.pokedexmyapplication2990.model.type.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TypeViewModel: ViewModel() {

    val state: MutableLiveData<TypeScreenState> by lazy {
        MutableLiveData<TypeScreenState>()
    }
    init {
        state.value = TypeScreenState.Loading
        RestManager.getEndpoints().getAllType(40,0).enqueue(object :Callback<AllTypes> {
            override fun onResponse(
                call: Call<AllTypes>,
                response: Response<AllTypes>
            ) {
                response.body()?.let{ body ->
                    state.value = TypeScreenState.Success(body.results)
                } ?: run {state.value = TypeScreenState.Error(R.string.erroApi)}
            }

            override fun onFailure(call: Call<AllTypes>, t: Throwable) {
                state.value = TypeScreenState.Error(R.string.erro)
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
                //val nome = query.name.capitalize()
                val id = query.url.replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "").lowercase()
                if (nome.contains(texto !!) || id.contains(texto)) {
                    //if (nome.contains(texto !!) ) {
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