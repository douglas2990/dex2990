package com.douglas2990.pokedexmyapplication2990.model.responses

import com.google.gson.annotations.SerializedName

data class Other(
    val dream_world: DreamWorld,
    val home: Home,
    //val official-artwork: OfficialArtwork
    @field:SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork
)