package com.douglas2990.pokedexmyapplication2990.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.model.type.Result

class AllTypesAdapter(private val list: List<Result>,
                        private var listenner: TypeInterface? = null)
    :RecyclerView.Adapter<AllTypesAdapter.TypeViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TypeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.type_adapter, parent, false)

        return TypeViewHolder(view)
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        val typList = list[position]
        val type = typList.name
        val typeId = typList.url.replace("https://pokeapi.co/api/v2/type/", "")
            .replace("/", "")

        holder.textNomeType.text = type

        holder.textNomeType.setOnClickListener {
            listenner?.onClick(typeId)
        }
    }

    class TypeViewHolder(view: View):RecyclerView.ViewHolder(view){
        var textNomeType: TextView = view.findViewById(R.id.textViewType)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface TypeInterface{
        fun onClick(typeId:String)
    }
}