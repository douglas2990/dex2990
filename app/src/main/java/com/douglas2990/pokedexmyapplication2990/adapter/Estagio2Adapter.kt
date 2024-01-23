package com.douglas2990.pokedexmyapplication2990.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.model.evolution_chain.EvolvesTo

class Estagio2Adapter(private val list: List<EvolvesTo>,
                      private var listenner: Estagio2Interface? = null)
    :RecyclerView.Adapter<Estagio2Adapter.Estagio1ViewHolder>(){

    private var pokemonShiny: Boolean = false

     override fun onCreateViewHolder(
         parent: ViewGroup,
         viewType: Int
     ): Estagio1ViewHolder{
         val view = LayoutInflater.from(parent.context)
             .inflate(R.layout.evolucao_estagio_adapter, parent, false)

         return Estagio1ViewHolder(view)
     }

    override fun onBindViewHolder(holder: Estagio1ViewHolder, position: Int){
        val pokemon = list[position]
        val namePokemon = pokemon.species.name
        val estagio = "Estagio 2"
        val comoEvolui = pokemon.evolution_details[0].trigger.name
        val pokemonId = pokemon.species.url.replace("https://pokeapi.co/api/v2/pokemon-species/","")
            .replace("/", "")

        try {
            holder.textNamePokemon.text = namePokemon.capitalize()
            holder.textEstagio.text = estagio
            holder.textComoEvolui.text = comoEvolui

            holder.textNamePokemon.setOnClickListener {
                listenner?.onClick(pokemonId)
            }

            Glide.with(holder.imageEstagio1)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/" + pokemonId + ".png")
                .into(holder.imageEstagio1)

            holder.imageEstagio1.setOnClickListener{
                mudarForma()
                if(pokemonShiny == true){
                    Glide.with(holder.imageEstagio1)
                        .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/shiny/" + pokemonId + ".png")
                        .into(holder.imageEstagio1)
                }else{
                    Glide.with(holder.imageEstagio1)
                        .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/" + pokemonId + ".png")
                        .into(holder.imageEstagio1)
                }
            }
        }catch (e: Exception){
            holder.textNamePokemon.text = ""
            holder.textEstagio.text = ""
            holder.textComoEvolui.text = ""
        }

    }

    class Estagio1ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var imageEstagio1: ImageView = view.findViewById(R.id.imageViewEstagio2)
        var textEstagio: TextView = view.findViewById(R.id.textViewEstagio)
        var textNamePokemon: TextView = view.findViewById(R.id.textViewNomePokemon)
        var textComoEvolui: TextView = view.findViewById(R.id.textViewComoEvolui)

    }
    override fun getItemCount(): Int{
        return list.size
    }

    private fun mudarForma() {
        if (pokemonShiny == true){
            pokemonShiny = false
        } else {
            if(pokemonShiny == false){
                pokemonShiny = true
            }
        }
    }
    interface Estagio2Interface {
        fun onClick(pokemonId: String)
    }
}

