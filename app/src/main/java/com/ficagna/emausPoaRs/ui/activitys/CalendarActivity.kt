package com.ficagna.emausPoaRs.ui.activitys


import android.R
import android.os.Bundle
import android.support.annotation.NonNull
import android.view.View
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.ficagna.emausPoaRs.databinding.ActivityCalendarBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class CalendarActivity : AppCompatActivity() {

    private var dateDisplay: CalendarView? = null
    private var eventEditText: EditText? = null
    private var stringDateSelected: String? = null
    private var databaseReference: DatabaseReference? = null

    private lateinit var binding: ActivityCalendarBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dateDisplay = binding.dateDisplay
        eventEditText = binding.eventEditText

        dateDisplay!!.setOnDateChangeListener(OnDateChangeListener() { dateDisplay, i, i1, i2 ->
            stringDateSelected = i.toString() + (i1 + 1).toString() + i2.toString()
            calendarClicked()
        })
        databaseReference = FirebaseDatabase.getInstance().getReference("calendario")
    }

    private fun calendarClicked() {
        databaseReference!!.child(stringDateSelected!!)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.value != null) {
                        eventEditText!!.setText(snapshot.value.toString())
                    } else {
                        eventEditText!!.setText("null")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
    }

    fun btSave(view: View?) {
        databaseReference!!.child(stringDateSelected!!).setValue(eventEditText!!.text.toString())
    }

}
