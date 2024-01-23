package com.douglas2990.pokedexmyapplication2990.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.model.moves.Result

class AllMovesAdapter(private val list: List<Result>,
                        private var listenner: MoveInterface ? = null)
    :RecyclerView.Adapter<AllMovesAdapter.MoveViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoveViewHolder {
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.all_moves_adapter, parent, false)

        return MoveViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoveViewHolder, position: Int) {
        val moveList = list[position]
        val move = moveList.name

        val moveId = moveList.url.replace("https://pokeapi.co/api/v2/move/", "")
            .replace("/", "")

        holder.textMove.text = move

        holder.textMove.setOnClickListener {
            listenner?.onClick(moveId)
        }
    }

    class MoveViewHolder(view: View):RecyclerView.ViewHolder(view){
        var textMove: TextView = view.findViewById(R.id.textViewType)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface MoveInterface{
        fun onClick(moveId:String)
    }
}