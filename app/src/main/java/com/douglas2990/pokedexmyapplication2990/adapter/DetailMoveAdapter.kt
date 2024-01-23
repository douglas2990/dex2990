package com.douglas2990.pokedexmyapplication2990.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.model.moves.LearnedByPokemon

class DetailMoveAdapter(private val list: List<LearnedByPokemon>,
                        private var listenner: PokemonInterface? = null)
            :RecyclerView.Adapter<DetailMoveAdapter.DetailMoveViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailMoveViewHolder {
        val view = LayoutInflater.from(parent.context)
            //.inflate(R.layout.pokemon_adapter, parent, false)
            .inflate(R.layout.detail_auxiliar_adapter, parent, false)

        return DetailMoveViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailMoveViewHolder, position: Int) {
        val pokemon = list[position]
        val namePokemon = pokemon.name
        val pokemonId = pokemon.url.replace("https://pokeapi.co/api/v2/pokemon/", "")
            .replace("/", "")

        holder.texViewPokemon.text = namePokemon.capitalize()
        if (pokemonId.toInt() < 900){
            holder.textViewNumero.text = pokemonId.padStart(3, '0')
        }else {
            holder.textViewNumero.text = ""
        }
        holder.texViewPokemon.setOnClickListener {
            listenner?.onClick(pokemonId)
        }

        Glide.with(holder.imgViewPokemon)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + pokemonId + ".png")
            .into(holder.imgViewPokemon)
    }

    class DetailMoveViewHolder(view: View):RecyclerView.ViewHolder(view){
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