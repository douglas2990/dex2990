package com.douglas2990.pokedexmyapplication2990.type.detailType

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
import com.douglas2990.pokedexmyapplication2990.adapter.MovesForTypeAdapter
import com.douglas2990.pokedexmyapplication2990.databinding.FragmentTypeBinding

class MoveForTypesFragment: Fragment() {

    private var _binding: FragmentTypeBinding? = null

    private val viewModelDetailType by viewModels<DetailTypeViewModel> {
        DetailTypeFactory(arguments?.getString("id", "") ?: "")
    }

    private val binding get() = _binding!!

    private val typeListenner = object : MovesForTypeAdapter.MovesForTypeToInterface{
        override fun onClick(typeId: String) {

            findNavController().navigate(R.id.Moves_for_Fragment_to_DetailMoveFragment, bundleOf("id" to typeId))
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
            findNavController().navigate(R.id.action_moves_for_typeFragment_to_first_fragment)
        }

        binding.recyclerViewType.layoutManager = LinearLayoutManager(context)
        viewModelDetailType.state.observe(viewLifecycleOwner,
            { state ->
                binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                binding.recyclerViewType.isVisible = state is DetailTypeScreenState.Success

                when(state) {
                    is DetailTypeScreenState.Loading -> {}
                    is DetailTypeScreenState.Success -> {
                        binding.recyclerViewType.adapter = MovesForTypeAdapter(state.data.moves, typeListenner)
                    }
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}