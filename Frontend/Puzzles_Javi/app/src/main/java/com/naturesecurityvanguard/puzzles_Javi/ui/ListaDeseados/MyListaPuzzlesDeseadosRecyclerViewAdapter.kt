package com.naturesecurityvanguard.puzzles_Javi.ui.ListaDeseados

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.naturesecurityvanguard.puzzles_Javi.R
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.Puzzle
import com.naturesecurityvanguard.puzzles_Javi.ui.DetallePuzzleDeseado.DetallePuzzleDeseadoActivity


/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */

class MyListaPuzzlesDeseadosRecyclerViewAdapter(
    private val activity: Context,
    private val viewModel: PuzzleDeseadosViewModel,
    private var values: List<Puzzle>
) : RecyclerView.Adapter<MyListaPuzzlesDeseadosRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_puzzle_deseado, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.titleView.text = " ${item.nombre}"
        holder.categoriaView.text = "${item.categoria}"
        holder.precioView.text = item.precio.toString()  + "€"
        holder.fotoView.load(item.imagen)


        holder.rootView.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, DetallePuzzleDeseadoActivity::class.java).apply {
                putExtra("puzzleId", item.id)
            }
            activity.startActivity(intent)
        })
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.text_view_titulo)
        val precioView: TextView = view.findViewById(R.id.text_view_precio)
        val categoriaView: TextView = view.findViewById(R.id.text_view_categoria)
        val fotoView: ImageView = view.findViewById(R.id.image_view_foto)
        val rootView: View = view.findViewById(R.id.puzzle_deseado_view)
    }

    fun setData(newPuzzle: List<Puzzle>){
        this.values = newPuzzle
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = values.size

}