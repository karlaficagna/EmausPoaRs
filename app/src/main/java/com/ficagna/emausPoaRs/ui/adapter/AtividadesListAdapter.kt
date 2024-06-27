package com.ficagna.emausPoaRs.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ficagna.emausPoaRs.databinding.ItemAtividadeListBinding
import com.ficagna.emausPoaRs.ui.model.Atividades

class AtividadesListAdapter(

    private val atividadeList: List<Atividades?>,
    private val context: Context

) : RecyclerView.Adapter<AtividadesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAtividadeListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AtividadesListAdapter.ViewHolder, position: Int) {
        val atividades = atividadeList[position]

        holder.binding.evento.text = atividades?.evento
        holder.binding.data.text = atividades?.dataEvento
        holder.binding.horario.text = atividades?.horario
        holder.binding.local.text = atividades?.local
        atividades?.imgAtividade?.let {

        }

    }

    override fun getItemCount(): Int = atividadeList.size

    inner class ViewHolder(val binding: ItemAtividadeListBinding) :
        RecyclerView.ViewHolder(binding.root)
}



