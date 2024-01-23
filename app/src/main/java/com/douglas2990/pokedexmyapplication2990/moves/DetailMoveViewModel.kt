package com.douglas2990.pokedexmyapplication2990.moves

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.api.RestManager
import com.douglas2990.pokedexmyapplication2990.model.moves.MovesDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMoveViewModel(moveID: String) : ViewModel() {
    val state:MutableLiveData<DetailMoveScreenState> by lazy {
        MutableLiveData<DetailMoveScreenState>()
    }
    init {
        state.value = DetailMoveScreenState.Loading
        RestManager.getEndpoints().getAllMovesDetail(moveID).enqueue(object : Callback<MovesDetail> {
            override fun onResponse(call: Call<MovesDetail>, response: Response<MovesDetail>) {
                response.body()?.let { body ->
                    state.value = DetailMoveScreenState.Success(body)
                }?: run{state.value = DetailMoveScreenState.Error(R.string.erroApi)}
            }

            override fun onFailure(call: Call<MovesDetail>, t: Throwable) {
                state.value = DetailMoveScreenState.Error(R.string.erro)
            }

        })
    }
}