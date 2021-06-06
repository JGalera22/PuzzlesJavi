package com.naturesecurityvanguard.puzzles_Javi.ui.puzzles

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.naturesecurityvanguard.puzzles_Javi.R
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.Puzzle
import com.naturesecurityvanguard.puzzles_Javi.ui.DetallePuzzle.DetallePuzzleActivity


class MyPuzzleRecyclerViewAdapter(
        private val activity: Context,
        private val viewModel: PuzzleViewModel,
        private var values: List<Puzzle>
) : RecyclerView.Adapter<MyPuzzleRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_puzzle, parent, false)
        return ViewHolder(view)
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.text_view_titulo)
        val precioView: TextView = view.findViewById(R.id.text_view_precio)
        val categoriaView: TextView = view.findViewById(R.id.text_view_categoria)
        val fotoView: ImageView = view.findViewById(R.id.image_view_foto)
        val rootView: View = view.findViewById(R.id.puzzle_view)
        val favorite: ImageButton = view.findViewById(R.id.image_button_favorito2)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.titleView.text = " ${item.nombre}"
        holder.categoriaView.text = "${item.categoria}"
        holder.precioView.text = item.precio.toString()  + "€"
        holder.fotoView.load(item.imagen)
        var deseado= item.deseado

        if(deseado){
            holder.favorite.load(R.drawable.ic_favorite_24_rojo)

        } else{
            holder.favorite.load(R.drawable.ic_favorite_border_24)
        }


        holder.rootView.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, DetallePuzzleActivity::class.java).apply {
                putExtra("puzzleId", item.id)
            }
            activity.startActivity(intent)
        })
        holder.favorite.setOnClickListener(View.OnClickListener {
            viewModel.createPuzzleDeseado(item.id, item.deseado)
        })

    }

    fun setData(newPuzzle: List<Puzzle>){
        this.values = newPuzzle
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = values.size


}