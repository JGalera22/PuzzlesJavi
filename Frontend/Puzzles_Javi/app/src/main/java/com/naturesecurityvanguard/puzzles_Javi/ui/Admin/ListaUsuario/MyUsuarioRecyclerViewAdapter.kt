package com.naturesecurityvanguard.puzzles_Javi.ui.Admin.ListaUsuario

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.naturesecurityvanguard.puzzles_Javi.R
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.Usuario
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.UsuarioDetalleList
import com.naturesecurityvanguard.puzzles_Javi.ui.Usuario.UsuarioActivity



class MyUsuarioRecyclerViewAdapter(
    private val activity: Context,
    private val viewModel: UsuarioViewModel,
    private var values: List<UsuarioDetalleList>
) : RecyclerView.Adapter<MyUsuarioRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_usuario, parent, false)
        return ViewHolder(view)
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.textView_nombre)
        val usernameView: TextView = view.findViewById(R.id.textView_username)
        val emailView: TextView = view.findViewById(R.id.textView_email)
        val rootView: View = view.findViewById(R.id.usuario_view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.nameView.text = " ${item.nombreCompleto}"
        holder.usernameView.text = "${item.username}"
        holder.emailView.text = "${item.email}"



        holder.rootView.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, UsuarioActivity::class.java).apply {
                putExtra("usuarioId", item.id)
            }
            activity.startActivity(intent)
        })
    }

    fun setData(newUsuario: List<UsuarioDetalleList>){
        this.values = newUsuario
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = values.size


}