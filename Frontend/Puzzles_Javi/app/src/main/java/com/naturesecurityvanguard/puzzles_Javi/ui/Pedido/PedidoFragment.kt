package com.naturesecurityvanguard.puzzles_Javi.ui.Pedido

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
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.LineaPedido
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.Pedido
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.Puzzle
import com.naturesecurityvanguard.puzzles_Javi.ui.ListaPuzzles.MyPuzzleRecyclerViewAdapter
import com.naturesecurityvanguard.puzzles_Javi.ui.ListaPuzzles.PuzzleViewModel


class PedidoFragment : Fragment() {

    var pedidoList: List<LineaPedido> = listOf()
    lateinit var listAdapter: MyPedidoRecyclerViewAdapter
    lateinit var viewModel: PedidoViewModel
    lateinit var lista: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(PedidoViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_pedido, container, false)

        lista = view.findViewById(R.id.list_pedidos)
        val v = lista

        v.layoutManager = LinearLayoutManager(context)
        listAdapter = MyPedidoRecyclerViewAdapter(activity as Context, viewModel, pedidoList)
        v.adapter = listAdapter


        viewModel.pedido.observe(viewLifecycleOwner, Observer {
            pedido -> pedidoList = pedido
            Log.i("Pedido: ", pedidoList.toString())
            listAdapter.setData(pedido)
        })

        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPedidoList()
    }
}