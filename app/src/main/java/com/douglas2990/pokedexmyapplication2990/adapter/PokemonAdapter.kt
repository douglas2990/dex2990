package com.douglas2990.pokedexmyapplication2990.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.model.responses.Result

class PokemonAdapter(private val list: List<Result>,
                     private var listenner: PokemonInterface ? = null)
    :RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_adapter, parent, false)

        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = list[position]
        val namePokemon = pokemon.name
        val pokemonId = pokemon.url.replace("https://pokeapi.co/api/v2/pokemon/", "")
            .replace("/", "")


        holder.texViewPokemon.text = namePokemon.capitalize()
        if(pokemonId.toInt() < 900) {
            holder.textViewNumero.text = pokemonId.padStart(3, '0')
        }else{
            holder.textViewNumero.text = ""
        }
        holder.texViewPokemon.setOnClickListener {
            listenner?.onClick(pokemonId)
        }

        Glide.with(holder.imgViewPokemon)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + pokemonId + ".png")
            .into(holder.imgViewPokemon)
    }

    class PokemonViewHolder(view: View):RecyclerView.ViewHolder(view){
        var texViewPokemon: TextView = view.findViewById(R.id.txtNomePokemon)
        var textViewNumero: TextView = view.findViewById(R.id.txtNumeroPokemon)
        var imgViewPokemon: ImageView = view.findViewById(R.id.imgPokemon)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface PokemonInterface {
        fun onClick(pokemonId: String)
    }


}