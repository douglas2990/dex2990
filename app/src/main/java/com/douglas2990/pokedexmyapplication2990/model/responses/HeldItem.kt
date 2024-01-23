package com.douglas2990.pokedexmyapplication2990.model.responses

import com.google.gson.annotations.SerializedName

data class HeldItem(
    @field:SerializedName("item")
    val item: Item,
    @field:SerializedName("version_details")
    val version_details: List<VersionDetail>
)