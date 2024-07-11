package com.ficagna.emausPoaRs.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ficagna.emausPoaRs.databinding.FragmentFormCalendarBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.to

class FormCalendarFragment : Fragment() {

    private var _binding: FragmentFormCalendarBinding? = null
    private val binding get() = _binding!!
    private val saveDb = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btSave.setOnClickListener {
            val eventosMap = hashMapOf(
                "titulo" to "Maranatha de Natal",
                "data" to "11/12/22",
                "hora" to "19:30",
                "local" to "Maronta"
            )

            saveDb.collection("Eventos").document("Marantha ed Natal")
                .set(eventosMap)
                .addOnCompleteListener {
                    Log.d("saveDb", "Evento cadastrado com sucesso!")
                }
                .addOnFailureListener {
                    Log.e("saveDb", "Falha ao cadastrar evento", it)
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


//    private fun saveData() {
//        val titulo = binding.etTitulo.text.toString().trim()
//        val data = binding.etData.text.toString().trim()
//        val hora = binding.etHora.text.toString().trim()
//        val local = binding.etLocal.text.toString().trim()
//
//        if (titulo.isNotEmpty()) {
//            if (data.isNotEmpty()) {
//                if (hora.isNotEmpty()) {
//
//                    salvarEvento(titulo, data, hora)
//
//                } else {
//                    Toast.makeText(
//                        requireContext(),
//                        "Informe o título do seu evemto",
//                        Toast.LENGTH_SHORT
//                    )
//                        .show()
//                }
//            } else {
//                Toast.makeText(requireContext(), "Informe a data do seu evemto", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        } else {
//            Toast.makeText(requireContext(), "Informe a hora do seu evemto", Toast.LENGTH_SHORT)
//                .show()
//        }
//    }
//
//    private fun salvarEvento(titulo: String, data: String, hora: String) {
//        save.currentUser(titulo, data, hora)
//            .addOnCompleteListener(requireActivity()) { task ->
//                if (task.isSuccessful) {
//                    findNavController().navigate(R.id.action_formCadastroFragment_to_formLoginFragment)
//                } else {
//                    Toast.makeText(
//                        requireContext(),
//                        FirebaseHelper.validError(task.exception?.message ?: ""),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//
//    }
//}
//}