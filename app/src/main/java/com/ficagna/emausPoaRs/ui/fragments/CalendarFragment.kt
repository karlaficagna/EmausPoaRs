package com.ficagna.emausPoaRs.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ficagna.emausPoaRs.databinding.FragmentCalendarBinding
import com.ficagna.emausPoaRs.ui.adapter.CalendarioAdapter
import com.ficagna.emausPoaRs.ui.model.Calendario
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    private lateinit var calendarioAdapter: CalendarioAdapter
    private val calendarios = mutableListOf<Calendario>()
    private lateinit var databaseReference: DatabaseReference
    private var selectedDate: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseReference = FirebaseDatabase.getInstance().reference.child("database").child("calendario")

        calendarioAdapter = CalendarioAdapter(calendarios)
        binding.recyclerViewEvents.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewEvents.adapter = calendarioAdapter

        binding.calendarView.setOnDateChangeListener { _, dayOfMonth, month,year  ->
            val selectedCalendar = Calendar.getInstance().apply {
                set(dayOfMonth, month, year)
            }
            selectedDate =
                SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(selectedCalendar.time)
            updateSelectedDate(selectedCalendar.time)
            loadEventsForDate(selectedDate!!)
        }

        binding.btnAddEvent.setOnClickListener {
            addEvent()
        }
    }

    private fun updateSelectedDate(date: Date) {
        val format = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        binding.tvSelectedDate.text = format.format(date)
    }

    private fun loadEventsForDate(date: String) {
        databaseReference.child(date).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                calendarios.clear()
                snapshot.children.mapNotNullTo(calendarios) {
                    it.getValue(Calendario::class.java)
                }
                calendarioAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun addEvent() {
        val eventText = binding.etEvent.text.toString()
        if (eventText.isNotEmpty() && selectedDate != null) {
            val eventId = databaseReference.child(selectedDate!!).push().key
            val calendario = Calendario(eventId!!, eventText)
            databaseReference.child(selectedDate!!).child(eventId).setValue(calendario).toString()
            binding.etEvent.text.clear()
            loadEventsForDate(selectedDate!!)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}