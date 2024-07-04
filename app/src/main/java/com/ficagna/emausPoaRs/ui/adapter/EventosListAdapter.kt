package com.ficagna.emausPoaRs.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ficagna.emausPoaRs.databinding.ItemEventosListBinding
import com.ficagna.emausPoaRs.ui.model.Eventos

class EventosListAdapter(

    private val eventosList: List<Eventos?>,
    private val context: Context

) : RecyclerView.Adapter<EventosListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemEventosListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val eventos = eventosList[position]

        holder.binding.evento.text = eventos?.evento
        holder.binding.data.text = eventos?.dataEvento
        holder.binding.horario.text = eventos?.horario
        holder.binding.local.text = eventos?.local
        eventos?.imgEvento?.let {
            val resId: Int = context.resources.getIdentifier(
                it,
                "drawable",
                context.packageName
            )
            holder.binding.imgEventos.setImageResource(resId)
        }

    }

    override fun getItemCount(): Int = eventosList.size

    inner class ViewHolder(val binding: ItemEventosListBinding) :
        RecyclerView.ViewHolder(binding.root)
}



