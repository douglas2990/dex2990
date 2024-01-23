package com.douglas2990.pokedexmyapplication2990.type

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
import com.douglas2990.pokedexmyapplication2990.adapter.AllTypesAdapter
import com.douglas2990.pokedexmyapplication2990.databinding.FragmentTypeBinding

class TypeFragment : Fragment()  {

    private var _binding: FragmentTypeBinding? = null
    private val viewModelType: TypeViewModel by viewModels()

    private val binding get() = _binding!!

    private val firstListenner = object: AllTypesAdapter.TypeInterface{
        override fun onClick(typeId: String) {
            findNavController().navigate(R.id.TypeFragmentToDetailTypeFragment, bundleOf("id" to typeId))

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabHome.setOnClickListener{
            findNavController().navigate(R.id.action_typeFragment_to_first_fragment)
        }

        binding.recyclerViewType.layoutManager = LinearLayoutManager(context)
        viewModelType.state.observe(viewLifecycleOwner,
            { state ->
                            binding.firstProgress.isVisible = state is TypeScreenState.Loading
                            binding.recyclerViewType.isVisible = state is TypeScreenState.Success

                            when (state) {
                                is TypeScreenState.Loading -> {}
                                is TypeScreenState.Success ->
                                    binding.recyclerViewType.adapter = AllTypesAdapter(state.data, firstListenner)
                                is TypeScreenState.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG).show()
                            }
                        })

        setHasOptionsMenu(true)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        return inflater.inflate(R.menu.menu_for_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.app_bar_search_pokemon -> {

                val searchView = item.actionView as androidx.appcompat.widget.SearchView
                binding.recyclerViewType.layoutManager = LinearLayoutManager(context)
                binding.recyclerViewType.setHasFixedSize(true)

                viewModelType.state.observe(viewLifecycleOwner,
                     { state ->
                        binding.firstProgress.isVisible = state is TypeScreenState.Loading
                        binding.recyclerViewType.isVisible = state is TypeScreenState.Success

                        when(state) {

                            is TypeScreenState.Loading -> {}
                            is TypeScreenState.Success -> {
                                val listaPokemon = state.data
                                viewModelType.listaQuery = listaPokemon
                                val listaPokemonBusca = viewModelType.listaQueryBusca

                                searchView.setOnQueryTextListener(object :
                                    androidx.appcompat.widget.SearchView.OnQueryTextListener {

                                    override fun onQueryTextSubmit(query: String?): Boolean {
                                        searchView.clearFocus()

                                        viewModelType.pesquisarQuery(query?.lowercase())

                                        binding.recyclerViewType.adapter = AllTypesAdapter( listaPokemonBusca, firstListenner)

                                        return true
                                    }

                                    override fun onQueryTextChange(newText: String?): Boolean {

                                        viewModelType.pesquisarQuery(newText?.lowercase())

                                        binding.recyclerViewType.adapter = AllTypesAdapter(listaPokemonBusca, firstListenner)

                                        return true
                                    }
                                })
                            }
                            is TypeScreenState.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG).show()
                        }
                    }
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }




}