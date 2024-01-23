package com.douglas2990.pokedexmyapplication2990.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.model.type.typeDetail.HalfDamageTo

class HalfDamageToAdapter(private val list: List<HalfDamageTo>,
                          private var listenner: HalfDamageToInterface )
    : RecyclerView.Adapter<HalfDamageToAdapter.HalfDamageToViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HalfDamageToViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.type_adapter, parent, false)

        return HalfDamageToViewHolder(view)
    }

    override fun onBindViewHolder(holder: HalfDamageToViewHolder, position: Int) {
        val typeList = list[position]
        val type = typeList.name

        val typeId = typeList.url.replace("https://pokeapi.co/api/v2/type/", "")
            .replace("/", "")

        holder.textNomeType.text = type

        holder.textNomeType.setOnClickListener {
            listenner.onClick(typeId)
        }
    }

    class HalfDamageToViewHolder(view: View): RecyclerView.ViewHolder(view){
        var textNomeType: TextView = view.findViewById(R.id.textViewType)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface HalfDamageToInterface{
        fun onClick(type: String)
    }

}