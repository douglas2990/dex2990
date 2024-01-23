package com.douglas2990.pokedexmyapplication2990.fragments.megaevolucao

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
import com.douglas2990.pokedexmyapplication2990.adapter.PokemonAdapter
import com.douglas2990.pokedexmyapplication2990.databinding.FragmentFirstBinding
import com.douglas2990.pokedexmyapplication2990.screenState.FirstScreenState
import com.douglas2990.pokedexmyapplication2990.viewModel.megaEvolucao.MegaEvolucaoViewModel

class MegaEvolucaoFragment: Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel: MegaEvolucaoViewModel by viewModels()

    private val binding get() = _binding!!

    private val firstListener = object : PokemonAdapter.PokemonInterface{
        override fun onClick(pokemonId: String) {
            findNavController().navigate(
                R.id.action_MegaEvolucaoFragment_to_SecondFragment, bundleOf("id" to pokemonId)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerFirst.layoutManager = LinearLayoutManager(context)
        viewModel.state.observe(viewLifecycleOwner,
            Observer { state ->
                binding.firstProgress.isVisible = state is FirstScreenState.Loading
                binding.recyclerFirst.isVisible = state is FirstScreenState.Success

                when(state){
                    is FirstScreenState.Loading -> {}
                    is FirstScreenState.Success ->
                        binding.recyclerFirst.adapter = PokemonAdapter(state.data, firstListener)
                    is FirstScreenState.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG).show()
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
}