package com.ficagna.tcc2.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ficagna.tcc2.R
import com.ficagna.tcc2.ui.adapter.ProductListAdapter
import com.ficagna.tcc2.ui.model.Product
import com.google.firebase.database.FirebaseDatabase

class ProductFragment : Fragment() {

    private lateinit var productList: FirebaseDatabase


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        FirebaseDatabase.getInstance().reference.child("database").child("loja")
            .get().addOnSuccessListener {
                Log.d("teste", it.toString())
                val products = it.children.map { item -> item.getValue(Product::class.java) }.toList()
                Log.d("products", products.toString())
                recyclerView.adapter = ProductListAdapter(productList = products, context = requireContext())

            }
        return view
    }

}
