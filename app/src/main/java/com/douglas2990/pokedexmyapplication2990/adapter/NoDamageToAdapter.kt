package com.douglas2990.pokedexmyapplication2990.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.model.type.typeDetail.NoDamageTo

class NoDamageToAdapter(private var list: List<NoDamageTo>,
                        private var listenner: NoDamageToInterface)
    : RecyclerView.Adapter<NoDamageToAdapter.NoDamageToViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoDamageToViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.type_adapter, parent, false)

        return NoDamageToViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoDamageToViewHolder, position: Int) {
        val typeList = list[position]
        val type = typeList.name

        val typeId = typeList.url.replace("https://pokeapi.co/api/v2/type/", "")
            .replace("/", "")

        holder.textNomeType.text = type

        holder.textNomeType.setOnClickListener {
            listenner.onClick(typeId)
        }
    }

    class NoDamageToViewHolder(view: View): RecyclerView.ViewHolder(view){
        var textNomeType: TextView = view.findViewById(R.id.textViewType)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface NoDamageToInterface{
        fun onClick(type: String)
    }

}
