package com.douglas2990.pokedexmyapplication2990.moves

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.adapter.DetailMoveAdapter
import com.douglas2990.pokedexmyapplication2990.databinding.DetailAuxiliarFragmentBinding


class DetailMoveFragment : Fragment() {
    private var _binding: DetailAuxiliarFragmentBinding? = null

    private val viewModelDetailMove by viewModels<DetailMoveViewModel> {
        DetailMoveFactory(arguments?.getString("id", "") ?: "")
    }

    private val binding get() = _binding!!

    private val firstListener = object : DetailMoveAdapter.PokemonInterface{
        override fun onClick(pokemonId: String){
            findNavController().navigate(R.id.DetailMovesFragmentToSecondFragment, bundleOf("id" to pokemonId))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailAuxiliarFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerFirst.layoutManager = LinearLayoutManager(context)

        binding.fabHome.setOnClickListener{
            findNavController().navigate(R.id.action_detailMovesFragment_to_first_fragment)
        }

        viewModelDetailMove.state.observe(viewLifecycleOwner,
            { state ->
                binding.firstProgress.isVisible = state is DetailMoveScreenState.Loading
                binding.recyclerFirst.isVisible = state is DetailMoveScreenState.Success

            when(state){
                is DetailMoveScreenState.Loading -> {}
                is DetailMoveScreenState.Success -> {
                    binding.recyclerFirst.adapter = DetailMoveAdapter(state.data.learned_by_pokemon, firstListener )
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}