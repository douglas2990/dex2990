package com.douglas2990.pokedexmyapplication2990.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.adapter.DetailTypeAdapter
import com.douglas2990.pokedexmyapplication2990.adapter.SearchPokemonAdapter
import com.douglas2990.pokedexmyapplication2990.databinding.SearchPokemonFragmentBinding
import com.douglas2990.pokedexmyapplication2990.model.responses.Result
import com.douglas2990.pokedexmyapplication2990.type.detailType.DetailTypeScreenState
import com.douglas2990.pokedexmyapplication2990.type.detailType.viewModelType.*

class SearchPokemonFragment : Fragment() {

    private var _binding: SearchPokemonFragmentBinding? = null

    private val viewModelSearchView: SearchPokemonViewModel by viewModels()

    private val searchListener = object : SearchPokemonAdapter.PokemonSearchInterface{
        override fun onClick(pokemonId: String){
            findNavController().navigate(R.id.action_SearchPokemonFragment_to_SecondFragment, bundleOf("id" to pokemonId))

        }
    }

    private val firstListenner = object : DetailTypeAdapter.DetailTypeInterface{
        override fun onClick(pokemonId: String) {
            findNavController().navigate(R.id.action_SearchPokemonFragment_to_SecondFragment, bundleOf("id" to pokemonId))
        }

    }

    private var listaPokemon: List<Result> = ArrayList()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = SearchPokemonFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recyclerSearchPokemon.layoutManager = LinearLayoutManager(context)
        viewModelSearchView.state.observe(viewLifecycleOwner,
                { state ->
                    binding.firstProgress.isVisible = state is SearchPokemonScreenState.Loading
                    binding.recyclerSearchPokemon.isVisible = state is SearchPokemonScreenState.Success

                    when(state) {

                        is SearchPokemonScreenState.Loading -> {}
                        is SearchPokemonScreenState.Success -> {

                            listaPokemon = state.data

                        binding.recyclerSearchPokemon.adapter = SearchPokemonAdapter(listaPokemon,searchListener)

                        binding.searchView.setOnQueryTextListener(object :
                            android.widget.SearchView.OnQueryTextListener {
                            override fun onQueryTextSubmit(query: String?): Boolean {
                                binding.searchView.clearFocus()

                                    pesquisarPokemon(query?.lowercase())

                                    binding.recyclerSearchPokemon.adapter = SearchPokemonAdapter(listaPokemonBusca,searchListener)

                                return true

                            }

                            override fun onQueryTextChange(newText: String?): Boolean {
                                pesquisarPokemon(newText?.lowercase())

                                binding.recyclerSearchPokemon.adapter = SearchPokemonAdapter(listaPokemonBusca,searchListener)

                                return true
                            }


                        })

                        }

                        is SearchPokemonScreenState.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG).show()
                    }

                }
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    val listaPokemonBusca: MutableList<Result> = ArrayList()
    fun pesquisarPokemon(texto: String?) {

        listaPokemonBusca.clear()

        for (pokemon in listaPokemon) {
            if (pokemon.name != null) {
                val nome = pokemon.name.toLowerCase()
                val id = pokemon.url.replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "").toLowerCase()
                if (nome.contains(texto !!) || id.contains(texto)) {
                    listaPokemonBusca.add(pokemon)
                }
            } else {
                val nome = pokemon.name
                val id = pokemon.url.replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "")
                if (nome.contains(texto !!) || id.contains(texto)) {
                    listaPokemonBusca.add(pokemon)
                }
            }
        }
    }

    fun buscaAvancada(query: String?){

        if(query == "normal"){
            val viewModelDetailTypeNormal: DetailViewModelTypeNormal by viewModels()
            binding.recyclerSearchPokemon.layoutManager = LinearLayoutManager(context)
            viewModelDetailTypeNormal.state.observe(viewLifecycleOwner,
                Observer { state ->
                    binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                    binding.recyclerSearchPokemon.isVisible = state is DetailTypeScreenState.Success

                    when(state){
                        is DetailTypeScreenState.Loading -> {}
                        is DetailTypeScreenState.Success -> {
                            binding.recyclerSearchPokemon.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                        }
                    }
                })
        }

        if(query == "fighting" || query == "lutador"){
            val viewModelDetailTypeFighting: DetailViewModelTypeFighting by viewModels()
            binding.recyclerSearchPokemon.layoutManager = LinearLayoutManager(context)
            viewModelDetailTypeFighting.state.observe(viewLifecycleOwner,
                Observer { state ->
                    binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                    binding.recyclerSearchPokemon.isVisible = state is DetailTypeScreenState.Success

                    when(state){
                        is DetailTypeScreenState.Loading -> {}
                        is DetailTypeScreenState.Success -> {
                            binding.recyclerSearchPokemon.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                        }
                    }
                })
        }

        if(query == "flying" || query == "voador"){
            val viewModelDetailTypeFlying: DetailViewModelTypeFlying by viewModels()
            binding.recyclerSearchPokemon.layoutManager = LinearLayoutManager(context)
            viewModelDetailTypeFlying.state.observe(viewLifecycleOwner,
                Observer { state ->
                    binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                    binding.recyclerSearchPokemon.isVisible = state is DetailTypeScreenState.Success

                    when(state){
                        is DetailTypeScreenState.Loading -> {}
                        is DetailTypeScreenState.Success -> {
                            binding.recyclerSearchPokemon.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                        }
                    }
                })
        }

        if (query == "poison" || query == "venenoso"){
            val viewModelDetailTypePoison: DetailViewModelTypePoison by viewModels()
            binding.recyclerSearchPokemon.layoutManager = LinearLayoutManager(context)
            viewModelDetailTypePoison.state.observe(viewLifecycleOwner,
                Observer { state ->
                    binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                    binding.recyclerSearchPokemon.isVisible = state is DetailTypeScreenState.Success

                    when(state){
                        is DetailTypeScreenState.Loading -> {}
                        is DetailTypeScreenState.Success -> {
                            binding.recyclerSearchPokemon.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                        }
                    }
                })
        }

        if (query == "ground" || query == "terrestre"){
            val viewModelDetailTypeGround: DetailViewModelTypeGround by viewModels()
            binding.recyclerSearchPokemon.layoutManager = LinearLayoutManager(context)
            viewModelDetailTypeGround.state.observe(viewLifecycleOwner,
                Observer { state ->
                    binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                    binding.recyclerSearchPokemon.isVisible = state is DetailTypeScreenState.Success

                    when(state){
                        is DetailTypeScreenState.Loading -> {}
                        is DetailTypeScreenState.Success -> {
                            binding.recyclerSearchPokemon.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                        }
                    }
                })
        }

        if (query == "rock" || query == "pedra") {
            val viewModelDetailTypeRock: DetailViewModelTypeRock by viewModels()
            binding.recyclerSearchPokemon.layoutManager = LinearLayoutManager(context)
            viewModelDetailTypeRock.state.observe(viewLifecycleOwner,
                Observer { state ->
                    binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                    binding.recyclerSearchPokemon.isVisible = state is DetailTypeScreenState.Success

                    when(state){
                        is DetailTypeScreenState.Loading -> {}
                        is DetailTypeScreenState.Success -> {
                            binding.recyclerSearchPokemon.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                        }
                    }
                })
        }

        if(query == "bug" || query == "inseto"){
            val viewModelDetailTypeBug: DetailViewModelTypeBug by viewModels()
            binding.recyclerSearchPokemon.layoutManager = LinearLayoutManager(context)
            viewModelDetailTypeBug.state.observe(viewLifecycleOwner,
                Observer { state ->
                    binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                    binding.recyclerSearchPokemon.isVisible = state is DetailTypeScreenState.Success

                    when(state){
                        is DetailTypeScreenState.Loading -> {}
                        is DetailTypeScreenState.Success -> {
                            binding.recyclerSearchPokemon.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                        }
                    }
                })
        }

        if (query == "ghost" || query == "fanstasma"){
            val viewModelDetailTypeGhost: DetailViewModelTypeGhost by viewModels()
            binding.recyclerSearchPokemon.layoutManager = LinearLayoutManager(context)
            viewModelDetailTypeGhost.state.observe(viewLifecycleOwner,
                Observer { state ->
                    binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                    binding.recyclerSearchPokemon.isVisible = state is DetailTypeScreenState.Success

                    when(state){
                        is DetailTypeScreenState.Loading -> {}
                        is DetailTypeScreenState.Success -> {
                            binding.recyclerSearchPokemon.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                        }
                    }
                })
        }

        if (query == "steel" || query == "aço"){
            val viewModelDetailTypeSteel: DetailViewModelTypeSteel by viewModels()
            binding.recyclerSearchPokemon.layoutManager = LinearLayoutManager(context)
            viewModelDetailTypeSteel.state.observe(viewLifecycleOwner,
                Observer { state ->
                    binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                    binding.recyclerSearchPokemon.isVisible = state is DetailTypeScreenState.Success

                    when(state){
                        is DetailTypeScreenState.Loading -> {}
                        is DetailTypeScreenState.Success -> {
                            binding.recyclerSearchPokemon.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                        }
                    }
                })
        }

        if(query == "fire" || query == "fogo"){
            val viewModelDetailTypeFire: DetailViewModelTypeFire by viewModels()
            binding.recyclerSearchPokemon.layoutManager = LinearLayoutManager(context)
            viewModelDetailTypeFire.state.observe(viewLifecycleOwner,
                Observer { state ->
                    binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                    binding.recyclerSearchPokemon.isVisible = state is DetailTypeScreenState.Success

                    when(state){
                        is DetailTypeScreenState.Loading -> {}
                        is DetailTypeScreenState.Success -> {
                            binding.recyclerSearchPokemon.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                        }
                    }
                })
        }

        if (query == "water" || query == "água"){
            val viewModelDetailTypeWater: DetailViewModelTypeWater by viewModels()
            binding.recyclerSearchPokemon.layoutManager = LinearLayoutManager(context)
            viewModelDetailTypeWater.state.observe(viewLifecycleOwner,
                Observer { state ->
                    binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                    binding.recyclerSearchPokemon.isVisible = state is DetailTypeScreenState.Success

                    when(state){
                        is DetailTypeScreenState.Loading -> {}
                        is DetailTypeScreenState.Success -> {
                            binding.recyclerSearchPokemon.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                        }
                    }
                })
        }

        if(query == "grass" || query == "planta"){
            val viewModelDetailTypeGrass: DetailViewModelTypeGrass by viewModels()
            binding.recyclerSearchPokemon.layoutManager = LinearLayoutManager(context)
            viewModelDetailTypeGrass.state.observe(viewLifecycleOwner,
                Observer { state ->
                    binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                    binding.recyclerSearchPokemon.isVisible = state is DetailTypeScreenState.Success

                    when(state){
                        is DetailTypeScreenState.Loading -> {}
                        is DetailTypeScreenState.Success -> {
                            binding.recyclerSearchPokemon.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                        }
                    }
                })
        }

        if(query == "electric" || query == "elétrico"){
            val viewModelDetailTypeElectric: DetailViewModelTypeElectric by viewModels()
            binding.recyclerSearchPokemon.layoutManager = LinearLayoutManager(context)
            viewModelDetailTypeElectric.state.observe(viewLifecycleOwner,
                Observer { state ->
                    binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                    binding.recyclerSearchPokemon.isVisible = state is DetailTypeScreenState.Success

                    when(state){
                        is DetailTypeScreenState.Loading -> {}
                        is DetailTypeScreenState.Success -> {
                            binding.recyclerSearchPokemon.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                        }
                    }
                })
        }

        if(query == "psychic" || query == "psíquico"){
            val viewModelDetailTypePsychic: DetailViewModelTypePsychic by viewModels()
            binding.recyclerSearchPokemon.layoutManager = LinearLayoutManager(context)
            viewModelDetailTypePsychic.state.observe(viewLifecycleOwner,
                Observer { state ->
                    binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                    binding.recyclerSearchPokemon.isVisible = state is DetailTypeScreenState.Success

                    when(state){
                        is DetailTypeScreenState.Loading -> {}
                        is DetailTypeScreenState.Success -> {
                            binding.recyclerSearchPokemon.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                        }
                    }
                })
        }

        if(query == "ice" || query == "gelo"){
            val viewModelDetailTypeIce: DetailViewModelTypeIce by viewModels()
            binding.recyclerSearchPokemon.layoutManager = LinearLayoutManager(context)
            viewModelDetailTypeIce.state.observe(viewLifecycleOwner,
                Observer { state ->
                    binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                    binding.recyclerSearchPokemon.isVisible = state is DetailTypeScreenState.Success

                    when(state){
                        is DetailTypeScreenState.Loading -> {}
                        is DetailTypeScreenState.Success -> {
                            binding.recyclerSearchPokemon.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                        }
                    }
                })
        }

        if(query == "dragon" || query == "dragão"){
            val viewModelDetailTypeDragon: DetailViewModelTypeDragon by viewModels()
            binding.recyclerSearchPokemon.layoutManager = LinearLayoutManager(context)
            viewModelDetailTypeDragon.state.observe(viewLifecycleOwner,
                Observer { state ->
                    binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                    binding.recyclerSearchPokemon.isVisible = state is DetailTypeScreenState.Success

                    when(state){
                        is DetailTypeScreenState.Loading -> {}
                        is DetailTypeScreenState.Success -> {
                            binding.recyclerSearchPokemon.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                        }
                    }
                })
        }

        if (query == "dark" || query == "sombrio"){
            val viewModelDetailTypeDark: DetailViewModelTypeDark by viewModels()
            binding.recyclerSearchPokemon.layoutManager = LinearLayoutManager(context)
            viewModelDetailTypeDark.state.observe(viewLifecycleOwner,
                Observer { state ->
                    binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                    binding.recyclerSearchPokemon.isVisible = state is DetailTypeScreenState.Success

                    when(state){
                        is DetailTypeScreenState.Loading -> {}
                        is DetailTypeScreenState.Success -> {
                            binding.recyclerSearchPokemon.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                        }
                    }
                })
        }
        if(query == "fairy" || query == "fada"){
            val viewModelDetailTypeFary: DetailViewModelTypeFairy by viewModels()
            binding.recyclerSearchPokemon.layoutManager = LinearLayoutManager(context)
            viewModelDetailTypeFary.state.observe(viewLifecycleOwner,
                Observer { state ->
                    binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                    binding.recyclerSearchPokemon.isVisible = state is DetailTypeScreenState.Success

                    when(state){
                        is DetailTypeScreenState.Loading -> {}
                        is DetailTypeScreenState.Success -> {
                            binding.recyclerSearchPokemon.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                        }
                    }
                })
        }
    }

}