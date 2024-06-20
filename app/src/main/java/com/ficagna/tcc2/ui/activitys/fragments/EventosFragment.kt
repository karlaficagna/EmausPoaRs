package com.ficagna.tcc2.ui.activitys.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ficagna.tcc2.R
import com.ficagna.tcc2.ui.adapter.EventosListAdapter
import com.ficagna.tcc2.ui.model.Eventos
import com.google.firebase.database.FirebaseDatabase

class EventosFragment : Fragment() {

    private lateinit var eventosList: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_eventos, container, false)
        val recyclerViewAtividades = view.findViewById<RecyclerView>(R.id.recyclerViewAtividades)

        FirebaseDatabase.getInstance().reference.child("database").child("eventos")
            .get().addOnSuccessListener {
                Log.d("teste", it.toString())
                val eventos =
                    it.children.map { item -> item.getValue(Eventos::class.java) }.toList()
                Log.d("eventos", eventos.toString())
                recyclerViewAtividades.adapter =
                    EventosListAdapter(eventosList = eventos, context = requireContext())

            }
        return view
    }

}