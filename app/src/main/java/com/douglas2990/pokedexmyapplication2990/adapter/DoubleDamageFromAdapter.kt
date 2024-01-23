package com.douglas2990.pokedexmyapplication2990.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.model.type.typeDetail.DoubleDamageFrom

class DoubleDamageFromAdapter(private val list: List<DoubleDamageFrom>,
                              private var listenner: DoubleDamageFromInterface)
    : RecyclerView.Adapter<DoubleDamageFromAdapter.DoubleDamageFromViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DoubleDamageFromViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.type_adapter, parent, false)

        return DoubleDamageFromViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoubleDamageFromViewHolder, position: Int) {
        val typeList = list[position]
        val type = typeList.name

        val typeId = typeList.url.replace("https://pokeapi.co/api/v2/type/", "")
            .replace("/", "")

        holder.textNomeType.text = type

        holder.textNomeType.setOnClickListener {
            listenner.onClick(typeId)
        }
    }

    class DoubleDamageFromViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var textNomeType: TextView = view.findViewById(R.id.textViewType)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface DoubleDamageFromInterface{
        fun onClick(typeId: String)
    }
}