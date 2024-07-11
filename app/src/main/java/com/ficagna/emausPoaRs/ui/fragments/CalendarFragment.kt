package com.ficagna.emausPoaRs.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.ficagna.emausPoaRs.databinding.FragmentCalendarBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class CalendarFragment : Fragment() {

    private var dateDisplay: CalendarView? = null
    private var eventEditText: EditText? = null
    private var stringDateSelected: String? = null
    private var databaseReference: DatabaseReference? = null

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dateDisplay = binding.dateDisplay
        eventEditText = binding.eventEditText

        dateDisplay!!.setOnDateChangeListener { _, year, month, dayOfMonth ->
            stringDateSelected = "$year${month + 1}$dayOfMonth"
            calendarClicked()
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("calendario")
    }

    private fun calendarClicked() {
        databaseReference!!.child(stringDateSelected!!)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    eventEditText!!.setText(snapshot.value?.toString() ?: "null")
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle possible errors.
                }
            })
    }

    fun btSave(view: View?) {
        databaseReference!!.child(stringDateSelected!!).setValue(eventEditText!!.text.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
