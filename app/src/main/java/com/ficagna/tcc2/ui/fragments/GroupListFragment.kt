package com.ficagna.tcc2.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ficagna.tcc2.R
import com.ficagna.tcc2.ui.adapter.GroupListAdapter
import com.ficagna.tcc2.ui.model.Group
import com.google.firebase.database.FirebaseDatabase

class GroupListFragment : Fragment() {


    private lateinit var groupList: FirebaseDatabase


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_group_list, container, false)


        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        FirebaseDatabase.getInstance().getReference().child("database").child("grupos")
            .get().addOnSuccessListener {
                Log.d("teste", it.toString())
                val groups = it.children.map { item -> item.getValue(Group::class.java) }.toList()
                Log.d("groups", groups.toString())
                recyclerView.adapter = GroupListAdapter(groupList = groups)

            }
        return view
    }

}










