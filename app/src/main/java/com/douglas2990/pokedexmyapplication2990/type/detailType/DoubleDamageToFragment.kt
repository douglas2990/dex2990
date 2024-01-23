package com.douglas2990.pokedexmyapplication2990.type.detailType

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.adapter.DoubleDamageToAdapter
import com.douglas2990.pokedexmyapplication2990.databinding.FragmentTypeBinding

class DoubleDamageToFragment : Fragment() {

    private var _binding: FragmentTypeBinding? = null

    private val viewModelDetailType by viewModels<DetailTypeViewModel>{
        DetailTypeFactory(arguments?.getString("id", "") ?: "")
    }

    private val binding get() = _binding!!

    private val typeListenner = object : DoubleDamageToAdapter.DoubleDamageToInterface{
        override fun onClick(typeId: String) {
            findNavController().navigate(R.id.double_damage_ToFragment_to_DetailTypeFragment, bundleOf("id" to typeId))
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
            findNavController().navigate(R.id.action_doubledamageToFragment_to_first_fragment)
        }

        binding.recyclerViewType.layoutManager = LinearLayoutManager(context)
        viewModelDetailType.state.observe(viewLifecycleOwner,
            { state ->
                 binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                 binding.recyclerViewType.isVisible = state is DetailTypeScreenState.Success

                 when(state) {
                     is DetailTypeScreenState.Loading -> {}
                     is DetailTypeScreenState.Success -> {
                         binding.recyclerViewType.adapter = DoubleDamageToAdapter(state.data.damage_relations.double_damage_to, typeListenner)
                     }
                 }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}