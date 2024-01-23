package com.douglas2990.pokedexmyapplication2990.fragments.region

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.adapter.PokemonAdapter
import com.douglas2990.pokedexmyapplication2990.databinding.FragmentFirstBinding
import com.douglas2990.pokedexmyapplication2990.screenState.FirstScreenState
import com.douglas2990.pokedexmyapplication2990.search.SearchPokemonViewModel
import com.douglas2990.pokedexmyapplication2990.viewModel.filtroRegions.PokemonHoennViewModel


class HoennDexFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val viewModel: PokemonHoennViewModel by viewModels()
    private val viewModelSearchView: SearchPokemonViewModel by viewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val firstListener = object : PokemonAdapter.PokemonInterface{
        override fun onClick(pokemonId: String){
            findNavController().navigate(R.id.action_HoennFragment_to_SecondFragment, bundleOf("id" to pokemonId))
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
                    is FirstScreenState.Success ->
                        binding.recyclerFirst.adapter = PokemonAdapter(state.data ,firstListener)
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

        return inflater.inflate(R.menu.menu_for_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.app_bar_search_pokemon -> {
                val searchView = item.actionView as SearchView
                binding.recyclerFirst.layoutManager = LinearLayoutManager(context)
                binding.recyclerFirst.setHasFixedSize(true)

                viewModel.state.observe(viewLifecycleOwner,
                     { state ->
                        binding.firstProgress.isVisible = state is FirstScreenState.Loading
                        binding.recyclerFirst.isVisible = state is FirstScreenState.Success

                        when(state) {

                            is FirstScreenState.Loading -> {}
                            is FirstScreenState.Success -> {
                                val listaPokemon = state.data
                                viewModelSearchView.listaQuery = listaPokemon
                                val listaPokemonBusca = viewModelSearchView.listaQueryBusca

                                searchView.setOnQueryTextListener(object :
                                    SearchView.OnQueryTextListener {

                                    override fun onQueryTextSubmit(query: String?): Boolean {
                                        searchView.clearFocus()

                                        viewModelSearchView.pesquisarQuery(query?.lowercase())

                                        binding.recyclerFirst.adapter = PokemonAdapter( listaPokemonBusca,firstListener)

                                        return true
                                    }

                                    override fun onQueryTextChange(newText: String?): Boolean {

                                        viewModelSearchView.pesquisarQuery(newText?.lowercase())

                                        binding.recyclerFirst.adapter = PokemonAdapter(listaPokemonBusca, firstListener)


                                        return true
                                    }
                                })
                            }
                            is FirstScreenState.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG).show()
                        }
                    }
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}