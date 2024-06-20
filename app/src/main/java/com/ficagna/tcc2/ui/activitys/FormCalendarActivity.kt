package com.ficagna.tcc2.ui.activitys

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ficagna.tcc2.databinding.ActivityFormCalendarBinding
import com.google.firebase.firestore.FirebaseFirestore

@Suppress("UNREACHABLE_CODE")
class FormCalendarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormCalendarBinding
    private val saveDb = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btSave.setOnClickListener {
            val eventosMap = hashMapOf(
                "titulo" to "Maranatha de Natal",
                "data" to "11/12/22",
                "hora" to "19:30",
                "local" to "Maronta"
            )

            saveDb.collection("Eventos").document("Marantha ed Natal")
                .set(eventosMap).addOnCompleteListener {
                    Log.d("saveDb", "Evento cadastrado com sucesso!")
                }.addOnFailureListener { }

        }
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