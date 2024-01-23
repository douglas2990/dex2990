package com.douglas2990.pokedexmyapplication2990.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.model.responses.Move

class MovesAdapter(private val list: List<Move>,
                   private var listenner: MoveInterface? = null)
    :RecyclerView.Adapter<MovesAdapter.MovesViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.move_adapter, parent, false)

        return MovesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovesViewHolder, position: Int) {
        val moveList = list.sortedBy { it.version_group_details[0].level_learned_at  }[position]
        val move = moveList.move.name
        val nivelLearn = moveList.version_group_details[0].level_learned_at
        val moveId = moveList.move.url.replace("https://pokeapi.co/api/v2/move/", "")
            .replace("/", "")

        if (nivelLearn != 0){
            holder.textNivelLearn.text = nivelLearn.toString()
        }else{
            holder.textNivelLearn.text = "--"
        }

        holder.textMove.text = move
        holder.textMove.setOnClickListener {
            listenner?.onClick(moveId)
        }

    }

    class MovesViewHolder(view: View):RecyclerView.ViewHolder(view){
        var textMove: TextView = view.findViewById(R.id.txtMove)
        var textNivelLearn: TextView = view.findViewById(R.id.txtNivelLearn)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface MoveInterface{
        fun onClick(moveId:String)
    }
}