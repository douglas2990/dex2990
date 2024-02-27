package com.douglas2990.pokedexmyapplication2990

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.douglas2990.pokedexmyapplication2990.databinding.FragmentSecondBinding
import com.douglas2990.pokedexmyapplication2990.detail.DetailPokemonFactory
import com.douglas2990.pokedexmyapplication2990.detail.DetailPokemonViewModel
import com.douglas2990.pokedexmyapplication2990.detail.DetailScreenState
import com.douglas2990.pokedexmyapplication2990.detailSpecies.DetailPokemonSpeciesFactory
import com.douglas2990.pokedexmyapplication2990.detailSpecies.DetailPokemonSpeciesViewModel
import com.douglas2990.pokedexmyapplication2990.detailSpecies.DetailScreenStateSpecies
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds



/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private lateinit var mAdView: AdView

    // This property is only valid between onCreateView and
    // onDestroyView.
    ////

    private var pokemonShiny: Boolean = false
    private var idEvolutionPokemon = ""
    private var idFirebasePokemon = ""
    private var idColorSpecies = ""

    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailPokemonViewModel>{
       DetailPokemonFactory(arguments?.getString("id","") ?: "")
    }
    private val viewModelSpecies by viewModels<DetailPokemonSpeciesViewModel>{
        DetailPokemonSpeciesFactory(arguments?.getString("id","") ?: "")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       MobileAds.initialize(context){}

       val adView = AdView(context)

       adView.adSize = AdSize.BANNER

       //adView.adUnitId = "ca-app-pub-3xxxxxxxxxx/6xxxxxxxx1"
       adView.adUnitId = "ca-app-pub-95xxxxxxxx~109xxxxxx2"

       mAdView = binding.adView
       val adRequest = AdRequest.Builder().build()
       mAdView.loadAd(adRequest)

        binding.fabHome.setOnClickListener{
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

       viewModel.state.observe(viewLifecycleOwner, { state ->
            presentLoading(state is DetailScreenState.Loading)

            when(state){
                is DetailScreenState.Loading -> {}
                is DetailScreenState.Success -> {
                    binding.detailPokemon.setImageURI(state.data.sprites.other.home.front_default)

                    val namePokemon = state.data.name.capitalize()
                    val numberPokemon = state.data.id.toString().padStart(3,'0')

                    if ( numberPokemon.toInt() < 900) {
                        binding.detailNamePokemon.text = "#$numberPokemon $namePokemon"
                    }else {
                        binding.detailNamePokemon.text = "$namePokemon"
                    }
                    binding.typePokemon1.visibility = View.INVISIBLE
                    binding.typePokemon2.visibility = View.INVISIBLE
                    binding.typePokemon.visibility = View.INVISIBLE

                    idFirebasePokemon = state.data.id.toString()

                    val tamanho_type = state.data.types.size
                    var type1 = state.data.types[0].type.name.capitalize()
                    var type2 = state.data.types[0].type.name.capitalize()

                    if(tamanho_type == 2){
                        type1 = state.data.types[0].type.name.capitalize()
                        type2 = state.data.types[1].type.name.capitalize()
                        binding.typePokemon1.visibility = View.VISIBLE
                        binding.typePokemon2.visibility = View.VISIBLE
                        binding.typePokemon.visibility = View.INVISIBLE
                        binding.typePokemon1.text = type1
                        binding.typePokemon2.text = type2

                        binding.typePokemon1.setOnClickListener {
                            val idType = state.data.types[0].type.url.replace("https://pokeapi.co/api/v2/type/", "")
                                .replace("/", "")
                            mostrarTypes(arguments?.getString(idType,idType) ?: idType)
                        }

                        binding.typePokemon2.setOnClickListener {
                            val idType = state.data.types[1].type.url.replace("https://pokeapi.co/api/v2/type/", "")
                                .replace("/", "")
                            mostrarTypes(arguments?.getString(idType,idType) ?: idType)
                        }

                    }else{
                        type1 = state.data.types[0].type.name.capitalize()
                        binding.typePokemon1.visibility = View.INVISIBLE
                        binding.typePokemon2.visibility = View.INVISIBLE
                        binding.typePokemon.visibility = View.VISIBLE
                        binding.typePokemon1.text = ""
                        binding.typePokemon.text = type1
                        binding.typePokemon2.text = ""

                        binding.typePokemon.setOnClickListener {
                            val idType = state.data.types[0].type.url.replace("https://pokeapi.co/api/v2/type/", "")
                                .replace("/", "")
                            mostrarTypes(arguments?.getString(idType,idType) ?: idType)
                        }
                    }
                    binding.detailNamePokemon.setOnClickListener {
                        iniciarVarities(arguments?.getString("id","") ?: "")

                    }
                    binding.txtEntrarVarieties.setOnClickListener {
                        iniciarVarities(arguments?.getString("id","") ?: "")
                    }

                    binding.detailPokemon.setOnClickListener {
                        mudarForma()
                        if (pokemonShiny == true){
                            binding.detailPokemon.setImageURI(state.data.sprites.other.home.front_shiny)
                        }else {
                            binding.detailPokemon.setImageURI(state.data.sprites.other.home.front_default)
                        }
                    }


                }
                is DetailScreenState.Error -> {
                    Toast.makeText(context, state.messageId, Toast.LENGTH_SHORT).show()
                }
            }
        })
       viewModelSpecies.state.observe(viewLifecycleOwner, { state ->
           presentLoading(state is DetailScreenStateSpecies.Loading)

           when(state){
               is DetailScreenStateSpecies.Loading -> {}
               is DetailScreenStateSpecies.Success -> {

                   val idEvolution = state.data.evolution_chain.url.replace("https://pokeapi.co/api/v2/evolution-chain/", "")
                       .replace("/", "")

                   idColorSpecies = state.data.color.url.replace("https://pokeapi.co/api/v2/pokemon-color/", "").replace("/", "")

                   binding.buttonClickMoves.setOnClickListener {
                       mostrarMovesPokemon(arguments?.getString("id","") ?: "")
                   }

                   idEvolutionPokemon = idEvolution

                   binding.buttonStateEvolution.setOnClickListener {
                       mostrarEstagioEvolution(arguments?.getString(idEvolutionPokemon ,idEvolutionPokemon) ?: idEvolutionPokemon)
                   }

               }

               is DetailScreenStateSpecies.Error -> {
                   Toast.makeText(context, state.messageId, Toast.LENGTH_SHORT).show()
               }
           }

       }

       )


        setHasOptionsMenu(true)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun presentLoading(show: Boolean){
        if (show){
            binding.detailsShimmerPokemon.showShimmer(true)
            binding.detaisShimmerNamePokemon.showShimmer(true)
            binding.detaisShimmerType.showShimmer(true)
            binding.detailShimmerType1.showShimmer(true)
            binding.detaisShimmerType2.showShimmer(true)

            binding.detaisTeste.showShimmer(true)
        }else {
            binding.detailsShimmerPokemon.hideShimmer()
            binding.detaisShimmerNamePokemon.hideShimmer()
            binding.detaisShimmerType.hideShimmer()
            binding.detailShimmerType1.hideShimmer()
            binding.detaisShimmerType2.hideShimmer()

            binding.detaisTeste.hideShimmer()
        }
    }


    override fun onCreateOptionsMenu(menu_second_Fragment: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.menu_second_fragment, menu_second_Fragment)
    }

    override fun onOptionsItemSelected(itemImages: MenuItem): Boolean  {
        return when (itemImages.itemId) {

            R.id.menu_formas -> {

                iniciarDetailsImage(arguments?.getString("id","") ?: "")
                true
            }
            R.id.estagios -> {
                mostrarEstagioEvolution(arguments?.getString(idEvolutionPokemon ,idEvolutionPokemon) ?: idEvolutionPokemon)
                true
            }
            R.id.varieties -> {
                iniciarVarities(arguments?.getString("id","") ?: "")
                true
            }

            else -> super.onOptionsItemSelected(itemImages)
        }
    }

    private fun iniciarDetailsImage(pokemonId: String){
        findNavController().navigate(R.id.action_SecondFragment_to_DetailImagesFragment, bundleOf("id" to pokemonId))
    }


    private fun mostrarEstagioEvolution(pokemonId: String){
        findNavController().navigate(R.id.action_SecondFragment_to_EstagioEvolutionFragment, bundleOf("id" to pokemonId))
    }
    private fun mostrarMovesPokemon(pokemonId: String){
        findNavController().navigate(R.id.action_SecondFragment_to_MovesPokemonFragment, bundleOf("id" to pokemonId))
    }

    private fun iniciarVarities(pokemonId: String){
        findNavController().navigate(R.id.action_SecondFragmentToVarietiesFragment, bundleOf("id" to pokemonId))
    }

    fun mostrarTypes(pokemonId: String){
        findNavController().navigate(R.id.action_secondFragment_to_fragmentDetailMenu, bundleOf("id" to pokemonId))
    }

    private fun mudarForma() {
        if (pokemonShiny == true){
            pokemonShiny = false
        } else {
            if(pokemonShiny == false){
                pokemonShiny = true
            }
        }
    }


}
