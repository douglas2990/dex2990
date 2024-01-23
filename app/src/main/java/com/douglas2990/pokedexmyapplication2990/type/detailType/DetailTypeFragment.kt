package com.douglas2990.pokedexmyapplication2990.type.detailType

import android.os.Bundle
import android.text.TextUtils.replace
import android.view.*
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglas2990.pokedexmyapplication2990.R
import com.douglas2990.pokedexmyapplication2990.adapter.*
import com.douglas2990.pokedexmyapplication2990.databinding.DetailAuxiliarFragmentBinding


class DetailTypeFragment : Fragment() {

    private var idType = ""

    private var _binding: DetailAuxiliarFragmentBinding? = null

    private val viewModelDetailType by viewModels<DetailTypeViewModel>{
        DetailTypeFactory(arguments?.getString("id","") ?: "")
    }

    private val binding get() = _binding!!

    private val firstListenner = object : DetailTypeAdapter.DetailTypeInterface{
        override fun onClick(pokemonId: String) {
            findNavController().navigate(R.id.actionDetailTypeFragmentToSecondFragment, bundleOf("id" to pokemonId))
        }

    }

    private val typeListennerDoubleDamageFrom = object : DoubleDamageFromAdapter.DoubleDamageFromInterface{
        override fun onClick(typeId: String) {
            Toast.makeText(context,typeId,Toast.LENGTH_LONG).show()
        }
    }

    private val typeListennerDoubleDamageTo = object : DoubleDamageToAdapter.DoubleDamageToInterface{
        override fun onClick(typeId: String) {
            Toast.makeText(context,typeId,Toast.LENGTH_LONG).show()
        }
    }

    private val typeListennerHalfDamageFrom = object : HalfDamageFromAdapter.HalfDamageFromInterface{
        override fun onClick(typeId: String) {
            Toast.makeText(context,typeId,Toast.LENGTH_LONG).show()
        }
    }

    private val typeListennerHalfDamageTo = object : HalfDamageToAdapter.HalfDamageToInterface{
        override fun onClick(typeId: String) {
            Toast.makeText(context,typeId,Toast.LENGTH_LONG).show()
        }
    }

    private val typeListennerNoDamageFrom = object : NoDamageFromAdapter.NoDamageFromInterface{
        override fun onClick(typeId: String) {
            Toast.makeText(context,typeId,Toast.LENGTH_LONG).show()
        }
    }

    private val typeListennerNoDamageTo = object : NoDamageToAdapter.NoDamageToInterface{
        override fun onClick(typeId: String) {
            Toast.makeText(context,typeId,Toast.LENGTH_LONG).show()
        }
    }

    private val typeListennerMoveForType = object : MovesForTypeAdapter.MovesForTypeToInterface{
        override fun onClick(typeId: String) {
            Toast.makeText(context,typeId,Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailAuxiliarFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabHome.setOnClickListener{
            findNavController().navigate(R.id.action_detail_type_Fragment_to_first_fragment)
        }

        binding.recyclerFirst.layoutManager = LinearLayoutManager(context)
        viewModelDetailType.state.observe(viewLifecycleOwner,
             { state ->
                binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                binding.recyclerFirst.isVisible = state is DetailTypeScreenState.Success

                binding.buttonMoreDetail.visibility = View.VISIBLE

                when(state){
                    is DetailTypeScreenState.Loading -> {}
                    is DetailTypeScreenState.Success -> {
                        binding.recyclerFirst.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)

                        idType = state.data.id.toString()
                        binding.buttonMoreDetail.setOnClickListener {
                            mostrarMoreDetail(arguments?.getString("id","") ?: "")
                        }

                    }
                }
            })


        setHasOptionsMenu(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menuType: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.menu_detail_types, menuType)
    }

    override fun onOptionsItemSelected(itemType: MenuItem): Boolean {
        return when (itemType.itemId) {
            R.id.double_damage_from -> {
                mostrarMenu_double_damage_from()
                true
            }
            R.id.double_damage_to -> {
                mostrarMenu_double_damage_to()
                true
            }

            R.id.half_damage_from -> {
                mostrarMenu_half_damage_from()
                true
            }

            R.id.half_damage_to -> {
                mostrarMenu_half_damage_to()
                true
            }

            R.id.no_damage_from -> {
                mostrarMenu_no_damage_from()
                true
            }

            R.id.pokemonType -> {
                mostrarMenu_pokemonType()
                true
            }

            R.id.no_damage_to -> {
                mostrarMenu_no_damage_to()
                true
            }

            R.id.moves_for_type -> {
                mostrarMenu_moves_for_type()
                true

            }
            R.id.app_bar_search_pokemon -> {

                val searchView = itemType.actionView as androidx.appcompat.widget.SearchView
                binding.recyclerFirst.layoutManager = LinearLayoutManager(context)
                binding.recyclerFirst.setHasFixedSize(true)

                viewModelDetailType.state.observe(viewLifecycleOwner,
                    Observer { state ->
                        binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                        binding.recyclerFirst.isVisible = state is DetailTypeScreenState.Success

                        when(state) {

                            is DetailTypeScreenState.Loading -> {}
                            is DetailTypeScreenState.Success -> {
                                var listaPokemon = state.data.pokemon
                                viewModelDetailType.listaQuery = listaPokemon
                                val listaPokemonBusca = viewModelDetailType.listaQueryBusca

                                searchView.setOnQueryTextListener(object :
                                    androidx.appcompat.widget.SearchView.OnQueryTextListener {

                                    override fun onQueryTextSubmit(query: String?): Boolean {
                                        searchView.clearFocus()

                                        viewModelDetailType.pesquisarQuery(query?.lowercase())

                                        binding.recyclerFirst.adapter = DetailTypeAdapter( listaPokemonBusca, firstListenner)

                                        return true
                                    }

                                    override fun onQueryTextChange(newText: String?): Boolean {

                                        viewModelDetailType.pesquisarQuery(newText?.lowercase())

                                        binding.recyclerFirst.adapter = DetailTypeAdapter(listaPokemonBusca, firstListenner)

                                        return true
                                    }
                                })
                            }
                            is DetailTypeScreenState -> Toast.makeText(context, "erro", Toast.LENGTH_LONG).show()
                        }
                    }
                )
                true
            }

            else -> super.onOptionsItemSelected(itemType)
        }

    }

    fun mostrarMoreDetail(pokemonId: String){
        findNavController().navigate(R.id.actionDetailTypeFragmentToFragmentMenuDetailType, bundleOf("id" to pokemonId))
    }


    fun mostrarMenu_double_damage_from(){
        viewModelDetailType.state.observe(viewLifecycleOwner,
            { state ->
                binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                binding.recyclerFirst.isVisible = state is DetailTypeScreenState.Success

                when(state){
                    is DetailTypeScreenState.Loading -> {}
                    is DetailTypeScreenState.Success -> {
                        binding.recyclerFirst.adapter = DoubleDamageFromAdapter(state.data.damage_relations.double_damage_from, typeListennerDoubleDamageFrom )
                    }
                }
            })
    }

    fun mostrarMenu_double_damage_to(){
        viewModelDetailType.state.observe(viewLifecycleOwner,
             { state ->
                binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                binding.recyclerFirst.isVisible = state is DetailTypeScreenState.Success

                when(state){
                    is DetailTypeScreenState.Loading -> {}
                    is DetailTypeScreenState.Success -> {
                        binding.recyclerFirst.adapter = DoubleDamageToAdapter(state.data.damage_relations.double_damage_to, typeListennerDoubleDamageTo )
                    }
                }
            })
    }

    fun mostrarMenu_half_damage_from(){
        viewModelDetailType.state.observe(viewLifecycleOwner,
             { state ->
                binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                binding.recyclerFirst.isVisible = state is DetailTypeScreenState.Success

                when(state){
                    is DetailTypeScreenState.Loading -> {}
                    is DetailTypeScreenState.Success -> {
                        binding.recyclerFirst.adapter = HalfDamageFromAdapter(state.data.damage_relations.half_damage_from, typeListennerHalfDamageFrom )
                    }
                }
            })
    }

    fun mostrarMenu_half_damage_to(){
        viewModelDetailType.state.observe(viewLifecycleOwner,
             { state ->
                binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                binding.recyclerFirst.isVisible = state is DetailTypeScreenState.Success

                when(state){
                    is DetailTypeScreenState.Loading -> {}
                    is DetailTypeScreenState.Success -> {
                        binding.recyclerFirst.adapter = HalfDamageToAdapter(state.data.damage_relations.half_damage_to, typeListennerHalfDamageTo )
                    }
                }
            })
    }

    fun mostrarMenu_no_damage_from(){
        viewModelDetailType.state.observe(viewLifecycleOwner,
            { state ->
                binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                binding.recyclerFirst.isVisible = state is DetailTypeScreenState.Success

                when(state){
                    is DetailTypeScreenState.Loading -> {}
                    is DetailTypeScreenState.Success -> {
                        binding.recyclerFirst.adapter = NoDamageToAdapter(state.data.damage_relations.no_damage_to, typeListennerNoDamageTo )
                    }
                }
            })
    }

    fun mostrarMenu_pokemonType(){
        viewModelDetailType.state.observe(viewLifecycleOwner,
             { state ->
                binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                binding.recyclerFirst.isVisible = state is DetailTypeScreenState.Success

                when(state){
                    is DetailTypeScreenState.Loading -> {}
                    is DetailTypeScreenState.Success -> {
                        binding.recyclerFirst.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                    }
                }
            })
    }

    fun mostrarMenu_no_damage_to(){
        viewModelDetailType.state.observe(viewLifecycleOwner,
            { state ->
                binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                binding.recyclerFirst.isVisible = state is DetailTypeScreenState.Success

                when(state){
                    is DetailTypeScreenState.Loading -> {}
                    is DetailTypeScreenState.Success -> {
                        binding.recyclerFirst.adapter = DetailTypeAdapter(state.data.pokemon, firstListenner)
                    }
                }
            })
    }

    fun mostrarMenu_moves_for_type(){
        viewModelDetailType.state.observe(viewLifecycleOwner,
            { state ->
                binding.firstProgress.isVisible = state is DetailTypeScreenState.Loading
                binding.recyclerFirst.isVisible = state is DetailTypeScreenState.Success

                when(state){
                    is DetailTypeScreenState.Loading -> {}
                    is DetailTypeScreenState.Success -> {
                        binding.recyclerFirst.adapter = MovesForTypeAdapter(state.data.moves, typeListennerMoveForType)
                    }
                }
            })
    }

}