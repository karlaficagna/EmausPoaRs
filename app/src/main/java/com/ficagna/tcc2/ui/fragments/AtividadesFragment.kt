package com.ficagna.tcc2.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ficagna.tcc2.R
import com.ficagna.tcc2.ui.adapter.AtividadesListAdapter
import com.ficagna.tcc2.ui.model.Atividades
import com.google.firebase.database.FirebaseDatabase

class AtividadesFragment : Fragment() {

    private lateinit var atividadeList: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_atividades_list, container, false)
        val recyclerViewAtividades = view.findViewById<RecyclerView>(R.id.recyclerViewAtividades)

        FirebaseDatabase.getInstance().reference.child("database").child("atividades")
            .get().addOnSuccessListener {
                Log.d("teste", it.toString())
                val atividades =
                    it.children.map { item -> item.getValue(Atividades::class.java) }.toList()
                Log.d("atividades", atividades.toString())
                recyclerViewAtividades.adapter =
                    AtividadesListAdapter(atividadeList = atividades, context = requireContext())

            }
        return view
    }

}