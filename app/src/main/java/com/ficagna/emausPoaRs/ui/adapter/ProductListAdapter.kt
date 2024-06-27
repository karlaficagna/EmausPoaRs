package com.ficagna.emausPoaRs.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ficagna.emausPoaRs.databinding.ItemProductBinding
import com.ficagna.emausPoaRs.ui.model.Product

class ProductListAdapter(

    private val productList: List<Product?>,
    private val context: Context

) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prod = productList[position]

        holder.binding.nome.text = prod?.nameProd
        holder.binding.value.text = prod?.preco
//        holder.binding.quantity.text = prod?.quantidade
//        holder.binding.totalValue.text = prod?.total
        prod?.imageProd?.let {
            val resId: Int = context.resources.getIdentifier(
                it,
                "drawable",
                context.packageName
            )
            holder.binding.image.setImageResource(resId)
        }

    }

    override fun getItemCount(): Int = productList.size

    inner class ViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)
}