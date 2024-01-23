package com.douglas2990.pokedexmyapplication2990.moves

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.adapter.AllMovesAdapter
import com.douglas2990.pokedexmyapplication2990.databinding.FragmentMoveBinding

class MovesFragment : Fragment()  {

    private var _binding: FragmentMoveBinding? = null
    private val viewModelMoves: MovesViewModel by viewModels()

    private val binding get() = _binding!!

    private val firstListener = object : AllMovesAdapter.MoveInterface{
        override fun onClick(moveId: String) {
            findNavController().navigate(R.id.MoveFragmentToDetailMovesFragment, bundleOf("id" to moveId))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewMove.layoutManager = LinearLayoutManager(context)
        viewModelMoves.state.observe(viewLifecycleOwner,
                         { state ->
                            binding.firstProgress.isVisible = state is MovesScreenState.Loading
                            binding.recyclerViewMove.isVisible = state is MovesScreenState.Success

                            when (state) {
                                is MovesScreenState.Loading -> {}
                                is MovesScreenState.Success ->
                                    binding.recyclerViewMove.adapter = AllMovesAdapter(state.data, firstListener)
                                is MovesScreenState.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_SHORT).show()
                            }
                        })
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        return inflater.inflate(R.menu.menu_for_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.app_bar_search_pokemon -> {

                val searchView = item.actionView as androidx.appcompat.widget.SearchView
                binding.recyclerViewMove.layoutManager = LinearLayoutManager(context)
                binding.recyclerViewMove.setHasFixedSize(true)

                viewModelMoves.state.observe(viewLifecycleOwner,
                     { state ->
                        binding.firstProgress.isVisible = state is MovesScreenState.Loading
                        binding.recyclerViewMove.isVisible = state is MovesScreenState.Success

                        when(state) {

                            is MovesScreenState.Loading -> {}
                            is MovesScreenState.Success -> {
                                var listaPokemon = state.data
                                viewModelMoves.listaQuery = listaPokemon
                                val listaPokemonBusca = viewModelMoves.listaQueryBusca

                                searchView.setOnQueryTextListener(object :
                                    androidx.appcompat.widget.SearchView.OnQueryTextListener {

                                    override fun onQueryTextSubmit(query: String?): Boolean {
                                        searchView.clearFocus()

                                        viewModelMoves.pesquisarQuery(query?.lowercase())

                                        binding.recyclerViewMove.adapter = AllMovesAdapter( listaPokemonBusca, firstListener)

                                        return true
                                    }

                                    override fun onQueryTextChange(newText: String?): Boolean {

                                        viewModelMoves.pesquisarQuery(newText?.lowercase())

                                        binding.recyclerViewMove.adapter = AllMovesAdapter(listaPokemonBusca, firstListener)

                                        return true
                                    }
                                })
                            }
                            is MovesScreenState.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG).show()
                        }
                    }
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}