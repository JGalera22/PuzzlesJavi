package com.naturesecurityvanguard.puzzles_Javi.ui.ListaPuzzles

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.naturesecurityvanguard.puzzles_Javi.R
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.Puzzle


/**
 * A fragment representing a list of Items.
 */
class PuzzleFragment : Fragment() {

    var puzzleList: List<Puzzle> = listOf()
    lateinit var listAdapter: MyPuzzleRecyclerViewAdapter
    lateinit var viewModel: PuzzleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_puzzle_list, container, false)

        viewModel = ViewModelProvider(this).get(PuzzleViewModel::class.java)
        // Set the adapter
        val v = view as RecyclerView
        v.layoutManager = LinearLayoutManager(context)
        listAdapter = MyPuzzleRecyclerViewAdapter(activity as Context, viewModel, puzzleList)
        v.adapter = listAdapter


        viewModel.puzzle.observe(viewLifecycleOwner, Observer {
            puzzles -> puzzleList = puzzles
            Log.i("puzzles Home: ", puzzleList.toString())
            listAdapter.setData(puzzles)
        })

        return view
    }
}