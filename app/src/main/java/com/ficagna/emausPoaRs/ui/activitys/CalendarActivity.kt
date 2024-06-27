package com.ficagna.emausPoaRs.ui.activitys


import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.ficagna.emausPoaRs.databinding.ActivityCalendarBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database


class CalendarActivity : AppCompatActivity() {

    private lateinit var dateDisplay: CalendarView
    private lateinit var eventEditText: EditText
    private lateinit var database: DatabaseReference
    private lateinit var stringDateSelected: String

    private lateinit var binding: ActivityCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dateDisplay = binding.dateDisplay
        eventEditText = binding.eventEditText

        dateDisplay.setOnDateChangeListener { _, year, month, dayOfMonth ->
            stringDateSelected = "$year${month + 1}$dayOfMonth"
            calendarClicked()
        }

        database = Firebase.database.reference.child("atividades")
    }

    private fun calendarClicked() {

        database.child(stringDateSelected)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.value != null) {
                        eventEditText.setText(snapshot.value.toString())
                    } else {
                        eventEditText.setText("")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })

        fun bt_save(view: View?) {
            database.child(stringDateSelected).setValue(eventEditText.text.toString())
        }
    }
}