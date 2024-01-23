package com.douglas2990.pokedexmyapplication2990.model.responses

import com.google.gson.annotations.SerializedName

data class AbilityX(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("url")
    val url: String
)