package com.douglas2990.pokedexmyapplication2990.model.responses

import com.google.gson.annotations.SerializedName

data class GenerationIv(
   // val diamond-pearl: DiamondPearl,
    @field:SerializedName("diamond-pearl")
    val diamond_pearl: DiamondPearl,
   // val heartgold-soulsilver: HeartgoldSoulsilver,
    @field:SerializedName("heartgold-soulsilver")
    val heartgold_soulsilver: HeartgoldSoulsilver,
    @field:SerializedName("platinum")
    val platinum: Platinum
)