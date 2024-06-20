package com.ficagna.tcc2.ui.activitys

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ficagna.tcc2.R
import com.ficagna.tcc2.databinding.ActivityCalendarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class CalendarActivity : AppCompatActivity() {

    private lateinit var dateDisplay: CalendarView
    private lateinit var eventEditText: EditText
    private lateinit var database: FirebaseDatabase
    private var stringDateSelected: String? = null

    private lateinit var binding: ActivityCalendarBinding

    private val mesesDoAno = arrayOf(
        "Janeiro", "Fevereiro", "Março", "Abril",
        "Maio", "Junho", "Julho", "Agosto",
        "Setembro", "Outubro", "Novembro", "Dezembro"
    )

    private val diasDaSemana = arrayOf(
        "Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)


        dateDisplay = findViewById(R.id.dateDisplay)
        eventEditText = findViewById(R.id.eventEditText)
        database = FirebaseDatabase.getInstance()

        dateDisplay.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val monthName = mesesDoAno[month]
            val data = getDate(year, month, dayOfMonth)
            val atividade = eventEditText.text.toString().trim()

            if (atividade.isNotEmpty()) {
                addActivity(data, atividade)
                Toast.makeText(this, "Atividade adicionado para $data", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, insira uma atividade.", Toast.LENGTH_SHORT).show()
            }

            binding.btInsere.setOnClickListener {
                val intent = Intent(this, FormCalendarActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun getDate(year: Int, month: Int, dayOfMonth: Int): String {
        val monthName = mesesDoAno[month]
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dayOfWeek = diasDaSemana[calendar.get(Calendar.DAY_OF_WEEK) - 1]
        val dataSelecionada = "$dayOfWeek, $dayOfMonth de $monthName de $year"
        return dataSelecionada.format(calendar.time)
    }

    private fun addActivity(data: String, atividade: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val atividadeAdd =
            database.getReference().child("atividades").child(userId).child(data).push()
        atividadeAdd.setValue(atividade)
            .addOnSuccessListener {
            }
            .addOnFailureListener { exception ->
                Toast.makeText(
                    this,
                    "Erro ao adicionar atividade: ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

    }
}