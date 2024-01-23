package com.douglas2990.pokedexmyapplication2990.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.model.modelSpecies.Variety

class VarietiesAdapter(private val list: List<Variety>,
                       private var listenner: VarietiesInterface ? = null)
    :RecyclerView.Adapter<VarietiesAdapter.VarietiesViewHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VarietiesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.varieties_adapter, parent, false)

        return VarietiesViewHolder(view)
    }

    override fun onBindViewHolder(holder: VarietiesViewHolder, position: Int) {
        val pokemon = list[position]
        val namePokemon = pokemon.pokemon.name
        val pokemonId = pokemon.pokemon.url.replace("https://pokeapi.co/api/v2/pokemon/", "")
            .replace("/", "")

        Glide.with(holder.imgViewPokemon)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + pokemonId + ".png")
            .into(holder.imgViewPokemon)

        holder.textViewPokemon.text = namePokemon.capitalize()

        holder.textViewPokemon.setOnClickListener {
            listenner?.onClick(pokemonId)
        }
    }



    override fun getItemCount(): Int {
        return list.size
    }

    class VarietiesViewHolder(view: View):RecyclerView.ViewHolder(view){
        var textViewPokemon: TextView = view.findViewById(R.id.detail_namePokemon)
        var imgViewPokemon: ImageView = view.findViewById(R.id.detail_pokemon)
    }

    interface VarietiesInterface{
        fun onClick(pokemonId: String)
    }


}