package com.ficagna.emausPoaRs.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ficagna.emausPoaRs.databinding.ItemNoticiasListBinding
import com.ficagna.emausPoaRs.ui.model.Noticias

class NoticiasListAdapter(

    private val noticiasList: List<Noticias?>,
    private val context: Context

) : RecyclerView.Adapter<NoticiasListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNoticiasListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NoticiasListAdapter.ViewHolder, position: Int) {
        val noticias = noticiasList[position]

        holder.binding.titulo.text = noticias?.titulo
        holder.binding.descricaoNoticia.text = noticias?.descricaoNoticia
        noticias?.imgNoticia?.let {

        }
    }

    override fun getItemCount(): Int = noticiasList.size

    inner class ViewHolder(val binding: ItemNoticiasListBinding) :
        RecyclerView.ViewHolder(binding.root)
}
