package com.ficagna.tcc2.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ficagna.tcc2.databinding.ItemListBinding
import com.ficagna.tcc2.ui.model.Group

class GroupListAdapter(

    private val groupList: List<Group?>,
    private val context: Context

) : RecyclerView.Adapter<GroupListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val group = groupList[position]

        holder.binding.nome.text = group?.nameGroup
        holder.binding.coordenador.text = group?.nameCoordenador
        holder.binding.local.text = group?.localReuniao
        holder.binding.dia.text = group?.diaReuniao
        holder.binding.hora.text = group?.hora
        group?.img?.let {
            val resId: Int = context.resources.getIdentifier(
                it,
                "drawable",
                context.getPackageName()
            )
            holder.binding.logoGroup.setImageResource(resId) }
    }

    override fun getItemCount(): Int = groupList.size

    inner class ViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root)

}







