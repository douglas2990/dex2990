package com.douglas2990.pokedexmyapplication2990.evolution

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.databinding.FragmentEvolutionBinding
import com.douglas2990.pokedexmyapplication2990.detailSpecies.DetailPokemonSpeciesFactory
import com.douglas2990.pokedexmyapplication2990.detailSpecies.DetailPokemonSpeciesViewModel
import com.douglas2990.pokedexmyapplication2990.detailSpecies.DetailScreenStateSpecies

class DetailPokemonEvolutionFragment : Fragment(){

    private var _binding: FragmentEvolutionBinding? = null

    private val binding get() = _binding!!


    private val viewModel by viewModels<DetailPokemonEvolutionViewModel> {
        DetailPokemonEvolutionFactory(arguments?.getString("id", "") ?: "")
    }

    private val viewModelSpecies by viewModels<DetailPokemonSpeciesViewModel>{
        DetailPokemonSpeciesFactory(arguments?.getString("id","") ?: "")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEvolutionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, { state ->
            presentLoading(state is DetailScreenStateEvolution.Loading)

            when(state){
                is DetailScreenStateEvolution.Loading -> {}
                is DetailScreenStateEvolution.Success -> {

                    try {
                        var idPokemonSpeciesEvolucao1 = state.data.chain.species.url

                        var idPokemonReplaceEvolucao1 = idPokemonSpeciesEvolucao1.replace("https://pokeapi.co/api/v2/pokemon-species/", "")
                            .replace("/", "")

                        binding.txtNamePokemonEvolution1.text = state.data.chain.species.name.capitalize()
                        binding.imgEvolution1.setImageURI("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + idPokemonReplaceEvolucao1 + ".png")
                        binding.txtComoEvolui1.text = state.data.chain.evolves_to[0].evolution_details[0].trigger.name
                        binding.txtComoEvolui2.text = state.data.chain.evolves_to[0].evolves_to[0].evolution_details[0].trigger.name
                        binding.imgSetaEvolui1.setImageResource(R.drawable.seta724808)
                        binding.imgSetaEvolui2.setImageResource(R.drawable.seta724808)
                        var switchEvolucao1 = ""


                    if (state.data.chain.evolves_to[0].evolution_details[0].gender != null){
                        switchEvolucao1 = state.data.chain.evolves_to[0].evolution_details[0].gender.toString()
                        binding.txtMetodoEvolui1.text = switchEvolucao1
                    }
                    if (state.data.chain.evolves_to[0].evolution_details[0].held_item != null){
                        switchEvolucao1 = state.data.chain.evolves_to[0].evolution_details[0].held_item.toString()
                        binding.txtMetodoEvolui1.text = switchEvolucao1
                    }
                    if (state.data.chain.evolves_to[0].evolution_details[0].item != null){
                        switchEvolucao1 = state.data.chain.evolves_to[0].evolution_details[0].item.toString()
                        binding.txtMetodoEvolui1.text = switchEvolucao1
                    }
                    if (state.data.chain.evolves_to[0].evolution_details[0].known_move != null){
                        switchEvolucao1 = state.data.chain.evolves_to[0].evolution_details[0].known_move.toString()
                        binding.txtMetodoEvolui1.text = switchEvolucao1
                    }
                    if(state.data.chain.evolves_to[0].evolution_details[0].known_move_type != null){
                        switchEvolucao1 = state.data.chain.evolves_to[0].evolution_details[0].known_move_type.toString()
                        binding.txtMetodoEvolui1.text = switchEvolucao1
                    }
                    if(state.data.chain.evolves_to[0].evolution_details[0].location != null) {
                        switchEvolucao1 = state.data.chain.evolves_to[0].evolution_details[0].location.toString()
                        binding.txtMetodoEvolui1.text = switchEvolucao1
                    }
                    if(state.data.chain.evolves_to[0].evolution_details[0].min_affection != null){
                        switchEvolucao1 = state.data.chain.evolves_to[0].evolution_details[0].min_affection.toString()
                        binding.txtMetodoEvolui1.text = switchEvolucao1
                    }
                    if (state.data.chain.evolves_to[0].evolution_details[0].min_beauty != null){
                        switchEvolucao1 = state.data.chain.evolves_to[0].evolution_details[0].min_beauty.toString()
                        binding.txtMetodoEvolui1.text = switchEvolucao1
                    }
                    if(state.data.chain.evolves_to[0].evolution_details[0].min_happiness != null){
                        switchEvolucao1 = state.data.chain.evolves_to[0].evolution_details[0].min_happiness.toString()
                        binding.txtMetodoEvolui1.text = switchEvolucao1
                    }
                    if (state.data.chain.evolves_to[0].evolution_details[0].min_level != null){
                        switchEvolucao1 = state.data.chain.evolves_to[0].evolution_details[0].min_level.toString()
                        binding.txtMetodoEvolui1.text = switchEvolucao1
                    }
                    if (state.data.chain.evolves_to[0].evolution_details[0].needs_overworld_rain != null){
                        switchEvolucao1 = state.data.chain.evolves_to[0].evolution_details[0].needs_overworld_rain.toString()
                        binding.txtMetodoEvolui1.text = switchEvolucao1
                    }
                    if (state.data.chain.evolves_to[0].evolution_details[0].party_species != null){
                        switchEvolucao1 = state.data.chain.evolves_to[0].evolution_details[0].party_species.toString()
                        binding.txtMetodoEvolui1.text = switchEvolucao1
                    }
                    if (state.data.chain.evolves_to[0].evolution_details[0].party_type != null){
                        switchEvolucao1 = state.data.chain.evolves_to[0].evolution_details[0].party_type.toString()
                        binding.txtMetodoEvolui1.text = switchEvolucao1
                    }
                    if (state.data.chain.evolves_to[0].evolution_details[0].relative_physical_stats != null){
                        switchEvolucao1 = state.data.chain.evolves_to[0].evolution_details[0].relative_physical_stats.toString()
                        binding.txtMetodoEvolui1.text = switchEvolucao1
                    }
                    if (state.data.chain.evolves_to[0].evolution_details[0].time_of_day != null){
                        switchEvolucao1 = state.data.chain.evolves_to[0].evolution_details[0].time_of_day.toString()
                        binding.txtMetodoEvolui1.text = switchEvolucao1
                    }
                    if (state.data.chain.evolves_to[0].evolution_details[0].trade_species != null){
                        switchEvolucao1 = state.data.chain.evolves_to[0].evolution_details[0].trade_species.toString()
                        binding.txtMetodoEvolui1.text = switchEvolucao1
                    }

                    }catch (e: Exception){
                        binding.txtNamePokemonEvolution1.text = state.data.chain.species.name.capitalize()

                    }

                    try {
                        var idPokemonSpeciesEvolucao2 = state.data.chain.evolves_to[0].species.url
                        binding.txtNamePokemonEvolution2.text = state.data.chain.evolves_to[0].species.name.capitalize()
                        var idPokemonReplaceEvolucao2 = idPokemonSpeciesEvolucao2.replace("https://pokeapi.co/api/v2/pokemon-species/", "")
                            .replace("/", "")
                        binding.imgEvolution2.setImageURI("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + idPokemonReplaceEvolucao2 + ".png")
                        binding.txtComoEvolui2.text = state.data.chain.evolves_to[0].evolves_to[0].evolution_details[0].trigger.name

                    }catch (e: Exception){
                        binding.txtNamePokemonEvolution2.text = ""
                    }

                    try {
                        var idPokemonSpeciesEvolucao3 = state.data.chain.evolves_to[0].evolves_to[0].species.url
                        binding.txtNamePokemonEvolution3.text = state.data.chain.evolves_to[0].evolves_to[0].species.name.capitalize()
                        var idPokemonReplaceEvolucao3 = idPokemonSpeciesEvolucao3.replace("https://pokeapi.co/api/v2/pokemon-species/", "")
                            .replace("/", "")
                        binding.imgEvolution3.setImageURI("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + idPokemonReplaceEvolucao3 + ".png")

                    }catch (e:Exception){
                        binding.txtNamePokemonEvolution3.text = ""
                    }
                }
            }
        })

        viewModelSpecies.state.observe(viewLifecycleOwner, { state ->
            presentLoading(state is DetailScreenStateSpecies.Loading)

            when(state){
                is DetailScreenStateSpecies.Loading -> {}
                is DetailScreenStateSpecies.Success -> {

                    binding.scrolViewFraagmentEvolution.setBackgroundColor(state.data.color.url.hashCode())

                }

                is DetailScreenStateSpecies.Error -> {
                    Toast.makeText(context, state.messageId, Toast.LENGTH_SHORT).show()
                }
            }

        }

        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun presentLoading(show: Boolean){
        if (show){
            binding.detailsShimmerEvolutionPokemon1.showShimmer(true)
            binding.detailsShimmerEvolutionPokemonImage1.showShimmer(true)
            binding.detailsShimmerEvolutionPokemon2.showShimmer(true)
            binding.detailsShimmerEvolutionPokemonImage2.showShimmer(true)
            binding.detailsShimmerEvolutionPokemon3.showShimmer(true)
            binding.detailsShimmerEvolutionPokemonImage3.showShimmer(true)
            binding.detailsShimmerEvolutionPokemonMega.showShimmer(true)
            binding.detailsShimmerEvolutionPokemonImageMega.showShimmer(true)
            binding.detailsShimmerEvolutionComoEvolui1.showShimmer(true)
            binding.detailsShimmerEvolutionComoEvolui2.showShimmer(true)


        }else {
            binding.detailsShimmerEvolutionPokemon1.hideShimmer()
            binding.detailsShimmerEvolutionPokemonImage1.hideShimmer()
            binding.detailsShimmerEvolutionPokemon2.hideShimmer()
            binding.detailsShimmerEvolutionPokemonImage2.hideShimmer()
            binding.detailsShimmerEvolutionPokemon3.hideShimmer()
            binding.detailsShimmerEvolutionPokemonImage3.hideShimmer()
            binding.detailsShimmerEvolutionPokemonMega.hideShimmer()
            binding.detailsShimmerEvolutionPokemonImageMega.hideShimmer()
            binding.detailsShimmerEvolutionComoEvolui1.hideShimmer()
            binding.detailsShimmerEvolutionComoEvolui2.hideShimmer()

        }
    }
}


