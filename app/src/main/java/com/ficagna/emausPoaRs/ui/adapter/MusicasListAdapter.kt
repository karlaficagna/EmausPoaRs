//package com.ficagna.emausPoaRs.ui.adapter
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.ficagna.emausPoaRs.databinding.ItemMusicasListBinding
//import com.ficagna.emausPoaRs.ui.model.Musicas
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.ktx.Firebase
//
//class MusicasListAdapter(
//
//
//    private val musicasList: List<Musicas?>,
//    private val context: Context
//
//) : RecyclerView.Adapter<MusicasListAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(
//            ItemMusicasListBinding.inflate(
//                LayoutInflater.from(parent.context),
//                parent, false
//            )
//        )
//    }
//
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val musicas = musicasList[position]
//
//        holder.binding.nomeMusica.text = musicas?.nomeMusica
//       musicas?.imgMusica?.let {
//            val resId: Int = context.resources.getIdentifier(
//                it,
//                "drawable",
//                context.packageName
//            )
//            holder.binding.imgMusica.setImageResource(resId)
//        }
//
//    }
//
//    override fun getItemCount(): Int = musicasList.size
//
//    inner class ViewHolder(val binding: ItemMusicasListBinding) :
//        RecyclerView.ViewHolder(binding.root)
//}
//
//
//
