package com.naturesecurityvanguard.puzzles_Javi.ui.Admin.PantallaInicioAdmin

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.naturesecurityvanguard.puzzles_Javi.R
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.Puzzle
import com.naturesecurityvanguard.puzzles_Javi.retrofit.PuzzleService
import com.naturesecurityvanguard.puzzles_Javi.ui.ListaPuzzles.MyPuzzleRecyclerViewAdapter
import com.naturesecurityvanguard.puzzles_Javi.ui.ListaPuzzles.PuzzleViewModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ZonaAdminFragment : Fragment() {
    var puzzleList: List<Puzzle> = listOf()
    lateinit var listAdapter: ZonaAdminRecyclerView
    lateinit var viewModel: ZonaAdminViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_puzzle_admin_list, container, false)

        viewModel = ViewModelProvider(this).get(ZonaAdminViewModel::class.java)
        // Set the adapter
        val v = view as RecyclerView
        v.layoutManager = LinearLayoutManager(context)
        listAdapter = ZonaAdminRecyclerView(activity as Context, viewModel, puzzleList)
        v.adapter = listAdapter


        viewModel.puzzle.observe(viewLifecycleOwner, Observer {
                puzzles -> puzzleList = puzzles
            Log.i("puzzles: ", puzzleList.toString())
            listAdapter.setData(puzzles)
        })

        return view
    }
}