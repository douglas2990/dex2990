package com.douglas2990.pokedexmyapplication2990.moves.movespokemon

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.adapter.MovesAdapter
import com.douglas2990.pokedexmyapplication2990.databinding.FragmentMovesPokemonBinding
import com.douglas2990.pokedexmyapplication2990.detail.DetailPokemonFactory
import com.douglas2990.pokedexmyapplication2990.detail.DetailPokemonViewModel
import com.douglas2990.pokedexmyapplication2990.detail.DetailScreenState
import com.douglas2990.pokedexmyapplication2990.detailSpecies.DetailPokemonSpeciesFactory
import com.douglas2990.pokedexmyapplication2990.detailSpecies.DetailPokemonSpeciesViewModel
import com.douglas2990.pokedexmyapplication2990.detailSpecies.DetailScreenStateSpecies

class MovesPokemonFragment : Fragment() {

    private var _binding: FragmentMovesPokemonBinding? = null

    private var pokemonShiny: Boolean = false


    private val viewModelMovesPokemon by viewModels<MovesPokemonViewModel>{
        MovesPokemonFactory(arguments?.getString("id","") ?: "")
    }

    private val movePokemonListener = object : MovesAdapter.MoveInterface{
        override fun onClick(moveId: String) {
            findNavController().navigate(R.id.MovePokemonFragmentToDetailMovesFragment, bundleOf("id" to moveId))
        }
    }

    private val viewModelPokemon by viewModels<DetailPokemonViewModel>{
        DetailPokemonFactory(arguments?.getString("id","") ?: "")
    }

    private val viewModelSpecies by viewModels<DetailPokemonSpeciesViewModel>{
        DetailPokemonSpeciesFactory(arguments?.getString("id","") ?: "")
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMovesPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabHome.setOnClickListener{
            findNavController().navigate(R.id.action_MoveFragment_to_first_fragment)
        }

        binding.recyclerViewPokemonMove.layoutManager = LinearLayoutManager(context)
        viewModelMovesPokemon.state.observe(viewLifecycleOwner,
         { state ->
            presentLoading(state is MovesPokemonScreenState.Loading)

            binding.movesPokemonProgress.isVisible = state is MovesPokemonScreenState.Loading
            binding.recyclerViewPokemonMove.isVisible = state is MovesPokemonScreenState.Success

            when(state){
                is MovesPokemonScreenState.Loading -> {}
                is MovesPokemonScreenState.Success -> {
                    binding.recyclerViewPokemonMove.adapter = MovesAdapter(state.data, movePokemonListener)
                }
                is MovesPokemonScreenState.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG)
            }
        })
        viewModelPokemon.state.observe(viewLifecycleOwner,
             { state ->

            when(state){
                is DetailScreenState.Loading -> {}
                is DetailScreenState.Success -> {
                    binding.imageViewMPokemon.setImageURI(state.data.sprites.other.home.front_default)
                    binding.txtMNamePokemon.text = state.data.name.capitalize()

                    var namePokemon = state.data.name.capitalize()
                    binding.btnMType1.isInvisible
                    binding.btnMType2.isInvisible
                    binding.btnMType.isInvisible

                    val tamanho_type = state.data.types.size
                    var type1 = state.data.types[0].type.name.capitalize()
                    var type2 = state.data.types[0].type.name.capitalize()

                    if(tamanho_type == 2){
                        type1 = state.data.types[0].type.name.capitalize()
                        type2 = state.data.types[1].type.name.capitalize()
                        binding.btnMType1.visibility = View.VISIBLE
                        binding.btnMType2.visibility = View.VISIBLE
                        binding.btnMType1.text = type1
                        binding.btnMType2.text = type2

                        binding.btnMType1.setOnClickListener {
                            val idType = state.data.types[0].type.url.replace("https://pokeapi.co/api/v2/type/", "")
                                .replace("/", "")
                            mostrarTypes(arguments?.getString(idType,idType) ?: idType)
                        }

                        binding.btnMType2.setOnClickListener {
                            val idType = state.data.types[1].type.url.replace("https://pokeapi.co/api/v2/type/", "")
                                .replace("/", "")
                            mostrarTypes(arguments?.getString(idType,idType) ?: idType)
                        }


                    }else{
                        type1 = state.data.types[0].type.name.capitalize()
                        type2 = state.data.types[0].type.name.capitalize()
                        binding.btnMType.visibility = View.VISIBLE
                        binding.btnMType1.visibility = View.INVISIBLE
                        binding.btnMType1.text = ""
                        binding.btnMType.text = type1
                        binding.btnMType2.text = ""

                        binding.btnMType.setOnClickListener {
                            val idType = state.data.types[0].type.url.replace("https://pokeapi.co/api/v2/type/", "")
                                .replace("/", "")
                            mostrarTypes(arguments?.getString(idType,idType) ?: idType)
                        }
                    }

                    binding.imageViewMPokemon.setOnClickListener {
                        mudarForma()
                        if (pokemonShiny == true){
                            binding.imageViewMPokemon.setImageURI(state.data.sprites.other.home.front_shiny)
                        }else {
                            binding.imageViewMPokemon.setImageURI(state.data.sprites.other.home.front_default)
                        }
                    }


                }
                is DetailScreenState.Error -> {
                    Toast.makeText(context, state.messageId, Toast.LENGTH_SHORT).show()
                }
            }
        })
        viewModelSpecies.state.observe(viewLifecycleOwner, { state ->

            when(state){
                is DetailScreenStateSpecies.Loading -> {}
                is DetailScreenStateSpecies.Success -> {
                    binding.layoutMovesPokemon.setBackgroundColor(state.data.color.url.hashCode())
                }
            }
        })

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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun presentLoading(show: Boolean){
        if(show){
            binding.shimmerImageViewMPokemon.showShimmer(true)
            binding.shimmerTxtMNamePokemon.showShimmer(true)
            binding.shimmerBtnMType1.showShimmer(true)
            binding.shimmerBtnMType2.showShimmer(true)
        }else {
            binding.shimmerImageViewMPokemon.hideShimmer()
            binding.shimmerTxtMNamePokemon.hideShimmer()
            binding.shimmerBtnMType1.hideShimmer()
            binding.shimmerBtnMType2.hideShimmer()
        }
    }

    fun mostrarTypes(pokemonId: String){
        findNavController().navigate(R.id.MovePokemonFragmentToFragmentDetailMenu, bundleOf("id" to pokemonId))
    }
}