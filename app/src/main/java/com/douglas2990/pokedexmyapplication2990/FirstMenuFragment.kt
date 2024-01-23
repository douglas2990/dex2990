package com.douglas2990.pokedexmyapplication2990

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.douglas2990.pokedexmyapplication2990.databinding.FragmentMenuMainBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstMenuFragment : Fragment() {

    private var _binding: FragmentMenuMainBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding !!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentMenuMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabHome.setOnClickListener{
            findNavController().navigate(R.id.action_FirstMenuFragment_to_first_fragment)
        }

        binding.allMoves.setOnClickListener {
            findNavController().navigate(R.id.actionFirstMenuFragment_to_MoveFragment)
        }

        binding.allType.setOnClickListener {
            findNavController().navigate(R.id.actionFirstMenuFragment_to_TypeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}