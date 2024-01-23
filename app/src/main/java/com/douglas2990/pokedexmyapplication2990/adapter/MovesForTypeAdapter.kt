package com.douglas2990.pokedexmyapplication2990.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.model.type.typeDetail.Move

class MovesForTypeAdapter (private val list: List<Move>,
                           private var listenner: MovesForTypeToInterface )
    : RecyclerView.Adapter<MovesForTypeAdapter.MoveForTypeViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoveForTypeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.type_adapter, parent, false)

        return MoveForTypeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoveForTypeViewHolder, position: Int) {
        val typeList = list[position]
        val type = typeList.name
        val typeId = typeList.url.replace("https://pokeapi.co/api/v2/move/", "")
            .replace("/", "")

        holder.textNomeType.text = type

        holder.textNomeType.setOnClickListener {
            listenner.onClick(typeId)
        }
    }

    class MoveForTypeViewHolder(view: View): RecyclerView.ViewHolder(view){
        var textNomeType: TextView = view.findViewById(R.id.textViewType)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface MovesForTypeToInterface{
        fun onClick(type: String)
    }
}