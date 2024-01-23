package com.douglas2990.pokedexmyapplication2990.model

import com.google.gson.annotations.SerializedName

data class ModelStart (
    @field:SerializedName("ability")
    val ability : String,
    @field:SerializedName("berry")
    val berry : String,
    @field:SerializedName("berry-firmness")
    val berry_firmness : String,
    @field:SerializedName("berry-flavor")
    val berry_flavor : String,
    @field:SerializedName("characteristic")
    val characteristic : String,
    @field:SerializedName("contest-effect")
    val contest_effect : String,
    @field:SerializedName("contest-type")
    val contest_type : String,
    @field:SerializedName("egg-group")
    val egg_group : String,
    @field:SerializedName("encounter-condition")
    val encounter_condition : String,
    @field:SerializedName("encounter-condition-value")
    val encounter_condition_value : String,
    @field:SerializedName("encounter-method")
    val encounter_method : String,
    @field:SerializedName("evolution-chain")
    val evolution_chain : String,
    @field:SerializedName("evolution-trigger")
    val evolution_trigger : String,
    @field:SerializedName("gender")
    val gender : String,
    @field:SerializedName("generation")
    val generation : String,
    @field:SerializedName("growth-rate")
    val growth_rate : String,
    @field:SerializedName("item")
    val item : String,
    @field:SerializedName("item-attribute")
    val item_attribute: String,
    @field:SerializedName("item-category")
    val item_category: String,
    @field:SerializedName("item-fling-effect")
    val item_fling_effect: String,
    @field:SerializedName("item-pocket")
    val item_pocket: String,
    @field:SerializedName("language")
    val language: String,
    @field:SerializedName("location")
    val location: String,
    @field:SerializedName("location-area")
    val location_area: String,
    @field:SerializedName("machine")
    val machine: String,
    @field:SerializedName("move")
    val move: String,
    @field:SerializedName("move-ailment")
    val move_ailment: String,
    @field:SerializedName("move-battle-style")
    val move_battle_style: String,
    @field:SerializedName("move-category")
    val move_category: String,
    @field:SerializedName("move-damage-class")
    val move_damage_class: String,
    @field:SerializedName("move-learn-method")
    val move_learn_method: String,
    @field:SerializedName("move-target")
    val move_target: String,
    @field:SerializedName("nature")
    val nature: String,
    @field:SerializedName("pal-park-area")
    val pal_park_area: String,
    @field:SerializedName("pokeathlon-stat")
    val pokeathlon_stat: String,
    @field:SerializedName("pokedex")
    val pokedex: String,
    @field:SerializedName("pokemon")
    val pokemon: String,
    @field:SerializedName("pokemon-color")
    val pokemon_color: String,
    @field:SerializedName("pokemon-form")
    val pokemon_form: String,
    @field:SerializedName("pokemon-habitat")
    val pokemon_habitat: String,
    @field:SerializedName("pokemon-shape")
    val pokemon_shape: String,
    @field:SerializedName("pokemon-species")
    val species: String,
    @field:SerializedName("region")
    val region: String,
    @field:SerializedName("stat")
    val stat: String,
    @field:SerializedName("super-contest-effect")
    val super_contest_effect: String,
    @field:SerializedName("type")
    val type: String,
    @field:SerializedName("version")
    val version: String,
    @field:SerializedName("version-group")
    val version_group: String,
)