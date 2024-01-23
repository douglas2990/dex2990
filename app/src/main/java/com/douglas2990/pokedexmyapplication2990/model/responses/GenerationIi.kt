package com.douglas2990.pokedexmyapplication2990.model.responses

import com.google.gson.annotations.SerializedName

data class GenerationIi(
    @field:SerializedName("crystal")
    val crystal: Crystal,
    @field:SerializedName("gold")
    val gold: Gold,
    @field:SerializedName("silver")
    val silver: Silver
)