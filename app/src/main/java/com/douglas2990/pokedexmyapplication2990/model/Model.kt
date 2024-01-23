package com.douglas2990.pokedexmyapplication2990.model

import com.douglas2990.pokedexmyapplication2990.model.Results
import com.google.gson.annotations.SerializedName

class Model (
    @field:SerializedName("count")
    val count : Int,
    @field:SerializedName("next")
    val next : String,
    @field:SerializedName("previous")
    val previous : String,
    @field:SerializedName("results")
    val results: List<Results>
    )


