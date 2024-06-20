package com.ficagna.tcc2.ui.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ficagna.tcc2.R
import com.ficagna.tcc2.databinding.ActivityCalendarBinding


class CalendarActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var dateDisplay: TextView
    private lateinit var binding: ActivityCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calendarView = findViewById(R.id.calendarView)
        dateDisplay = findViewById(R.id.dateDisplay)
        dateDisplay.text = "Date: "
        calendarView.setOnDateChangeListener { calendar, i, i1, i2 ->
            dateDisplay.text = "Date: $i2 / $i1 / $i"

        }

        binding.btAdicionar.setOnClickListener {
            val intent = Intent(this, FormCalendarActivity::class.java)
            startActivity(intent)
        }
    }
}