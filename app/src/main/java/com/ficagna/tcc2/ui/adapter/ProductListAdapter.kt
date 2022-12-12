package com.ficagna.tcc2.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ficagna.tcc2.databinding.ItemProductBinding
import com.ficagna.tcc2.ui.model.Product

class ProductListAdapter(

    private val productList: List<Product?>,
    private val context: Context

) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prod = productList[position]

        holder.binding.nomeProd.text = prod?.nameProd
        holder.binding.preco.text = prod?.preco
        holder.binding.quantidade.text = prod?.quantidade
        holder.binding.total.text = prod?.total
        prod?.imageProd?.let {
            val resId: Int = context.resources.getIdentifier(
                it,
                "drawable",
                context.getPackageName()
            )
            holder.binding.image.setImageResource(resId) }

    }

    override fun getItemCount(): Int = productList.size

    inner class ViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}