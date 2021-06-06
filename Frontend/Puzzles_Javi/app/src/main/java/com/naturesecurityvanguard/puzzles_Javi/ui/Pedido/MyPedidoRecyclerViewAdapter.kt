package com.naturesecurityvanguard.puzzles_Javi.ui.Pedido

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
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.LineaPedido
import com.naturesecurityvanguard.puzzles_Javi.ui.Admin.DetallePuzzleAdmin.DetallePuzzleAdminActivity

class MyPedidoRecyclerViewAdapter(
        private val activity: Context,
        private val viewModel: PedidoViewModel,
        private var values: List<LineaPedido>
) : RecyclerView.Adapter<MyPedidoRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_linea_pedido, parent, false)
        return ViewHolder(view)
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.text_view_precio)
        val precioView: TextView = view.findViewById(R.id.text_view_precio)
        val categoriaView: TextView = view.findViewById(R.id.text_view_categoria)
        val fotoView: ImageView = view.findViewById(R.id.image_view_foto)
        val rootView: View = view.findViewById(R.id.usuario_view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.titleView.text = " ${item.nombre}"
        holder.categoriaView.text = "${item.categoria}"
        holder.precioView.text = item.precio.toString()  + "€"
        holder.fotoView.load(item.imagen)


        holder.rootView.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, DetallePuzzleAdminActivity::class.java).apply {
                putExtra("puzzleId", item.id)
            }
            activity.startActivity(intent)
        })
    }

    fun setData(newPedido: List<LineaPedido>){
        this.values = newPedido
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = values.size


}
