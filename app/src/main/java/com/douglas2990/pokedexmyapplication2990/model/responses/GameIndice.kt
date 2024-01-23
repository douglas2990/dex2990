package com.douglas2990.pokedexmyapplication2990.model.responses

import com.google.gson.annotations.SerializedName

data class GameIndice(
    @field:SerializedName("game_index")
    val game_index: Int,
    @field:SerializedName("version")
    val version: Version
)