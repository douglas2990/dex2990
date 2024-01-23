package com.douglas2990.pokedexmyapplication2990.model.responses

import com.google.gson.annotations.SerializedName

data class Ability(
    @field:SerializedName("ability")
    val ability: AbilityX,
    @field:SerializedName("is_hidden")
    val is_hidden: Boolean,
    @field:SerializedName("slot")
    val slot: Int
)