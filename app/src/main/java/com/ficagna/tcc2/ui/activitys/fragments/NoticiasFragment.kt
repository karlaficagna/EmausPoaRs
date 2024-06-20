package com.ficagna.tcc2.ui.activitys.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ficagna.tcc2.R
import com.ficagna.tcc2.ui.adapter.NoticiasListAdapter
import com.ficagna.tcc2.ui.model.Noticias
import com.google.firebase.database.FirebaseDatabase


class NoticiasFragment : Fragment() {

    private lateinit var noticiasList: FirebaseDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_noticias, container, false)
        val recyclerViewNoticias = view.findViewById<RecyclerView>(R.id.recyclerViewNoticias)

        FirebaseDatabase.getInstance().reference.child("database").child("noticias")
            .get().addOnSuccessListener {
                Log.d("teste", it.toString())
                val noticias =
                    it.children.map { item -> item.getValue(Noticias::class.java) }.toList()
                Log.d("noticias", noticias.toString())
                recyclerViewNoticias.adapter =
                    NoticiasListAdapter(noticiasList = noticias, context = requireContext())

            }
        return view
    }
}