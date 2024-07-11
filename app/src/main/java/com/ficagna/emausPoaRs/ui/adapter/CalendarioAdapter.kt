package com.ficagna.emausPoaRs.ui.adapter

import com.ficagna.emausPoaRs.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ficagna.emausPoaRs.ui.model.Calendario


class CalendarioAdapter(private val calendarios: List<Calendario>) :
    RecyclerView.Adapter<CalendarioAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_calendario, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = calendarios[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int = calendarios.size

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val eventName: TextView = itemView.findViewById(R.id.tvEventName)

        fun bind(calendario: Calendario) {
            eventName.text = calendario.name
        }
    }
}