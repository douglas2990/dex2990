package com.douglas2990.pokedexmyapplication2990.detailSpecies

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.databinding.FragmentEvolutionBinding
import com.douglas2990.pokedexmyapplication2990.databinding.FragmentPokemonSpeciesBinding
import com.douglas2990.pokedexmyapplication2990.evolution.DetailPokemonEvolutionFactory
import com.douglas2990.pokedexmyapplication2990.evolution.DetailPokemonEvolutionViewModel
import com.douglas2990.pokedexmyapplication2990.evolution.DetailScreenStateEvolution
import com.douglas2990.pokedexmyapplication2990.model.evolution_chain.EvolutionChain

class DetailPokemonSpeciesFragment : Fragment(){

    private var _binding: FragmentPokemonSpeciesBinding? = null

    private val binding get() = _binding!!

    private val viewModelEvolution by viewModels<DetailPokemonEvolutionViewModel> {
        DetailPokemonEvolutionFactory(arguments?.getString("id", "") ?: "")
    }

    private val viewModel by viewModels<DetailPokemonSpeciesViewModel> {
        DetailPokemonSpeciesFactory(arguments?.getString("id", "") ?: "")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonSpeciesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, { state ->
            presentLoading(state is DetailScreenStateSpecies.Loading)

            when(state){
                is DetailScreenStateSpecies.Loading -> {}
                is DetailScreenStateSpecies.Success -> {
                    //binding.detailNamePokemonEvolution.text = state.data.evolution_chain.url
                    if(state.data.evolution_chain != null){
                        binding.txtEvoluiPara.text = state.data.evolution_chain.url
                    } else{
                        binding.txtEvoluiPara.text = ""
                    }
                    if(state.data.evolves_from_species != null) {
                        binding.txtPreEvolucao.text = state.data.evolves_from_species.toString()
                    }else {
                        binding.txtPreEvolucao.text = ""
                    }
                    if(state.data.habitat.name != null) {
                        binding.txtHabitat.text = state.data.habitat.name
                    }else {
                        binding.txtHabitat.text = ""
                    }
                    if(state.data.id != null) {
                        binding.txtIdPokemonSpecies.text = state.data.id.toString()
                    }else{
                        binding.txtIdPokemonSpecies.text = ""
                    }
                    if(state.data.name != null) {
                        binding.txtNamePokemonSpecies.text = state.data.name
                    }else{
                        binding.txtNamePokemonSpecies.text = ""
                    }
                    if(state.data.order != null) {
                        binding.txtOrderPokemonSpecies.text = state.data.order.toString()
                    }else{
                        binding.txtOrderPokemonSpecies.text = ""
                    }

                    var idEvolution = state.data.evolution_chain.url.replace("https://pokeapi.co/api/v2/evolution-chain/", "")
                        .replace("/", "")

                    binding.txtNamePokemonSpecies.setOnClickListener {
                        mostrarEvolution(arguments?.getString(idEvolution ,idEvolution) ?: idEvolution)
                    }



                }
            }
        })

        viewModelEvolution.state.observe(viewLifecycleOwner, { state ->
            presentLoading(state is DetailScreenStateEvolution.Loading)

            when(state){
                is DetailScreenStateEvolution.Loading -> {}
                is DetailScreenStateEvolution.Success -> {
                    binding.txtOrderPokemonSpeciesToff.text = state.data.chain.evolves_to[0].species.name
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun presentLoading(show: Boolean){
        if (show){
            binding.detaisShimmerEvoluiPara.showShimmer(true)
            binding.detaisShimmerEvoluiParaTOFF.showShimmer(true)

            binding.detaisShimmerPreEvolucao.showShimmer(true)
            binding.detaisShimmerPreEvolucaoTOFF.showShimmer(true)

            binding.detailsHabitat.showShimmer(true)
            binding.detailsHabitatTOFF.showShimmer(true)

            binding.detaisShimmerIdPokemonSpecies.showShimmer(true)
            binding.detaisShimmerIdPokemonSpeciesTOFF.showShimmer(true)
            binding.detaisShimmerNamePokemonSpecies.showShimmer(true)
            binding.detaisShimmerNamePokemonSpeciesTOff.showShimmer(true)
            binding.detaisShimmerOrderPokemonSpecies.showShimmer(true)
            binding.detaisShimmerOrderPokemonSpeciesToff.showShimmer(true)

        }else {
            binding.detaisShimmerEvoluiPara.hideShimmer()
            binding.detaisShimmerEvoluiParaTOFF.hideShimmer()
            binding.detaisShimmerPreEvolucao.hideShimmer()
            binding.detaisShimmerPreEvolucaoTOFF.hideShimmer()
            binding.detailsHabitat.hideShimmer()
            binding.detailsHabitatTOFF.hideShimmer()
            binding.detaisShimmerIdPokemonSpecies.hideShimmer()
            binding.detaisShimmerIdPokemonSpeciesTOFF.hideShimmer()
            binding.detaisShimmerNamePokemonSpecies.hideShimmer()
            binding.detaisShimmerNamePokemonSpeciesTOff.hideShimmer()
            binding.detaisShimmerOrderPokemonSpecies.hideShimmer()
            binding.detaisShimmerOrderPokemonSpeciesToff.hideShimmer()
        }
    }

    fun mostrarEvolution(pokemonId: String){
        findNavController().navigate(R.id.DetailPokemonSpeciesFragmentToEvolutionFragment, bundleOf("id" to pokemonId))
    }
}


