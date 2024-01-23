package com.douglas2990.pokedexmyapplication2990

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglas2990.pokedexmyapplication2990.adapter.PokemonAdapter
import com.douglas2990.pokedexmyapplication2990.adapter.SearchPokemonAdapter
import com.douglas2990.pokedexmyapplication2990.databinding.FragmentFirstBinding
import com.douglas2990.pokedexmyapplication2990.screenState.FirstScreenState
import com.douglas2990.pokedexmyapplication2990.search.SearchPokemonScreenState
import com.douglas2990.pokedexmyapplication2990.search.SearchPokemonViewModel
import com.douglas2990.pokedexmyapplication2990.viewModel.PokemonViewModel as PokemonViewModel


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment() : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel: PokemonViewModel by viewModels()
    private val viewModelSearchView: SearchPokemonViewModel by viewModels()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding: FragmentFirstBinding get() = _binding!!

    private val firstListener = object : PokemonAdapter.PokemonInterface{
        override fun onClick(pokemonId: String){
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundleOf("id" to pokemonId))

            //findNavController().navigate(R.id.action_FistFragmentToVarietiesFragment, bundleOf("id" to pokemonId))
        }
    }


    private val searchListener = object : SearchPokemonAdapter.PokemonSearchInterface{
        override fun onClick(pokemonId: String){
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundleOf("id" to pokemonId))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerFirst.layoutManager = LinearLayoutManager(context)

        viewModel.state.observe(viewLifecycleOwner,
                 { state ->
                    binding.firstProgress.isVisible = state is FirstScreenState.Loading
                    binding.recyclerFirst.isVisible = state is FirstScreenState.Success

                    when (state){
                        is FirstScreenState.Loading -> {}
                        is FirstScreenState.Success -> {
                            binding.recyclerFirst.adapter = PokemonAdapter(state.data, firstListener)
                        }
                        is FirstScreenState.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG).show()
                    }

                })

        setHasOptionsMenu(true)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        return inflater.inflate(R.menu.menu_filtro_regions, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.dex_kanto -> {
                loadKanto()
                true
            }
            R.id.dex_johto -> {
                loadJohto()
                true
            }
            R.id.dex_hoenn -> {
                loadHoenn()
                true
            }
            R.id.dex_sinnoh -> {
                findNavController().navigate(R.id.action_FirstFragment_to_DexSinnohFragment)
                true
            }
            R.id.dex_unova -> {
                findNavController().navigate(R.id.action_FirstFragment_to_UnovaFragment)
                true
            }
            R.id.dex_kalos -> {
                findNavController().navigate(R.id.action_FirstFragment_to_DexKalosFragment)
                true
            }
            R.id.dex_alola -> {
                findNavController().navigate(R.id.action_FirstFragment_to_DexAlolaFragment)
                true
            }
            R.id.dex_galar -> {
                findNavController().navigate(R.id.action_FirstFragment_to_DexGalarFragment)
                true
            }
            R.id.mega_evolucao -> {
                findNavController().navigate(R.id.action_FirstFragment_to_MegaEvolucaoFragment)
                true
            }
            R.id.firstMenu -> {
                findNavController().navigate(R.id.action_FirstFragmentToFirstMenuFragment)
                true
            }

            R.id.search_pokemon_menu -> {
                findNavController().navigate(R.id.action_FirstFragment_to_SearchPokemonFragment)

                true
            }

            R.id.app_bar_search_pokemon -> {

                val searchView = item.actionView as android.widget.SearchView

                viewModelSearchView.state.observe(viewLifecycleOwner,
                     { state ->
                        binding.firstProgress.isVisible = state is SearchPokemonScreenState.Loading
                        binding.recyclerFirst.isVisible = state is SearchPokemonScreenState.Success

                        when(state) {

                            is SearchPokemonScreenState.Loading -> {}
                            is SearchPokemonScreenState.Success -> {

                                viewModelSearchView.listaQuery = state.data
                                val listaPokemonBusca = viewModelSearchView.listaQueryBusca
                                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                                    android.widget.SearchView.OnQueryTextListener {

                                    override fun onQueryTextSubmit(query: String?): Boolean {
                                        searchView.clearFocus()

                                        viewModelSearchView.pesquisarQuery(query?.lowercase())

                                        binding.recyclerFirst.adapter = PokemonAdapter( listaPokemonBusca,firstListener)

                                        return false

                                    }

                                    override fun onQueryTextChange(newText: String?): Boolean {
                                        viewModelSearchView.pesquisarQuery(newText?.lowercase())

                                        binding.recyclerFirst.adapter = PokemonAdapter( listaPokemonBusca, firstListener)

                                        return false
                                    }

                                })

                            }

                            is SearchPokemonScreenState.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG).show()
                        }

                    }
                )

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadKanto(){
        findNavController().navigate(R.id.action_FirstFragment_to_DexKantoFragment)
    }

    private fun loadJohto() {
        findNavController().navigate(R.id.action_FirstFragment_to_DexJohtoFragment)
    }

    private fun loadHoenn() {
        findNavController().navigate(R.id.action_FirstFragment_to_DexHoennFragment)
    }

  }