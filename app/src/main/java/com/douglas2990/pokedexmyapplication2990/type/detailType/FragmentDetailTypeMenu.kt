package com.douglas2990.pokedexmyapplication2990.type.detailType

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.databinding.FragmentMenuDetailTypeBinding

class FragmentDetailTypeMenu : Fragment() {

    private var idType = ""

    private var _binding: FragmentMenuDetailTypeBinding? = null

    private val viewModelDetailType by viewModels<DetailTypeViewModel>{
        DetailTypeFactory(arguments?.getString("id","") ?: "")
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuDetailTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabHome.setOnClickListener{
            findNavController().navigate(R.id.action_detailMenuFragment_to_first_fragment)
        }

        viewModelDetailType.state.observe(viewLifecycleOwner,
             { state ->

                when(state){
                    is DetailTypeScreenState.Loading -> {}
                    is DetailTypeScreenState.Success -> {

                        idType = state.data.id.toString()
                        binding.buttonClickDoubleDamageFrom.setOnClickListener {
                            iniciarDoubleFromDamage(arguments?.getString("id","") ?: "")
                        }
                        binding.buttonClickDoubleDamageTo.setOnClickListener {
                            iniciarDoubleToDamage(arguments?.getString("id","") ?: "")
                        }
                        binding.buttonClickHalfDamageFrom.setOnClickListener {
                            iniciarHalfFromDamage(arguments?.getString("id","") ?: "")
                        }
                        binding.buttonClickHalfDamageTo.setOnClickListener {
                            iniciarHalfToDamage(arguments?.getString("id","") ?: "")
                        }
                        binding.buttonClickNoDamageFrom.setOnClickListener {
                            iniciarNoFromDamage(arguments?.getString("id","") ?: "")
                        }
                        binding.buttonClickNoDamageTo.setOnClickListener {
                            iniciarNoDamageTo(arguments?.getString("id","") ?: "")
                        }

                        binding.buttonClickMoveForTypes.setOnClickListener {
                            iniciarMovesForTypes(arguments?.getString("id", "") ?: "")
                        }
                        binding.buttonClickPokemon.setOnClickListener {
                            iniciarPokemonThisType(arguments?.getString("id", "") ?: "")
                        }

                    }
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun iniciarDoubleFromDamage(typeId: String){
        findNavController().navigate(R.id.actionfragmentDetailMenuToDoubleDamageFromFragment, bundleOf("id" to typeId))
    }

    fun iniciarDoubleToDamage(typeId: String){
        findNavController().navigate(R.id.actionfragmentDetailMenuToDoubleDamageToFragment, bundleOf("id" to typeId))
    }

    fun iniciarHalfFromDamage(typeId: String){
        findNavController().navigate(R.id.actionfragmentDetailMenuToHalfDamageFromFragment, bundleOf("id" to typeId))
    }

    fun iniciarHalfToDamage(typeId: String){
        findNavController().navigate(R.id.actionfragmentDetailMenuToHalfDamageToFragment, bundleOf("id" to typeId))
    }

    fun iniciarNoFromDamage(typeId: String){
        findNavController().navigate(R.id.actionfragmentDetailMenuToNoDamageFromFragment, bundleOf("id" to typeId))
    }

    fun iniciarNoDamageTo(typeId: String){
        findNavController().navigate(R.id.actionfragmentDetailMenuToNoDamageToFragment, bundleOf("id" to typeId))
    }

    fun iniciarMovesForTypes(typeId: String){
        findNavController().navigate(R.id.actionfragmentDetailMenuToMovesForTypeFragment, bundleOf("id" to typeId))
    }

    fun iniciarPokemonThisType(typeId: String){
        findNavController().navigate(R.id.actionfragmentDetailMenuToDetailTypeFragment, bundleOf("id" to typeId))
    }
}