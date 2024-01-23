package com.douglas2990.pokedexmyapplication2990.model.type.typeDetail

data class DetailType(
    val damage_relations: DamageRelations,
    val game_indices: List<GameIndice>,
    val generation: GenerationX,
    val id: Int,
    val move_damage_class: MoveDamageClass,
    val moves: List<Move>,
    val name: String,
    val names: List<Name>,
    val past_damage_relations: List<Any>,
    val pokemon: List<Pokemon>
)