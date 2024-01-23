package com.douglas2990.pokedexmyapplication2990.model.responses

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)