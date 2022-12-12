package com.ficagna.tcc2.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import com.ficagna.tcc2.R

class CalendarActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var dateDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calendarView = findViewById(R.id.calendarView)
        dateDisplay = findViewById(R.id.dateDisplay)
        dateDisplay!!.text = "Date: "
        calendarView!!.setOnDateChangeListener { calendar, i, i1, i2 ->
            dateDisplay!!.text = "Date: $i2 / $i1 / $i"
            Toast.makeText(
                applicationContext,
                "Selected Date:\nDay = $i2\nMonth = $i1\nYear = $i", Toast.LENGTH_LONG
            ).show()
        }
    }
}