package com.douglas2990.pokedexmyapplication2990.imagesDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.douglas2990.pokedexmyapplication2990.databinding.DetailImagesFragmentBinding
import com.douglas2990.pokedexmyapplication2990.detailSpecies.DetailPokemonSpeciesFactory
import com.douglas2990.pokedexmyapplication2990.detailSpecies.DetailPokemonSpeciesViewModel
import com.douglas2990.pokedexmyapplication2990.detailSpecies.DetailScreenStateSpecies

class DetailImagesFragment : Fragment() {


    private var _binding: DetailImagesFragmentBinding? = null

    private val binding get() = _binding!!

    private var pokemonfrontShinydefault: Boolean = false
    private var pokemonShinyImage2: Boolean = false
    private var pokemonShinyImage3: Boolean = false
    private var pokemonShinyImage4: Boolean = false

    private  val viewModel by viewModels<ImagesViewModel> {
        DetailImagesFactory(arguments?.getString("id","") ?: "")
    }
    private val viewModelSpecies by viewModels<DetailPokemonSpeciesViewModel>{
        DetailPokemonSpeciesFactory(arguments?.getString("id","") ?: "")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailImagesFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, { state ->
            presentLoading(state is ImageScreenState.Loading)
            Toast.makeText(context,"clique na imagem para mostrar a forma shiny", Toast.LENGTH_LONG).show()

            when(state){
                is ImageScreenState.Loading -> {}
                is ImageScreenState.Success -> {


                    binding.detailImg1.setImageURI(state.data.sprites.front_default)

                    if(state.data.sprites.front_female != null) {
                        binding.detailImg2.setImageURI(state.data.sprites.front_female)
                        binding.detailImg3.setImageURI(state.data.sprites.back_default)
                        binding.detailImg4.setImageURI(state.data.sprites.back_female)
                        binding.txtFrontDefault.text = ("Front Male")
                        binding.txtFrontFemale.text = ("Front Female")
                        binding.txtBackDefault.text = ("Back Male")
                        binding.txtBackFemale.text = ("back Female")
                        binding.detailImg2.setOnClickListener {
                            mudarFormaImage2()
                            if (pokemonShinyImage2 == true){
                                binding.detailImg2.setImageURI(state.data.sprites.front_shiny_female)
                                binding.txtFrontFemale.text = ("   Front Female Shiny")
                            } else {
                                binding.detailImg2.setImageURI(state.data.sprites.front_female)
                                binding.txtFrontFemale.text = ("   Front Female")
                            }
                        }
                        binding.detailImg3.setOnClickListener {
                            mudarFormaImage3()
                            if (pokemonShinyImage3 == true){
                                binding.detailImg3.setImageURI(state.data.sprites.back_shiny)
                                binding.txtBackDefault.text = ("   Back Male Shiny")
                            } else {
                                binding.detailImg3.setImageURI(state.data.sprites.back_default)
                                binding.txtBackDefault.text = ("   Back Male")
                            }
                        }
                        binding.detailImg4.setOnClickListener {
                            mudarFormaImage4()
                            if (pokemonShinyImage4 == true){
                                binding.detailImg4.setImageURI(state.data.sprites.back_shiny_female)
                                //binding.detailImg4.setImageURI(state.data.sprites.versions.generation_v.black_white.back_shiny_female)
                                binding.txtBackFemale.text = ("   back Female Shiny")
                            }else {
                                binding.detailImg4.setImageURI(state.data.sprites.back_female)
                                binding.txtBackFemale.text = ("   back Female")
                            }
                        }

                    }
                    else{
                        binding.txtFrontDefault.text = ("Front")
                        binding.txtFrontFemale.text = ("Back")
                        binding.txtBackDefault.text = ""
                        binding.detailImg2.setImageURI(state.data.sprites.back_default)
                        binding.detailImg2.setOnClickListener {
                            mudarFormaImage2()
                            if (pokemonShinyImage2 == true){
                                binding.detailImg2.setImageURI(state.data.sprites.back_shiny)
                                binding.txtFrontFemale.text = ("  Back Shiny")
                            } else {
                                binding.detailImg2.setImageURI(state.data.sprites.back_default)
                                binding.txtFrontFemale.text = ("   Back")
                            }
                        }
                    }

                    binding.detailImg1.setOnClickListener{
                        mudarFormaFrontMale()
                        if(pokemonfrontShinydefault == true){
                            binding.detailImg1.setImageURI(state.data.sprites.front_shiny)
                            if(state.data.sprites.front_female != null){
                                binding.txtFrontDefault.text = ("Front Male Shiny")
                            }else{
                                binding.txtFrontDefault.text = ("Front Shiny")
                            }
                        }else {
                            binding.detailImg1.setImageURI(state.data.sprites.front_default)
                            if(state.data.sprites.front_female != null) {
                                binding.txtFrontDefault.text = ("Front Male")
                            }else{
                                binding.txtFrontDefault.text = ("Front")
                            }
                        }
                    }

                }
                is ImageScreenState.Error -> {
                    Toast.makeText(context, state.messageId, Toast.LENGTH_SHORT).show()
                }
            }
        })
        viewModelSpecies.state.observe(viewLifecycleOwner, { state ->
            presentLoading(state is DetailScreenStateSpecies.Loading)

            when(state){
                is DetailScreenStateSpecies.Loading -> {}
                is DetailScreenStateSpecies.Success -> {
                    binding.aclimgDetailsImg.setBackgroundColor(state.data.color.url.hashCode())

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
        if(show){
            binding.detailsLayoutImg1.showShimmer(true)
            binding.detailsLayoutImg2.showShimmer(true)
            binding.detailsLayoutImg3.showShimmer(true)
            binding.detailsLayoutImg4.showShimmer(true)
            binding.layoutFormaFrontDefault.showShimmer(true)
            binding.layoutFormaFrontFemale.showShimmer(true)
            binding.layoutFormaBackDefault.showShimmer(true)
            binding.layoutFormaBackFemale.showShimmer(true)
        }
        else {
            binding.detailsLayoutImg1.hideShimmer()
            binding.detailsLayoutImg2.hideShimmer()
            binding.detailsLayoutImg3.hideShimmer()
            binding.detailsLayoutImg4.hideShimmer()
            binding.layoutFormaFrontDefault.hideShimmer()
            binding.layoutFormaFrontFemale.hideShimmer()
            binding.layoutFormaBackDefault.hideShimmer()
            binding.layoutFormaBackFemale.hideShimmer()
        }
    }

    private fun mudarFormaFrontMale() {
        if (pokemonfrontShinydefault == true){
            pokemonfrontShinydefault = false
        } else {
            if(pokemonfrontShinydefault == false){
                pokemonfrontShinydefault = true
            }
        }
    }

    private fun mudarFormaImage2() {
        if (pokemonShinyImage2 == true){
            pokemonShinyImage2 = false
        } else {
            if(pokemonShinyImage2 == false){
                pokemonShinyImage2 = true
            }
        }
    }

    private fun mudarFormaImage3() {
        if (pokemonShinyImage3 == true){
            pokemonShinyImage3 = false
        } else {
            if(pokemonShinyImage3 == false){
                pokemonShinyImage3 = true
            }
        }
    }

    private fun mudarFormaImage4() {
        if (pokemonShinyImage4 == true){
            pokemonShinyImage4 = false
        } else {
            if(pokemonShinyImage4 == false){
                pokemonShinyImage4 = true
            }
        }
    }

}