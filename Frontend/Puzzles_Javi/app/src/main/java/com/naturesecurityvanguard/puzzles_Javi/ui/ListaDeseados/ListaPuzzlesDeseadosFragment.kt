package com.naturesecurityvanguard.puzzles_Javi.ui.ListaDeseados

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.naturesecurityvanguard.puzzles_Javi.R
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.Puzzle
import com.naturesecurityvanguard.puzzles_Javi.ui.ListaPuzzles.MyPuzzleRecyclerViewAdapter
import com.naturesecurityvanguard.puzzles_Javi.ui.ListaPuzzles.PuzzleViewModel


/**
 * A fragment representing a list of Items.
 */
class ListaPuzzlesDeseadosFragment : Fragment() {

    var puzzlesDeseadosList: List<Puzzle> = listOf()
    lateinit var listAdapter: MyListaPuzzlesDeseadosRecyclerViewAdapter
    lateinit var viewModel: PuzzleDeseadosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_puzzles_deseados_list, container, false)

        viewModel = ViewModelProvider(this).get(PuzzleDeseadosViewModel::class.java)
        val v = view as RecyclerView
        v.layoutManager = LinearLayoutManager(context)
        listAdapter = MyListaPuzzlesDeseadosRecyclerViewAdapter(activity as Context, viewModel, puzzlesDeseadosList)
        v.adapter = listAdapter


        viewModel.puzzlesDeseados.observe(viewLifecycleOwner, Observer {
                puzzlesDeseados -> puzzlesDeseadosList = puzzlesDeseados
            Log.i("puzzles Deseados: ", puzzlesDeseadosList.toString())
            listAdapter.setData(puzzlesDeseados)
        })

        return view
    }

}