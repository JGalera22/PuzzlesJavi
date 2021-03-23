package com.naturesecurityvanguard.puzzles_Javi.ui.Admin.ListaUsuario

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.naturesecurityvanguard.puzzles_Javi.R
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.Puzzle
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.Usuario
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.UsuarioDetalleList
import com.naturesecurityvanguard.puzzles_Javi.ui.ListaPuzzles.MyPuzzleRecyclerViewAdapter
import com.naturesecurityvanguard.puzzles_Javi.ui.ListaPuzzles.PuzzleViewModel

/**
 * A fragment representing a list of Items.
 */
class UsuarioFragment : Fragment() {

    var usuarioList: List<UsuarioDetalleList> = listOf()
    lateinit var listAdapter: MyUsuarioRecyclerViewAdapter
    lateinit var viewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_usuario, container, false)

        viewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
        // Set the adapter
        val v = view as RecyclerView
        v.layoutManager = LinearLayoutManager(context)
        listAdapter = MyUsuarioRecyclerViewAdapter(activity as Context, viewModel, usuarioList)
        v.adapter = listAdapter


        viewModel.usuario.observe(viewLifecycleOwner, Observer {
                usuarios -> usuarioList = usuarios
            Log.i("puzzles: ", usuarioList.toString())
            listAdapter.setData(usuarios)
        })

        return view
    }
}