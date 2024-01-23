package com.douglas2990.pokedexmyapplication2990.type.detailType.viewModelType

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.api.RestManager
import com.douglas2990.pokedexmyapplication2990.model.type.typeDetail.DetailType
import com.douglas2990.pokedexmyapplication2990.type.detailType.DetailTypeScreenState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModelTypeDragon: ViewModel() {
    val state: MutableLiveData<DetailTypeScreenState> by lazy {
        MutableLiveData<DetailTypeScreenState>()
    }
    init {
        state.value = DetailTypeScreenState.Loading
        RestManager.getEndpoints().getTypeDragon().enqueue(object : Callback<DetailType> {
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
}