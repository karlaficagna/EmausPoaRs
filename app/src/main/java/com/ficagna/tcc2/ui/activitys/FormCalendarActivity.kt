package com.ficagna.tcc2.ui.activitys

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ficagna.tcc2.databinding.ActivityFormCalendarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class FormCalendarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormCalendarBinding
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()

        binding.btSave.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val titulo = binding.etTitulo.text.toString().trim()
        val data = binding.etData.text.toString().trim()
        val hora = binding.etHora.text.toString().trim()
        val local = binding.etLocal.text.toString().trim()

        if (titulo.isNotEmpty() && data.isNotEmpty() && hora.isNotEmpty() && local.isNotEmpty()) {
            salvarAtividade(titulo, data, hora, local)
        } else {
            when {
                titulo.isEmpty() -> showToast("Informe o título da sua atividade")
                data.isEmpty() -> showToast("Informe a data da sua atividade")
                hora.isEmpty() -> showToast("Informe a hora da sua atividade")
            }
        }
    }

    private fun salvarAtividade(titulo: String, data: String, hora: String, local: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        val eventId = database.reference.child("atividades").child(userId).child("eventId").push().key ?: ""

        val atividade = hashMapOf(
            "userId" to userId,
            "titulo" to titulo,
            "data" to data,
            "hora" to hora,
            "local" to local,
        )

        // Save event to Firebase Database
        database.reference.child("atividades").child(eventId).setValue(atividade)
            .addOnSuccessListener {
                Toast.makeText(
                    this,
                    "Atividade salva com sucesso",
                    Toast.LENGTH_SHORT
                ).show()
                binding.etTitulo.text.clear()
                binding.etData.text.clear()
                binding.etHora.text.clear()
                binding.etLocal.text.clear()
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    this,
                    "Erro ao salvar atividade: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}




