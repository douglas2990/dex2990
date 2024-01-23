package com.douglas2990.pokedexmyapplication2990.imagesDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.api.RestManager
import com.douglas2990.pokedexmyapplication2990.model.responses.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImagesViewModel (pokemonId: String) : ViewModel() {
    val state: MutableLiveData<ImageScreenState> by lazy {
        MutableLiveData<ImageScreenState>()
    }
    init {
        state.value = ImageScreenState.Loading
        RestManager.getEndpoints().pokemonDittoDetail(pokemonId).enqueue(object :
            Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let { body ->
                    state.value = ImageScreenState.Success(body)
                }?: run{ state.value = ImageScreenState.Error(R.string.app_name)

                }
            }
            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                state.value = ImageScreenState.Error(R.string.app_name)
            }
        })

    }
}