package com.douglas2990.pokedexmyapplication2990.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.model.type.typeDetail.HalfDamageFrom

class HalfDamageFromAdapter(private val list: List<HalfDamageFrom>,
                            private  var listenner: HalfDamageFromInterface)
    : RecyclerView.Adapter<HalfDamageFromAdapter.HalfDamageFromViewHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HalfDamageFromViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.type_adapter, parent, false)

        return HalfDamageFromViewHolder(view)
    }

    override fun onBindViewHolder(holder: HalfDamageFromViewHolder, position: Int) {
        val typeList = list[position]
        val type = typeList.name

        val typeId = typeList.url.replace("https://pokeapi.co/api/v2/type/", "")
            .replace("/", "")

        holder.textNomeType.text = type

        holder.textNomeType.setOnClickListener {
            listenner.onClick(typeId)
        }
    }

    class HalfDamageFromViewHolder(view: View): RecyclerView.ViewHolder(view){
        var textNomeType: TextView = view.findViewById(R.id.textViewType)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface HalfDamageFromInterface{
        fun onClick(type: String)
    }

}