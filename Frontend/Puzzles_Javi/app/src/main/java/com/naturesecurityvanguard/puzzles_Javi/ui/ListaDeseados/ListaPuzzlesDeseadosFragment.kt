package com.naturesecurityvanguard.puzzles_Javi.ui.ListaDeseados

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naturesecurityvanguard.puzzles_Javi.R


/**
 * A fragment representing a list of Items.
 */
class ListaPuzzlesDeseadosFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_puzzle_list, container, false)

        // Set the adapter

        return view
    }


}