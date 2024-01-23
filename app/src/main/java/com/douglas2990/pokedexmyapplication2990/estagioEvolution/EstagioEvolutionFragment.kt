package com.douglas2990.pokedexmyapplication2990.estagioEvolution

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
import com.douglas2990.pokedexmyapplication2990.adapter.Estagio2Adapter
import com.douglas2990.pokedexmyapplication2990.adapter.Estagio3Adapter
import com.douglas2990.pokedexmyapplication2990.databinding.FragmentEstagioEvolucaoBinding
import com.douglas2990.pokedexmyapplication2990.evolution.DetailPokemonEvolutionFactory
import com.douglas2990.pokedexmyapplication2990.evolution.DetailPokemonEvolutionViewModel
import com.douglas2990.pokedexmyapplication2990.evolution.DetailScreenStateEvolution

class EstagioEvolutionFragment : Fragment() {

    private var pokemonShiny: Boolean = false

    private var _binding: FragmentEstagioEvolucaoBinding? = null

    private val viewModelEstagio1 by viewModels<DetailPokemonEvolutionViewModel> {
        DetailPokemonEvolutionFactory(arguments?.getString("id", "") ?: "")
    }

    private val viewModelEstagio2 by viewModels<Estagio2EvolutionViewModel>{
        Estagio2EvolutionFactory(arguments?.getString("id","") ?: "")
    }
    private val estagio2Listener = object : Estagio2Adapter.Estagio2Interface{
        override fun onClick(pokemonId: String){
            findNavController().navigate(R.id.EstagioEvolutionFragmentToSecondFragment, bundleOf("id" to pokemonId))
        }
    }

    private val viewModelEstagio3Teste by viewModels<Estagio3EvolutionViewModel>{
        Estagio3EvolutionFactory(arguments?.getString("id","") ?: "")
    }

    private val estagio3Listener = object : Estagio3Adapter.Estagio3Interface{
        override fun onClick(pokemonId: String){
            findNavController().navigate(R.id.EstagioEvolutionFragmentToSecondFragment, bundleOf("id" to pokemonId))
        }
    }

    private val viewModelPokemon by viewModels<DetailPokemonEvolutionViewModel> {
        DetailPokemonEvolutionFactory(arguments?.getString("id", "") ?: "")
    }


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEstagioEvolucaoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelEstagio1.state.observe(viewLifecycleOwner,
         Observer{ state ->
             presentLoading(state is DetailScreenStateEvolution.Loading)
             binding.estagioProgress.isVisible = state is DetailScreenStateEvolution.Loading


            when(state){
                is DetailScreenStateEvolution.Loading -> {}
                is DetailScreenStateEvolution.Success -> {

                    val idPokemonSpeciesEstagio1 = state.data.chain.species.url

                    val idPokemonReplaceEstagio1 = idPokemonSpeciesEstagio1.replace("https://pokeapi.co/api/v2/pokemon-species/", "")
                        .replace("/", "")

                    try {
                        if(state.data.chain.species.name != null) {
                            binding.textViewNomePokemon.text = state.data.chain.species.name.capitalize()
                            binding.textViewComoEvolui.text =
                                state.data.chain.evolves_to[0].evolution_details[0].trigger.name
                            //binding.imageViewEstagio2.setImageURI("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + idPokemonReplaceEstagio1 + ".png")
                            binding.imageViewEstagio2.setImageURI("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/" + idPokemonReplaceEstagio1 + ".png")
                            binding.imageViewEstagio2.setOnClickListener {
                                mudarForma()
                                if (pokemonShiny == true){
                                    binding.imageViewEstagio2.setImageURI("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/shiny/" + idPokemonReplaceEstagio1 + ".png")
                                }else {
                                    binding.imageViewEstagio2.setImageURI("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/" + idPokemonReplaceEstagio1 + ".png")
                                }
                            }
                            binding.textViewNomePokemon.setOnClickListener {
                                mostrarSecondFragment(arguments?.getString(idPokemonReplaceEstagio1,idPokemonReplaceEstagio1) ?: idPokemonReplaceEstagio1)
                            }
                        }else{
                            binding.textViewNomePokemon.text = ""
                            binding.textViewComoEvolui.text = ""
                        }
                    }catch (e: Exception){
                        binding.textViewComoEvolui.text = ""
                    }

                    try {
                        val chegar2 = state.data.chain.evolves_to
                        //var chegar3 = state.data.chain.evolves_to[0].evolves_to
                        if (!chegar2.isNullOrEmpty()){
                            binding.recyclerViewEstagio2.layoutManager = LinearLayoutManager(context)
                            viewModelEstagio2.state.observe(viewLifecycleOwner,
                                Observer { state ->
                                    binding.recyclerViewEstagio2.isVisible = state is Estagio2EvolutionScreenState.Success
                                    binding.estagioProgress.isVisible = state is Estagio2EvolutionScreenState.Loading

                                    when(state){
                                        is Estagio2EvolutionScreenState.Loading -> {}
                                        is Estagio2EvolutionScreenState.Success ->{
                                            binding.recyclerViewEstagio2.adapter = Estagio2Adapter(state.data, estagio2Listener)
                                        }
                                        is Estagio2EvolutionScreenState.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG)
                                    }
                                })
                        }
                    }catch (e:Exception) {
                            binding.recyclerViewEstagio2.isInvisible
                            binding.recyclerViewEstagio3.isInvisible
                            binding.textViewNomePokemon.text = state.data.chain.species.name
                            binding.textViewComoEvolui.text = ""
                            binding.imageViewEstagio2.setImageURI("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/" + idPokemonReplaceEstagio1 + ".png")
                            binding.imageViewEstagio2.setOnClickListener {
                                mudarForma()
                                if (pokemonShiny == true){
                                    binding.imageViewEstagio2.setImageURI("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/shiny/" + idPokemonReplaceEstagio1 + ".png")
                                }else {
                                    binding.imageViewEstagio2.setImageURI("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/" + idPokemonReplaceEstagio1 + ".png")
                                }
                            }
                    }
                    try {
                        var chegar2 = state.data.chain.evolves_to
                        var chegar3 = state.data.chain.evolves_to[0].evolves_to
                        if (!chegar3.isNullOrEmpty() && !chegar2.isNullOrEmpty()){
                            binding.recyclerViewEstagio2.layoutManager = LinearLayoutManager(context)
                            binding.recyclerViewEstagio3.layoutManager = LinearLayoutManager(context)

                            viewModelEstagio3Teste.state.observe(viewLifecycleOwner,
                                Observer { state ->
                                    binding.recyclerViewEstagio3.isVisible = state is Estagio3EvolutionScreenState.Success
                                    binding.estagioProgress.isVisible = state is Estagio3EvolutionScreenState.Loading

                                    when(state){
                                        is Estagio3EvolutionScreenState.Loading -> {}
                                        is Estagio3EvolutionScreenState.Success -> {
                                            binding.recyclerViewEstagio3.adapter = Estagio3Adapter(state.data, estagio3Listener)
                                        }
                                        is Estagio3EvolutionScreenState.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG)
                                    }
                                })
                        }
                    } catch (e:Exception){

                            viewModelPokemon.state.observe(viewLifecycleOwner, {
                                state is DetailScreenStateEvolution.Loading

                                when(state){
                                    is DetailScreenStateEvolution.Loading -> {}
                                    is DetailScreenStateEvolution.Success -> {

                                        try {
                                            val idPokemonSpeciesEvolucao1 = state.data.chain.species.url

                                            val idPokemonReplaceEvolucao1 = idPokemonSpeciesEvolucao1.replace("https://pokeapi.co/api/v2/pokemon-species/", "")
                                                .replace("/", "")

                                            binding.textViewNomePokemon.text = state.data.chain.species.name.capitalize()
                                            binding.imageViewEstagio2.setImageURI("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/" + idPokemonReplaceEvolucao1 + ".png")
                                            binding.imageViewEstagio2.setOnClickListener {
                                                mudarForma()
                                                if (pokemonShiny == true){
                                                    binding.imageViewEstagio2.setImageURI("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/shiny/" + idPokemonReplaceEvolucao1 + ".png")
                                                }else {
                                                    binding.imageViewEstagio2.setImageURI("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/" + idPokemonReplaceEvolucao1 + ".png")
                                                }
                                            }
                                            binding.textViewNomePokemon.setOnClickListener {
                                                mostrarSecondFragment(arguments?.getString(idPokemonReplaceEstagio1,idPokemonReplaceEstagio1) ?: idPokemonReplaceEstagio1)
                                            }

                                        }catch (e: Exception){
                                            binding.textViewNomePokemon.text = state.data.chain.species.name.capitalize()

                                        }
                                    }
                                }
                            })
                    }

                  }

                is DetailScreenStateEvolution.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG)
                }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun presentLoading(show: Boolean){
        if (show){
            binding.shimmerImageViewEstagio2.showShimmer(true)
            binding.shimmerTextViewComoEvolui.showShimmer(true)
            binding.shimmerTextViewNomePokemon.showShimmer(true)
            binding.shimmerTextViewMetodo.showShimmer(true)
            binding.shimmerTextViewEstagio.showShimmer(true)
        }else {
            binding.shimmerImageViewEstagio2.hideShimmer()
            binding.shimmerTextViewComoEvolui.hideShimmer()
            binding.shimmerTextViewNomePokemon.hideShimmer()
            binding.shimmerTextViewMetodo.hideShimmer()
            binding.shimmerTextViewEstagio.hideShimmer()
        }
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
    fun mostrarSecondFragment(pokemonId: String){
        findNavController().navigate(R.id.EstagioEvolutionFragmentToSecondFragment, bundleOf("id" to pokemonId))
    }
}