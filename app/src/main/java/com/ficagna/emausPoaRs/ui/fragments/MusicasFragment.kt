package com.ficagna.emausPoaRs.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ficagna.emausPoaRs.R
import com.ficagna.emausPoaRs.ui.model.Musicas
import com.google.firebase.database.FirebaseDatabase


class MusicasFragment : Fragment() {


    private lateinit var musicasList: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_musicas, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewMusicas)

        FirebaseDatabase.getInstance().reference.child("database").child("musicas")
            .get().addOnSuccessListener {
                Log.d("teste", it.toString())
                val musicas =
                    it.children.map { item -> item.getValue(Musicas::class.java) }.toList()
                Log.d("musicas", musicas.toString())
//                recyclerView.adapter =
//                    MusicasListAdapter(musicasList = musicas, context = requireContext())

            }
        return view
    }


}