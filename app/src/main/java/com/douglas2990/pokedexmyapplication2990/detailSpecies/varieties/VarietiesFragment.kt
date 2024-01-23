package com.douglas2990.pokedexmyapplication2990.detailSpecies.varieties

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.adapter.VarietiesAdapter
import com.douglas2990.pokedexmyapplication2990.databinding.PokemonVarietiesFragmentBinding


class VarietiesFragment : Fragment() {

    private var _binding: PokemonVarietiesFragmentBinding? = null

    private val firstListener = object : VarietiesAdapter.VarietiesInterface{
        override fun onClick(pokemonId: String){
            findNavController().navigate(R.id.actionVarietiesToSecondFragment, bundleOf("id" to pokemonId))
        }
    }

    private val viewModelSpecies by viewModels<VarietiesViewModel> {
        VarietiesFactory(arguments?.getString("id","") ?: "")
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PokemonVarietiesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelSpecies.state.observe(viewLifecycleOwner,
            Observer { state ->
                binding.recyclerViewVarieties.layoutManager = LinearLayoutManager(context)

                when(state){
                    is VarietiesScreenState.Loading -> {}
                    is VarietiesScreenState.Success ->
                        binding.recyclerViewVarieties.adapter = VarietiesAdapter(state.data.varieties, firstListener)
                }

            })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}